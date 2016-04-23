package com.aosas.audismart.activitys;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.TextView;
import android.widget.Toast;

import com.aosas.audismart.R;
import com.aosas.audismart.adapters.AutocompleteCalendarioAdapter;
import com.aosas.audismart.adapters.AutocompleteCategoriaAdapter;
import com.aosas.audismart.adapters.AutocompleteEmpresaAdapter;
import com.aosas.audismart.adapters.ParentLevel;
import com.aosas.audismart.comunication.IRepository;
import com.aosas.audismart.comunication.Repository;
import com.aosas.audismart.model.Calendario;
import com.aosas.audismart.model.Categoria;
import com.aosas.audismart.model.Empresa;
import com.aosas.audismart.model.FechaCliente;
import com.aosas.audismart.model.GCM;
import com.aosas.audismart.model.Notificacion;
import com.aosas.audismart.repository.FileAsserts;
import com.aosas.audismart.repository.Preferences;
import com.aosas.audismart.util.AlarmReceiver;
import com.aosas.audismart.util.Constantes;
import com.aosas.audismart.util.Util;
import com.aosas.audismart.util.alarm.ScheduleClient;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import org.json.JSONArray;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

import static android.widget.Toast.LENGTH_LONG;
import static android.widget.Toast.LENGTH_SHORT;
import static android.widget.Toast.makeText;

public class MenuPrincipalActivity extends AppCompatActivity implements BaseActivity {
    private ExpandableListView explvlist;
    private List<String> listDataHeader;
    private HashMap<String, List<Notificacion>> listDataChild;
    private HashMap<String, List<String>> listDataChildOne;
    private List<String> listDataHeaderNotificaciones;
    private IRepository repository = new Repository();
    private List<Notificacion> notificacionesVencidas;
    private List<Notificacion> notificacionesHoy;
    private List<Notificacion> notificacionesProximas;
    private ArrayList<Notificacion> notificaciones;
    private static final SimpleDateFormat SFD = new SimpleDateFormat(Constantes.FORMATOFECHANOTIDICACIONJSON);
    private String idEmpresa = "0";
    private String idCalendario = "0";
    private ScheduleClient scheduleClient;

    @InjectView(R.id.editText_Empresas)
    AutoCompleteTextView editText_Empresas;

