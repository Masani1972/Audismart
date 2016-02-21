package com.aosas.audismart.activitys;

import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import com.aosas.audismart.R;
import com.google.gson.JsonElement;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class HomeActivity extends AppCompatActivity implements BaseActivity{

    @InjectView(R.id.button_Contrasena)
    Button button_Contrasena;

    @InjectView(R.id.button_Ingresar)
    Button button_Ingresar;

    @InjectView(R.id.button_Registro)
    Button button_Registro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.inject(this);
        //button_Contrasena.setPaintFlags(button_Contrasena.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
    }

    @OnClick(R.id.button_Ingresar)
    public void ingresar(View view) {
        Intent intent_ingreso = new Intent(HomeActivity.this, IngresoActivity.class);
        startActivity(intent_ingreso);
    }

    @OnClick(R.id.button_Registro)
    public void registrar(View view) {
        Intent intent_registro = new Intent(HomeActivity.this, Registro_PasoUnoActivity.class);
        startActivity(intent_registro);
    }

    @OnClick(R.id.button_Contrasena)
    public void solicitar_Password(View view) {
        solicitar_Contrasena();
    }


    /*******************
     Presentador¡¡ Logica de la  vista
     *******************/

    /*
    Permite solicitar de nuevo la contraseña,
    olvidada por el usuario
     */
    private void solicitar_Contrasena() {


    }

    @Override
    public void succes(String succes, JsonElement jsonElement) {


    }

    @Override
    public void error(String error) {

    }
}
