package com.aosas.audismart.activitys;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.ExpandableListView;
import android.widget.ImageButton;
import android.widget.Toast;

import com.aosas.audismart.R;
import com.aosas.audismart.adapters.AutocompleteCalendarioAdapter;
import com.aosas.audismart.adapters.AutocompleteEmpresaAdapter;
import com.aosas.audismart.adapters.LevelMenuPrincipal;
import com.aosas.audismart.comunication.proxy.IRepository;
import com.aosas.audismart.comunication.proxy.Repository;
import com.aosas.audismart.model.Calendario;
import com.aosas.audismart.model.ClienteUnico;
import com.aosas.audismart.model.Empresa;
import com.aosas.audismart.model.FechaCliente;
import com.aosas.audismart.model.Notificacion;
import com.aosas.audismart.model.User;
import com.aosas.audismart.repository.Preferences;
import com.aosas.audismart.util.Constantes;
import com.aosas.audismart.util.Util;
import com.aosas.audismart.util.alarm.ScheduleClient;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
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
import static android.widget.Toast.makeText;

public class MenuPrincipalActivity extends AppCompatActivity implements BaseActivity {
    private ExpandableListView explvlist;
    private HashMap<String, List<Notificacion>> listDataChild;
    private List<String> listDataHeaderNotificaciones;
    private IRepository repository = new Repository();
    private List<Notificacion> notificacionesVencidas;
    private List<Notificacion> notificacionesHoy;
    private List<Notificacion> notificacionesProximas;
    private List<Notificacion> notificacionesArchivadas;
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

        ImageButton calendario = (ImageButton) findViewById(R.id.botoncalendario);
        calendario.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Intent intentCalendario = new Intent(MenuPrincipalActivity.this, CalendarioActivity.class);
                startActivity(intentCalendario);

            }
        });

        consumoWSNotificaciones();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_splash, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch (item.getItemId())
        {
            case R.id.action_perfil:
                Intent i = new Intent(this,PerfilActivity.class);
                startActivity(i);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void calendario(View v){
       Intent i = new Intent(this,CalendarioActivity.class);
        startActivity(i);

    }

    @Override
    protected void onStop() {
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

        listDataHeaderNotificaciones = new ArrayList<String>();
        listDataChild = new HashMap<String, List<Notificacion>>();

        listDataHeaderNotificaciones.add("Vencidas");
        listDataHeaderNotificaciones.add("Para hoy");
        listDataHeaderNotificaciones.add("Próximas");
        listDataHeaderNotificaciones.add("Archivadas");

        listDataChild.put(listDataHeaderNotificaciones.get(0), notificacionesVencidas);// Header, Child data
        listDataChild.put(listDataHeaderNotificaciones.get(1), notificacionesHoy);
        listDataChild.put(listDataHeaderNotificaciones.get(2), notificacionesProximas);
        listDataChild.put(listDataHeaderNotificaciones.get(3), notificacionesProximas);
    }


    /*******************
     Presentador¡¡ Logica de la  vista
     *******************/

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

    private void notificacionesArchivadas() {
        notificacionesArchivadas = new ArrayList<Notificacion>();
        if(notificaciones!=null){
            int j=0;
            for(int i = 0;i<notificaciones.size();i++){
                try {
                    Date todayDate = SFD.parse(SFD.format(new Date ()));
                    if(notificaciones.get(i).cumplido.equals("1")&(Util.stringToDate(Constantes.FORMATOFECHANOTIDICACIONJSON,notificaciones.get(i).fecha)!=null )){
                        Date dateNotificaciones= SFD.parse(SFD.format((Util.stringToDate(Constantes.FORMATOFECHANOTIDICACIONJSON,notificaciones.get(i).fecha))));
                        if(dateNotificaciones.before(todayDate)){
                            notificacionesArchivadas.add(j,notificaciones.get(i));
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
            ClienteUnico clienteUnico = new ClienteUnico(Preferences.getIdClient(this),Constantes.BUSCAR_CLIENTE_UNICO);
            repository.createRequets(this,clienteUnico,Constantes.BUSCAR_CLIENTE_UNICO);
    }

    @Override
    public void succes(String succes, JsonElement jsonElement) {
        if(succes.equals("Se actualizo con exito")){
            Toast.makeText(this, succes, Toast.LENGTH_LONG).show();
            consumoWSNotificaciones();
        }else if (succes.equals("Cliente encontrado")){
            JsonObject jsonObject = jsonElement.getAsJsonObject();
            User user = new User(jsonObject.get("nombres").toString().replace('\"',' '),jsonObject.get("apellidos").toString().replace('\"',' '),jsonObject.get("email").toString().replace('\"',' '),jsonObject.get("id_departamento").toString().replace('\"',' '),jsonObject.get("id_ciudad").toString().replace('\"',' '),jsonObject.get("telefono").toString().replace('\"',' '),"","","","");
            Preferences.setUsuario(this,user);
            FechaCliente fechaCliente = new FechaCliente(Preferences.getIdClient(this), idCalendario, idEmpresa, Constantes.CONSULTA_FECHASCLIENTE);
            repository.createRequets(this, fechaCliente, Constantes.CONSULTA_FECHASCLIENTE);
        }
        else if (succes.substring(0,18).equals("Fechas encontradas")){

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

            Preferences.setNotificaciones(this,notificaciones);

            notificacionesVencidas();
            notificacionesHoy();
            notificacionesProximas();
            notificacionesArchivadas();
            prepareListData();

            explvlist = (ExpandableListView)findViewById(R.id.ParentLevel);
            explvlist.setAdapter(new LevelMenuPrincipal(this, listDataChild, listDataHeaderNotificaciones));
            initalarm();
        }}


    }

    @Override
    public void error(String error) {
        makeText(MenuPrincipalActivity.this, error, LENGTH_LONG).show();
    }
}


