package com.aosas.audismart.activitys;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.ListView;

import com.aosas.audismart.R;
import com.aosas.audismart.adapters.AutocompleteEmpresaAdapter;
import com.aosas.audismart.adapters.NoticiasListAdapter;
import com.aosas.audismart.comunication.proxy.IRepository;
import com.aosas.audismart.comunication.proxy.Repository;
import com.aosas.audismart.model.BuscarNoticia;
import com.aosas.audismart.model.ClienteUnico;
import com.aosas.audismart.model.Empresa;
import com.aosas.audismart.model.Noticia;
import com.aosas.audismart.model.Ticket;
import com.aosas.audismart.repository.Preferences;
import com.aosas.audismart.util.Constantes;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class NoticiasActivity extends AppCompatActivity implements BaseActivity  {

    private String idEmpresa = "0";
    private IRepository repository = new Repository();

    @InjectView(R.id.editText_Empresas)
    AutoCompleteTextView editText_Empresas;

    @InjectView(R.id.lvItems)
    ListView lvItems;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_noticias);
        ButterKnife.inject(this);

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

        consumoWSNoticias();
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

                consumoWSNoticias();
            }
        });
    }


    private void consumoWSNoticias(){
        BuscarNoticia buscarNoticia = new BuscarNoticia(idEmpresa, Preferences.getIdClient(this), Constantes.BUSCAR_NOTICIA);
        repository.createRequets(this,buscarNoticia,Constantes.BUSCAR_NOTICIA);
    }

    @Override
    public void succes(String succes, JsonElement jsonElement) {
        if(succes.substring(0,20).equals(Constantes.BUSCAR_NOTICIA_RESPONSE)){
            JsonArray jsonArray = jsonElement.getAsJsonArray();
            setNoticias(jsonArray);
        }
    }

    private void setNoticias(JsonArray jsonArray) {
        ArrayList noticias = new ArrayList<Noticia>();
        if (jsonArray != null) {
            for (int i = 0; i < jsonArray.size(); i++) {

                String id_noticia = ((JsonObject) jsonArray.get(i)).get("id_noticia").getAsString();
                String titulo = ((JsonObject) jsonArray.get(i)).get("titulo").getAsString();
                String contenido = ((JsonObject) jsonArray.get(i)).get("contenido").getAsString();

                String archivo = "";
                if(!((JsonObject) jsonArray.get(i)).get("archivo").isJsonNull())
                    archivo = ((JsonObject) jsonArray.get(i)).get("archivo").getAsString();

                String rutaarchivo = "";
                if(!((JsonObject) jsonArray.get(i)).get("rutaarchivo").isJsonNull())
                    rutaarchivo = ((JsonObject) jsonArray.get(i)).get("rutaarchivo").getAsString();

                String fecha = ((JsonObject) jsonArray.get(i)).get("fecha").getAsString();
                String hora = ((JsonObject) jsonArray.get(i)).get("hora").getAsString();
                String empresa = ((JsonObject) jsonArray.get(i)).get("empresa").getAsString();
                String id_empresa = ((JsonObject) jsonArray.get(i)).get("id_empresa").getAsString();
                Noticia noticia = new Noticia(id_noticia, titulo, contenido, archivo,rutaarchivo, fecha, hora,  empresa, id_empresa, "");
                noticias.add(i, noticia);
            }
            Preferences.setTickets(this, noticias);
            cargarNoticias(noticias);
        }
    }

    private void cargarNoticias(ArrayList<Noticia> noticias) {
        NoticiasListAdapter adapter = new NoticiasListAdapter(this, noticias);
        lvItems.setAdapter(adapter);
    }

    @Override
    public void error(String error) {

    }
}
