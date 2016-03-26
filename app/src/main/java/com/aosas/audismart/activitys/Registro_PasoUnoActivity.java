package com.aosas.audismart.activitys;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.aosas.audismart.R;
import com.aosas.audismart.adapters.AutocompleteCiudadAdapter;
import com.aosas.audismart.adapters.AutocompleteDepartamentoAdapter;
import com.aosas.audismart.comunication.IRepository;
import com.aosas.audismart.comunication.Repository;
import com.aosas.audismart.model.Ciudad;
import com.aosas.audismart.model.Departamento;
import com.aosas.audismart.model.User;
import com.aosas.audismart.repository.FileAsserts;
import com.aosas.audismart.repository.Preferences;
import com.aosas.audismart.util.Constantes;
import com.aosas.audismart.util.Util;
import com.google.gson.JsonElement;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnCheckedChanged;
import butterknife.OnClick;

public class Registro_PasoUnoActivity extends AppCompatActivity implements BaseActivity{
    private String idDepartamento = "";
    private String idCiudad = "";
    private boolean aceptaTerminos = false;
    private String contrasenaMD5 = "";
    private static final String TAG = "Registro_PasoUnoActivity";

    @InjectView(R.id.layout_Form)
    RelativeLayout layout_Form;

    @InjectView(R.id.editText_Nombres)
    EditText editText_Nombres;

    @InjectView(R.id.editText_Apellidos)
    EditText editText_Apellidos;

    @InjectView(R.id.editText_Email)
    EditText editText_Email;

    @InjectView(R.id.editText_Departamento)
    AutoCompleteTextView editText_Departamento;

    @InjectView(R.id.editText_Ciudad)
    AutoCompleteTextView editText_Ciudad;

    @InjectView(R.id.editText_Telefono)
    EditText editText_Telefono;

    @InjectView(R.id.editText_Contrasena)
    EditText editText_Contrasena;

    @InjectView(R.id.checkBox_terminos)
    CheckBox checkBox_terminos;

    @InjectView(R.id.button_Continuar)
    Button button_Continuar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_pasouno);
        ButterKnife.inject(this);

        /*
        Permite adicionar un icono al action bar
         */
        ActionBar actionBar = getSupportActionBar();
        actionBar.setLogo(R.mipmap.ic_launcher);
        actionBar.setDisplayUseLogoEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setSubtitle(getResources().getString(R.string.subtitulo));

        /*listener  autocomplete no soportado por ButterKnife*/
        editText_Departamento.setThreshold(1);
        editText_Departamento.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                cargarListaDepartamentos();
                editText_Departamento.showDropDown();
                return false;
            }
        });

        /*listener  autocomplete no soportado por ButterKnife*/
        editText_Ciudad.setThreshold(1);
        editText_Ciudad.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                cargarListaCiudades();
                editText_Ciudad.showDropDown();
                return false;
            }
        });

    }

    @OnCheckedChanged(R.id.checkBox_terminos)
    public void checkBox_terminos(boolean value)
    {
        aceptaTerminos = value;
    }

    @OnClick(R.id.button_Continuar)
    public void button_Continuar(View view) {
        validar_formulario();
    }




    /*******************
    Presentador¡¡ Logica de la  vista
     *******************/

    /*
    Solicita la lista de departamento al repositorio local
     */
    private void cargarListaDepartamentos() {
        ArrayList arrayListDepartamentos = Util.jsontoArrayList(FileAsserts.loadJSONFromAsset(this,"departamentos"), new Departamento());
        AutocompleteDepartamentoAdapter itemadapter = new AutocompleteDepartamentoAdapter(this, R.layout.adapter_autotext,arrayListDepartamentos);
        editText_Departamento.setAdapter(itemadapter);
        editText_Departamento.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> listView, View view, int position, long id) {
                Departamento departamento = (Departamento) listView.getItemAtPosition(position);
                idDepartamento = departamento.Id_departamento;
            }
        });
    }

    /*
    Solicita la lista de ciudades al repositorio local de acuerdo al departamento
     */
    private void cargarListaCiudades() {
        if(idDepartamento.length()>0) {
            ArrayList arrayListCiudades = Util.jsontoArrayList(FileAsserts.readJsonDescripcion(this, idDepartamento), new Ciudad());
            AutocompleteCiudadAdapter itemadapter = new AutocompleteCiudadAdapter(this, R.layout.adapter_autotext,arrayListCiudades);
            editText_Ciudad.setAdapter(itemadapter);
            editText_Ciudad.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                public void onItemClick(AdapterView<?> listView, View view, int position, long id) {
                    Ciudad ciudad = (Ciudad) listView.getItemAtPosition(position);
                    idCiudad = ciudad.Id_ciudad;
                }
            });
        }else{
            Toast.makeText(Registro_PasoUnoActivity.this, R.string.campoDepartamentoIvalido,Toast.LENGTH_LONG).show();
        }

    }

    /*
    Validar los campos EditText del formulario
    mediante el ciclo onteniendo cada una de las vista y validando la longitud del texto
     */
    private void validar_formulario() {
        int editTextOk = 0;
        int childcount = layout_Form.getChildCount();
        for (int i = 1; i < childcount; i=i+2) {
            View v = layout_Form.getChildAt(i);
            EditText tv = (EditText) v;
            if ((tv != null && tv.getText().toString().length() > 0))
                editTextOk++;
            continue;
        }

        if(aceptaTerminos) {
            if (editTextOk == childcount/2) {
                if(Util.textToMD5(editText_Contrasena.getText().toString()).length()>0)
                    contrasenaMD5 = Util.textToMD5(editText_Contrasena.getText().toString());
                User user = new User(editText_Nombres.getText().toString(), editText_Apellidos.getText().toString(), editText_Email.getText().toString(), idDepartamento, idCiudad, editText_Telefono.getText().toString(), contrasenaMD5, "1", "1", Constantes.REGISTRO_USUARIO);
                IRepository repository = new Repository();
                repository.createRequets(this, user, Constantes.REGISTRO_USUARIO);

            } else {
                Toast.makeText(Registro_PasoUnoActivity.this, R.string.formularioIncompleto, Toast.LENGTH_LONG).show();
            }
        }else{Toast.makeText(Registro_PasoUnoActivity.this, R.string.campoTerminos,Toast.LENGTH_LONG).show();}
    }


    @Override
    public void succes(String succes, JsonElement jsonElement) {
        String idUser=jsonElement.getAsString();
        Toast.makeText(Registro_PasoUnoActivity.this, succes,Toast.LENGTH_SHORT).show();
        Preferences.setIdClient(this,idUser);
        Intent intent_pasodos = new Intent(Registro_PasoUnoActivity.this, Registro_PasoDosActivity.class);
        startActivity(intent_pasodos);
    }

    @Override
    public void error(String error) {
        Toast.makeText(Registro_PasoUnoActivity.this, error,Toast.LENGTH_LONG).show();
    }
}
