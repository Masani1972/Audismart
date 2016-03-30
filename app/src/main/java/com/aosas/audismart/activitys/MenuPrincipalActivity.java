package com.aosas.audismart.activitys;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.Toast;

import com.aosas.audismart.R;
import com.aosas.audismart.adapters.ParentLevel;
import com.aosas.audismart.comunication.IRepository;
import com.aosas.audismart.comunication.Repository;
import com.aosas.audismart.model.FechaCliente;
import com.aosas.audismart.model.GCM;
import com.aosas.audismart.model.Notificacion;
import com.aosas.audismart.repository.Preferences;
import com.aosas.audismart.util.Constantes;
import com.aosas.audismart.util.Util;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static android.widget.Toast.LENGTH_LONG;
import static android.widget.Toast.LENGTH_SHORT;
import static android.widget.Toast.makeText;

public class MenuPrincipalActivity extends AppCompatActivity implements BaseActivity {
    private ExpandableListView explvlist;
    private List<String> listDataHeader;
    private HashMap<String, List<String>> listDataChild;
    private List<String> listDataHeaderNotificaciones;
    private IRepository repository = new Repository();
    private List<String> notificacionesVencidas = new ArrayList<String>();;
    private ArrayList<Notificacion> notificaciones;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_principal);
        consumoWSNotificaciones();
        prepareListData();

        explvlist = (ExpandableListView)findViewById(R.id.ParentLevel);
        explvlist.setAdapter(new ParentLevel(this, listDataHeader, listDataChild, listDataHeaderNotificaciones));

        // Listview Group expanded listener
        explvlist.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
            @Override
            public void onGroupExpand(int groupPosition) {
                asignarResponsabilidad(groupPosition);
            }
        });

    }

    /*
     * Preparing the list data
    */

    private void prepareListData() {
        listDataHeader = new ArrayList<String>();
        listDataHeaderNotificaciones = new ArrayList<String>();
        listDataChild = new HashMap<String, List<String>>();

        // Adding child data
        listDataHeader.add("Mis notificaciones");
        listDataHeader.add("Mis tickets");
        listDataHeader.add("Mi Perfil");

        listDataHeaderNotificaciones.add("Vencidas");
        listDataHeaderNotificaciones.add("Para hoy");
        listDataHeaderNotificaciones.add("Próximas");





        listDataChild.put(listDataHeaderNotificaciones.get(0), notificacionesVencidas); // Header, Child data

    }


    /*******************
     Presentador¡¡ Logica de la  vista
     *******************/

    private  void asignarResponsabilidad(int groupPosition){
        switch (groupPosition ){
            case 0 :
                notificacionesVencidas();
                break;

        }
    }

    private void notificacionesVencidas() {
        if(notificaciones!=null){
            for(int i = 0;i<notificaciones.size();i++){
                if(notificaciones.get(i).cumplido.equals("0"))
                    notificacionesVencidas.add(i,notificaciones.get(i).nombre+" "+ notificaciones.get(i).id);
            }
        }

    }

    private void consumoWSNotificaciones(){
        FechaCliente fechaCliente = new FechaCliente(Preferences.getClient(this), "0", Preferences.getIdCompany(this), Constantes.CONSULTA_FECHASCLIENTE);
        repository.createRequets(this, fechaCliente, Constantes.CONSULTA_FECHASCLIENTE);
    }

    @Override
    public void succes(String succes, JsonElement jsonElement) {
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
                Notificacion notificacion = new Notificacion(id,idFecha,idEmpresa,nombreEmpresa,idCalendario,fecha,hora,antesDias,antesHora,antesFecha,nombre,nombreCorto,periodo,cumplido,fechaCumplido);
                notificaciones.add(i,notificacion);
            }
        }


    }

    @Override
    public void error(String error) {
        makeText(MenuPrincipalActivity.this, error, LENGTH_LONG).show();
    }



}


