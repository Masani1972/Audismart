package com.aosas.audismart.activitys;

import android.graphics.Paint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.aosas.audismart.R;
import com.aosas.audismart.util.Util;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class IngresoActivity extends AppCompatActivity {
    private Util util;

    @InjectView(R.id.button_Ingresar)
    Button button_Ingresar;

    @InjectView(R.id.button_Contrasena)
    Button button_Contrasena;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingreso);
        ButterKnife.inject(this);

        button_Contrasena.setPaintFlags(button_Contrasena.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
    }

    @OnClick(R.id.button_Ingresar)
    public void ingresar(View view) {
        ingresar();
    }

    @OnClick(R.id.button_Contrasena)
    public void solicitar_Contraseña(View view) {
        solicitar_Contraseña();
    }


    /*
    Metodo para ingreso a la plataforma
    Se autentica consumiendo un servicio web
     */
    private void ingresar() {

    }

    /*
Permite solicitar de nuevo la contraseña,
olvidada por el usuario
 */
    private void solicitar_Contraseña() {
        util.solicitar_Contraseña();
    }
}
