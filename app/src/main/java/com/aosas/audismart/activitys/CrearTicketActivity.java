package com.aosas.audismart.activitys;

import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.support.v7.app.ActionBar;
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
import com.aosas.audismart.comunication.proxy.AsyntaskFile;
import com.aosas.audismart.comunication.proxy.HttpFileUpload;
import com.aosas.audismart.model.Empresa;
import com.aosas.audismart.repository.Preferences;
import com.aosas.audismart.util.Constantes;
import com.aosas.audismart.util.Util;
import com.google.gson.JsonElement;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

import static android.widget.Toast.LENGTH_LONG;
import static android.widget.Toast.makeText;

/**
 * The type Crear ticket activity.
 * Contiene el focumlario para la creacion de un ticket nuevo
 */
public class CrearTicketActivity extends AppCompatActivity implements BaseActivity {
    private String path = null;
    private String nombreArchivo = "";
    private String idEmpresa = "";

    /**
     * The Button cargar archivo.
     */
    @InjectView(R.id.button_CargarArchivo)
    Button button_CargarArchivo;

    /**
     * The Text nombre archivo.
     */
    @InjectView(R.id.text_NombreArchivo)
    TextView text_NombreArchivo;

    /**
     * The Edit text empresas.
     */
    @InjectView(R.id.editText_Empresas)
    AutoCompleteTextView editText_Empresas;

    /**
     * The Button crear ticket.
     */
    @InjectView(R.id.button_crearTicket)
    Button button_crearTicket;

    /**
     * The Edit text titulo.
     */
    @InjectView(R.id.editText_Titulo)
    EditText editText_Titulo;

    /**
     * The Edit text asunto.
     */
    @InjectView(R.id.editText_Asunto)
    EditText editText_Asunto;

    /**
     * On create.
     *
     * @param savedInstanceState the saved instance state
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_ticket);
        ButterKnife.inject(this);

        //Se adiciona el icono a la barra de la actividad
        ActionBar actionBar = getSupportActionBar();
        actionBar.setLogo(R.drawable.logoapp);
        actionBar.setDisplayUseLogoEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);

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

    }

    /**
     * Button cargar archivo.
     *
     * @param view the view
     */
    @OnClick(R.id.button_CargarArchivo)
    public void button_CargarArchivo(View view) {
        cargarArchivo();
    }

    /**
     * Button crear ticket.
     *
     * @param view the view
     */
    @OnClick(R.id.button_crearTicket)
    public void button_crearTicket(View view) {
        crearTicket();
    }


    /*******************
     * Presentador¡¡ Logica de la  vista
     *******************/

    private void crearTicket() {

        Map<String, String> params = new HashMap<String, String>(4);
        params.put("id_cliente", Preferences.getIdClient(this));
        params.put("id_empresa", idEmpresa);
        params.put("titulo", editText_Titulo.getText().toString());
        params.put("asunto", editText_Asunto.getText().toString());
        params.put("ACCION", Constantes.REGISTRO_TICKET);

        new AsyntaskFile(path, nombreArchivo, params, this, Constantes.REGISTRO_TICKET).execute();


    }


    private void cargarListaEmpresas() {
        if (Preferences.getEmpresas(this) != null) {
            editText_Empresas.setEnabled(true);
            ArrayList arrayListEmpresas = Preferences.getEmpresas(this);
            AutocompleteEmpresaAdapter itemadapter = new AutocompleteEmpresaAdapter(this, R.layout.adapter_autotext, arrayListEmpresas);
            editText_Empresas.setAdapter(itemadapter);
            editText_Empresas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                public void onItemClick(AdapterView<?> listView, View view, int position, long id) {
                    Empresa empresa = (Empresa) listView.getItemAtPosition(position);
                    idEmpresa = empresa.id_empresa.replaceAll("\"", "");
                    Log.i("", idEmpresa);
                }
            });
        } else {
            editText_Empresas.setEnabled(false);
        }
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

    /**
     * On activity result.
     *
     * @param requestCode the request code
     * @param resultCode  the result code
     * @param data        the data
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case Constantes.FILE_SELECT_CODE:
                if (resultCode == RESULT_OK) {
                    Uri uri = data.getData();
                    try {
                        path = Util.getPath(this, uri);
                        String[] parametrosPath = path.split("/");
                        nombreArchivo = parametrosPath[parametrosPath.length - 1];
                        text_NombreArchivo.setText(nombreArchivo);
                    } catch (URISyntaxException e) {
                        e.printStackTrace();
                    }
                }
                break;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    /**
     * Succes.
     *
     * @param succes      the succes
     * @param jsonElement the json element
     */
    @Override
    public void succes(String succes, JsonElement jsonElement) {
        makeText(this, succes, LENGTH_LONG).show();
        finish();
    }

    /**
     * Error.
     *
     * @param error the error
     */
    @Override
    public void error(String error) {
        makeText(this, error, LENGTH_LONG).show();
    }
}
