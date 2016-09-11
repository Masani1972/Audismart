package com.aosas.audismart.activitys;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.aosas.audismart.R;
import com.aosas.audismart.adapters.AutocompleteEmpresaAdapter;
import com.aosas.audismart.model.Empresa;
import com.aosas.audismart.repository.Preferences;
import com.aosas.audismart.util.Constantes;
import com.aosas.audismart.util.Util;
import com.google.gson.JsonElement;

import java.net.URISyntaxException;
import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

import static android.widget.Toast.LENGTH_LONG;
import static android.widget.Toast.makeText;

public class CrearTicketActivity extends AppCompatActivity implements BaseActivity{
    private String path = null;
    private String idEmpresa = "";

    @InjectView(R.id.button_CargarArchivo)
    Button button_CargarArchivo;

    @InjectView(R.id.text_NombreArchivo)
    TextView text_NombreArchivo;

    @InjectView(R.id.editText_Empresas)
    AutoCompleteTextView editText_Empresas;

    @InjectView(R.id.button_crearTicket)
    Button button_crearTicket;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_ticket);
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
        cargarListaEmpresas();

    }

    @OnClick(R.id.button_CargarArchivo)
    public void button_CargarArchivo(View view) {
        cargarArchivo();
    }

    @OnClick(R.id.button_crearTicket)
    public void button_crearTicket(View view){crearTicket();}


    /*******************
     Presentador¡¡ Logica de la  vista
     *******************/

    private void crearTicket() {
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
            }
        });
    }


    private void cargarArchivo() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("*/*");
        intent.addCategory(Intent.CATEGORY_OPENABLE);

        try {
            startActivityForResult(
                    Intent.createChooser(intent, "Select a File to Upload"),
                    Constantes.FILE_SELECT_CODE);
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(this, "Please install a File Manager.",
                    Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case Constantes.FILE_SELECT_CODE:
                if (resultCode == RESULT_OK) {
                    Uri uri = data.getData();
                    try {
                        path = Util.getPath(this, uri);
                        String[] parametrosPath = path.split("/");
                        text_NombreArchivo.setText(parametrosPath[parametrosPath.length-1]);
                    } catch (URISyntaxException e) {
                        e.printStackTrace();
                    }
                }
                break;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void succes(String succes, JsonElement jsonElement) {

    }

    @Override
    public void error(String error) {
        makeText(this, error, LENGTH_LONG).show();
    }
}
