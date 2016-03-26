package com.aosas.audismart.activitys;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.Toast;

import com.aosas.audismart.R;
import com.aosas.audismart.adapters.ExpandableListAdapter;
import com.aosas.audismart.comunication.IRepository;
import com.aosas.audismart.comunication.Repository;
import com.aosas.audismart.model.Empresa;
import com.aosas.audismart.model.FechaCliente;
import com.aosas.audismart.repository.Preferences;
import com.aosas.audismart.util.Constantes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MenuPrincipalActivity extends Activity {
    private ExpandableListAdapter listAdapter;
    private ExpandableListView expListView;
    private List<String> listDataHeader;
    private HashMap<String, List<String>> listDataChild;
    private IRepository repository = new Repository();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_principal);
        // preparing list data
        prepareListData();

      /*
        Get view parent an child
       */
        expListView = (ExpandableListView) findViewById(R.id.lvExp);
        listAdapter = new ExpandableListAdapter(this, listDataHeader, listDataChild);
        expListView.setAdapter(listAdapter);


        // Listview Group click listener
        expListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v,
                                        int groupPosition, long id) {
                return false;
            }
        });

        // Listview Group expanded listener
        expListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
            @Override
            public void onGroupExpand(int groupPosition) {
                Toast.makeText(getApplicationContext(),
                        listDataHeader.get(groupPosition) + " Expanded",
                        Toast.LENGTH_SHORT).show();
            }
        });

        // Listview Group collasped listener
        expListView.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() {

            @Override
            public void onGroupCollapse(int groupPosition) {
                Toast.makeText(getApplicationContext(),
                        listDataHeader.get(groupPosition) + " Collapsed",
                        Toast.LENGTH_SHORT).show();

            }
        });

        // Listview on child click listener
        expListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {

            @Override
            public boolean onChildClick(ExpandableListView parent, View v,
                                        int groupPosition, int childPosition, long id) {
                asignarResponsabilidad(groupPosition,childPosition);
                return false;
            }
        });

    }

    /*
     * Preparing the list data
     */
    private void prepareListData() {
        listDataHeader = new ArrayList<String>();
        listDataChild = new HashMap<String, List<String>>();

        // Adding child data
        listDataHeader.add("Mis notificaciones");
        listDataHeader.add("Mis tickets");
        listDataHeader.add("Mi Perfil");

        // Adding child data
        List<String> notificaciones = new ArrayList<String>();
        notificaciones.add("Vencidas");
        notificaciones.add("Para hoy");
        notificaciones.add("Próximas");

        List<String> tickets = new ArrayList<String>();
        tickets.add("Pendientes");
        tickets.add("Respondidos");
        tickets.add("Por Calificar");


        List<String> perfil = new ArrayList<String>();
        perfil.add("Mis Empresas");
        perfil.add("Mi información personal");

        listDataChild.put(listDataHeader.get(0), notificaciones); // Header, Child data
        listDataChild.put(listDataHeader.get(1), tickets);
        listDataChild.put(listDataHeader.get(2), perfil);
    }


    /*******************
     Presentador¡¡ Logica de la  vista
     *******************/

    private  void asignarResponsabilidad(int groupPosition,int childPosition){
        switch (groupPosition & childPosition){
            case 0 & 0:
                notificacionesVencidas();
                break;

        }
    }

    private void notificacionesVencidas() {
        consumoWSNotificaciones();

    }

    private void consumoWSNotificaciones(){
        FechaCliente fechaCliente = new FechaCliente(Preferences.getClient(this), "0", Preferences.getIdCompany(this),Constantes.CONSULTA_FECHASCLIENTE);
        repository.createRequets(this, fechaCliente, Constantes.CONSULTA_FECHASCLIENTE);
    }
}


