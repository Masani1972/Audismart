package com.aosas.audismart.activitys;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.aosas.audismart.model.Ciudad;
import com.aosas.audismart.model.Departamento;
import com.aosas.audismart.model.User;
import com.aosas.audismart.R;
import com.aosas.audismart.comunication.IPresenter;
import com.aosas.audismart.comunication.Presenter;
import com.aosas.audismart.util.Constants;
import com.aosas.audismart.util.File;
import com.aosas.audismart.util.Util;

import org.json.JSONObject;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class Registro_PasoUnoActivity extends AppCompatActivity implements BaseActivity{

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

    @InjectView(R.id.button_Continuar)
    Button button_Continuar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_pasouno);
        ButterKnife.inject(this);
    }


    @OnClick(R.id.button_Continuar)
    public void continuar(View view) {
        validar_formulario();
    }

    @OnClick(R.id.editText_Departamento)
    public void cargarDepartamentos(View view){
        cargarListaDepartamentos();
    }

    @OnClick(R.id.editText_Ciudad)
    public void cargarCiudades(View view){
        cargarListaCiudades("3");
    }


    /*
    Real presentador¡¡ Logica vista
     */

    /*
    Solicita la lista de departamento al repositorio local
     */
    private void cargarListaDepartamentos() {
        ArrayList arrayListDepartamentos = Util.jsontoArrayList(File.loadJSONFromAsset(this,"departamentos"), new Departamento());
        String[] departamentos = new String[arrayListDepartamentos.size()];
        for(int i = 0;i<arrayListDepartamentos.size();i++){
            Departamento departamento = (Departamento) arrayListDepartamentos.get(i);
            departamentos[i] = departamento.Nombre;
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>
                (this, android.R.layout.select_dialog_item, departamentos);
        editText_Departamento.setAdapter(adapter);
    }

    /*
    Solicita la lista de ciudades al repositorio local de acuerdo al departamento
     */
    private void cargarListaCiudades(String departamento) {
        ArrayList arrayListCiudades= Util.jsontoArrayList(File.readJsonDescripcion(this, departamento),new Ciudad());
        String[] ciudades = new String[arrayListCiudades.size()];
        for(int i = 0;i<arrayListCiudades.size();i++){
            Ciudad ciudad = (Ciudad) arrayListCiudades.get(i);
            ciudades[i] = ciudad.Nombre;
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>
                (this, android.R.layout.select_dialog_item, ciudades);
        editText_Ciudad.setAdapter(adapter);

    }

    /*
    Validar los campos EditText del formulario
    mediante el ciclo onteniendo cada una de las vista y validando la longitud del texto
     */
    private void validar_formulario() {
        int editTextOk = 0;
        int childcount = layout_Form.getChildCount();
        for (int i = 0; i < childcount; i++) {
            View v = layout_Form.getChildAt(i);
            EditText tv = (EditText) v;
            if ((tv != null && tv.getText().toString().length() > 0))
                editTextOk++;
            continue;
        }

        if (editTextOk == childcount) {
            User user = new User(editText_Nombres.getText().toString(),editText_Apellidos.getText().toString(),editText_Email.getText().toString(),editText_Departamento.getText().toString(),editText_Ciudad.getText().toString(),editText_Telefono.getText().toString(),editText_Contrasena.getText().toString(),"1","1", Constants.REGISTRO_USUARIO);
            IPresenter presenter = new Presenter();
            presenter.createRequets(Registro_PasoUnoActivity.this,user,"createUser");
           // Intent intent_pasodos = new Intent(Registro_PasoUnoActivity.this, Registro_PasoDosActivity.class);
           // startActivity(intent_pasodos);
        } else {
            Toast.makeText(Registro_PasoUnoActivity.this, R.string.formularioIncompleto,Toast.LENGTH_LONG).show();
        }
    }




    @Override
    public void succes(String succes) {

    }

    @Override
    public void error(String error) {

    }
}