    @InjectView(R.id.editText_Impuestos)
    AutoCompleteTextView editText_Impuestos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_principal);
        ButterKnife.inject(this);
        scheduleClient = new ScheduleClient(this);
        scheduleClient.doBindService();

        /*listener  autocomplete no soportado por ButterKnife*/
        editText_Empresas.setThreshold(1);
        editText_Empresas.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                cargarListaEmpresas();
                editText_Empresas.showDropDown();
                return false;
            }
        });

        /*listener  autocomplete no soportado por ButterKnife*/
        editText_Impuestos.setThreshold(1);
        editText_Impuestos.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                cargarListaImpuestos();
                editText_Impuestos.showDropDown();
                return false;
            }
        });


        consumoWSNotificaciones();
    }

    @Override
    protected void onStop() {
        // When our activity is stopped ensure we also stop the connection to the service
        // this stops us leaking our activity into the system *bad*
        //if(scheduleClient != null)
          //  scheduleClient.doUnbindService();
        super.onStop();
    }

    @Override
    protected void onResume (){

        super.onResume();
    }

    /*******************
     Presentador¡¡ Logica de la  vista
     *******************/

    public void initalarm(){
        ArrayList<Notificacion> notificacions = Preferences.getNotificaciones(this);
       // notificacions.get(0).antesFecha = "2016-04-23 14:43:00";

        ArrayList<Calendar> calendars = new ArrayList<Calendar>();
        for (int i =0;i<notificacions.size();i++) {
            String fecha = notificacions.get(i).antesFecha;

            Calendar cal = Calendar.getInstance();
            Date date = Util.stringToDate(Constantes.FORMATOFECHANOTIDICACIONJSONNOTIFICACION, fecha);
            cal.setTime(date);


            calendars.add(i,cal);
          /*  Calendar cal = Calendar.getInstance();
            cal.set(Calendar.MONTH, 3);
            cal.set(Calendar.YEAR, 2016);
            cal.set(Calendar.DAY_OF_MONTH, 23);
            cal.set(Calendar.HOUR_OF_DAY, 13);
            cal.set(Calendar.MINUTE, 31);
            cal.set(Calendar.SECOND, 00);*/
            Log.i("fecha hra", "" + cal.getTime());


        }
        scheduleClient.setAlarmForNotification(calendars);
        }




    private void cargarListaEmpresas() {
        ArrayList arrayListEmpresas = Preferences.getEmpresas(this);
        AutocompleteEmpresaAdapter itemadapter = new AutocompleteEmpresaAdapter(this, R.layout.adapter_autotext,arrayListEmpresas);
        editText_Empresas.setAdapter(itemadapter);
        editText_Empresas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> listView, View view, int position, long id) {
                Empresa empresa = (Empresa) listView.getItemAtPosition(position);
                idEmpresa = empresa.id_empresa.replaceAll("\"", "");
                Log.i("",idEmpresa);

                consumoWSNotificaciones();
            }
        });
    }

    private void cargarListaImpuestos() {
        ArrayList arrayListImpuestos = Preferences.getCalendarios(this);
        AutocompleteCalendarioAdapter itemadapter = new AutocompleteCalendarioAdapter(this, R.layout.adapter_autotext,arrayListImpuestos);
        editText_Impuestos.setAdapter(itemadapter);
        editText_Impuestos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> listView, View view, int position, long id) {
                Calendario calendario = (Calendario) listView.getItemAtPosition(position);
                idCalendario = calendario.id_calendario.replaceAll("\"", "");
                Log.i("",idCalendario);

                consumoWSNotificaciones();
            }
        });
    }

    /*
     * Preparing the list data
    */

    private void prepareListData() {

        listDataHeader = new ArrayList<String>();
        listDataHeaderNotificaciones = new ArrayList<String>();
        listDataChild = new HashMap<String, List<Notificacion>>();
        listDataChildOne = new HashMap<String, List<String>>();

        // Adding child data
        listDataHeader.add("Mis notificaciones");
        listDataHeader.add("Mis tickets");
        listDataHeader.add("Mi Perfil");

        listDataHeaderNotificaciones.add("Vencidas");
        listDataHeaderNotificaciones.add("Para hoy");
        listDataHeaderNotificaciones.add("Próximas");

        listDataChild.put(listDataHeaderNotificaciones.get(0), notificacionesVencidas);// Header, Child data
        listDataChild.put(listDataHeaderNotificaciones.get(1), notificacionesHoy);
        listDataChild.put(listDataHeaderNotificaciones.get(2), notificacionesProximas);
    }


    /*******************
     Presentador¡¡ Logica de la  vista
     *******************/

    private  void asignarResponsabilidad(int groupPosition){
        switch (groupPosition ){
            case 0 :
                notificacionesVencidas();
                break;
            case 1 :
                notificacionesHoy();
                break;

        }
    }

    private void notificacionesVencidas() {
        notificacionesVencidas = new ArrayList<Notificacion>();
        if(notificaciones!=null){
            int j=0;
            for(int i = 0;i<notificaciones.size();i++){
                try {
                    Date todayDate = SFD.parse(SFD.format(new Date ()));
                    if(notificaciones.get(i).cumplido.equals("0")&(Util.stringToDate(Constantes.FORMATOFECHANOTIDICACIONJSON,notificaciones.get(i).fecha)!=null )){
                        Date dateNotificaciones= SFD.parse(SFD.format((Util.stringToDate(Constantes.FORMATOFECHANOTIDICACIONJSON,notificaciones.get(i).fecha))));
                        if(dateNotificaciones.before(todayDate)){
                            notificacionesVencidas.add(j,notificaciones.get(i));
                            j++;}}
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void notificacionesHoy() {
        notificacionesHoy = new ArrayList<Notificacion>();
        if(notificaciones!=null){
            int j=0;
            for(int i = 0;i<notificaciones.size();i++){
                if(notificaciones.get(i).cumplido.equals("0")&(Util.stringToDate(Constantes.FORMATOFECHANOTIDICACIONJSON,notificaciones.get(i).fecha)!=null) &(SFD.format(Util.stringToDate(Constantes.FORMATOFECHANOTIDICACIONJSON,notificaciones.get(i).fecha)).equals(SFD.format(new Date())))){
                    notificacionesHoy.add(j,notificaciones.get(i));
                    j++;}
            }
        }
    }

    private void notificacionesProximas() {
        notificacionesProximas = new ArrayList<Notificacion>();
        if(notificaciones!=null){
            int j=0;
            for(int i = 0;i<notificaciones.size();i++){
                try {
                    Date todayDate = SFD.parse(SFD.format(new Date ()));
                    if(notificaciones.get(i).cumplido.equals("0")&(Util.stringToDate(Constantes.FORMATOFECHANOTIDICACIONJSON,notificaciones.get(i).fecha)!=null )){
                        Date dateNotificaciones= SFD.parse(SFD.format((Util.stringToDate(Constantes.FORMATOFECHANOTIDICACIONJSON,notificaciones.get(i).fecha))));
                        if(dateNotificaciones.after(todayDate)){
                        notificacionesProximas.add(j,notificaciones.get(i));
                        j++;}}
                } catch (ParseException e) {
                    e.printStackTrace();
                }

            }
        }
    }

    private void consumoWSNotificaciones(){
        FechaCliente fechaCliente = new FechaCliente(Preferences.getClient(this), idCalendario, idEmpresa, Constantes.CONSULTA_FECHASCLIENTE);
        repository.createRequets(this, fechaCliente, Constantes.CONSULTA_FECHASCLIENTE);
    }

    @Override
    public void succes(String succes, JsonElement jsonElement) {
        if(succes.equals("Se actualizo con exito")){
            Toast.makeText(this, succes, Toast.LENGTH_LONG).show();
            consumoWSNotificaciones();

        }else{

         notificaciones = new ArrayList<Notificacion>();
        JsonArray jsonArray = jsonElement.getAsJsonArray();
        if (jsonArray != null) {
            for (int i = 0; i < jsonArray.size(); i++) {
                String id = ((JsonObject)jsonArray.get(i)).get("id").getAsString();
                String idFecha = ((JsonObject)jsonArray.get(i)).get("id_fecha").getAsString();
                String idEmpresa = ((JsonObject)jsonArray.get(i)).get("id_empresa").getAsString();
                String nombreEmpresa = ((JsonObject)jsonArray.get(i)).get("nombreempresa").getAsString();
                String idCalendario = ((JsonObject)jsonArray.get(i)).get("id_calendario").getAsString();
                String fecha = ((JsonObject)jsonArray.get(i)).get("fecha").getAsString();
                String hora = ((JsonObject)jsonArray.get(i)).get("hora").getAsString();
                String antesDias = ((JsonObject)jsonArray.get(i)).get("antesdias").getAsString();
                String antesHora = ((JsonObject)jsonArray.get(i)).get("anteshora").getAsString();
                String antesFecha = ((JsonObject)jsonArray.get(i)).get("antesfecha").getAsString();
                String nombre = ((JsonObject)jsonArray.get(i)).get("nombre").getAsString();
                String nombreCorto = ((JsonObject)jsonArray.get(i)).get("nombrecorto").getAsString();
                String periodo = ((JsonObject)jsonArray.get(i)).get("periodo").getAsString();
                String cumplido = ((JsonObject)jsonArray.get(i)).get("cumplido").getAsString();
                String fechaCumplido = (((JsonObject)jsonArray.get(i)).get("fechacumplido"))==null?((JsonObject)jsonArray.get(i)).get("fechacumplido").getAsString():"";
                Notificacion notificacion = new Notificacion(id,idFecha,idEmpresa,nombreEmpresa,idCalendario,fecha,hora,antesDias,antesHora,antesFecha,nombre,nombreCorto,periodo,cumplido,fechaCumplido,"");
                notificaciones.add(i, notificacion);
            }
            if(Preferences.getNotificaciones(this)!=null)
                Preferences.clearNotificaciones(this);
            Preferences.setNotificaciones(this,notificaciones);

            notificacionesVencidas();
            notificacionesHoy();
            notificacionesProximas();
            prepareListData();

            explvlist = (ExpandableListView)findViewById(R.id.ParentLevel);
            explvlist.setAdapter(new ParentLevel(this, listDataHeader, listDataChild, listDataHeaderNotificaciones));
            initalarm();
        }}


    }

    @Override
    public void error(String error) {
        makeText(MenuPrincipalActivity.this, error, LENGTH_LONG).show();
    }




}


