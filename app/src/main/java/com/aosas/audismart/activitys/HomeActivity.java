package com.aosas.audismart.activitys;

import android.content.Intent;
import android.graphics.Paint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.aosas.audismart.R;
import com.aosas.audismart.util.Util;

public class HomeActivity extends AppCompatActivity {
    private Util util;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        //Inicializar Objetos
        util = new Util();

        //GetViews
        Button button = (Button) findViewById(R.id.button_Contrasena);
        button.setPaintFlags(button.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
    }

    /*
    Eventos del todos los botones del layout
     */
    public void click(View view) {
        switch (view.getId()) {
            case R.id.button_Registro:
                Intent intent_registro = new Intent(HomeActivity.this, Registro_PasoUnoActivity.class);
                startActivity(intent_registro);
                break;

            case R.id.button_Ingresar:
                Intent intent_ingreso = new Intent(HomeActivity.this, IngresoActivity.class);
                startActivity(intent_ingreso);
                break;

            case R.id.button_Contrasena:
                solicitar_Contraseña();
                break;
        }
    }

    /*
    Permite solicitar de nuevo la contraseña,
    olvidada por el usuario
     */
    private void solicitar_Contraseña() {
        util.solicitar_Contraseña();
    }
}
