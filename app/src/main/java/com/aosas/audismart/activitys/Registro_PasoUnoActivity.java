package com.aosas.audismart.activitys;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.aosas.audismart.Object.User;
import com.aosas.audismart.R;
import com.aosas.audismart.comunication.APIService;
import com.aosas.audismart.comunication.IPresenter;
import com.aosas.audismart.comunication.Presenter;
import com.aosas.audismart.comunication.ServiceGenerator;
import com.aosas.audismart.comunication.ServiceTaskAsyn;
import com.squareup.okhttp.ResponseBody;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import retrofit.Call;
import retrofit.Response;

public class Registro_PasoUnoActivity extends AppCompatActivity implements BaseActivity{
    RelativeLayout layout_form;
    EditText nombres, apellidos, correo_electronico,departamento,ciudad, telefono,contrasena;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_pasouno);

        //Get Views
        layout_form = (RelativeLayout) findViewById(R.id.layout_Form);
        nombres = (EditText) findViewById(R.id.editText_Nombres);
        apellidos = (EditText) findViewById(R.id.editText_Apellidos);
        correo_electronico = (EditText) findViewById(R.id.editText_Email);
        departamento = (EditText) findViewById(R.id.editText_Departamento);
        ciudad = (EditText) findViewById(R.id.editText_Ciudad);
        telefono = (EditText) findViewById(R.id.editText_Telefono);
        contrasena = (EditText) findViewById(R.id.editText_Contrasena);
    }

    /*
   Eventos del todos los botones del layout
    */
    public void click(View view) {
        switch (view.getId()) {
            case R.id.button_Continuar:
                validar_formulario();
                break;
        }
    }

    /*
    Validar los campos EditText del formulario
    mediante el ciclo onteniendo cada una de las vista y validando la longitud del texto
     */
    private void validar_formulario() {
        int editTextOk = 0;
        int childcount = layout_form.getChildCount();
        for (int i = 0; i < childcount; i++) {
            View v = layout_form.getChildAt(i);
            EditText tv = (EditText) v;
            if ((tv != null && tv.getText().toString().length() > 0))
                editTextOk++;
            continue;
        }

        if (editTextOk == childcount) {
            User user = new User(nombres.getText().toString(),apellidos.getText().toString(),correo_electronico.getText().toString(),departamento.getText().toString(),ciudad.getText().toString(),telefono.getText().toString(),contrasena.getText().toString(),"1","1","REGISTRO CLIENTE");
            IPresenter presenter = new Presenter();
            presenter.createRequets(Registro_PasoUnoActivity.this,user,"createUser");
           // Intent intent_pasodos = new Intent(Registro_PasoUnoActivity.this, Registro_PasoDosActivity.class);
           // startActivity(intent_pasodos);
        } else {
            Toast.makeText(Registro_PasoUnoActivity.this, "Por favor diligenciar el formulario",                  Toast.LENGTH_LONG).show();
        }
    }


    @Override
    public void succes(String succes) {

    }

    @Override
    public void error(String error) {

    }
}
