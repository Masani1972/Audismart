package com.aosas.audismart.activitys;

import android.content.Intent;
import android.graphics.Paint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.aosas.audismart.R;
import com.aosas.audismart.comunication.IRepository;
import com.aosas.audismart.comunication.Repository;
import com.aosas.audismart.model.Login;
import com.aosas.audismart.repository.Preferences;
import com.aosas.audismart.util.Constantes;
import com.aosas.audismart.util.Util;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnCheckedChanged;
import butterknife.OnClick;

public class IngresoActivity extends AppCompatActivity implements BaseActivity{
    private Util util;
    private boolean sesion = false;

    @InjectView(R.id.button_Ingresar)
    Button button_Ingresar;

    @InjectView(R.id.button_Contrasena)
    Button button_Contrasena;

    @InjectView(R.id.editText_Usuario)
    EditText editText_Usuario;

    @InjectView(R.id.editText_Contrasena)
    EditText editText_Contrasena;

    @InjectView(R.id.checkBox_Sesion)
    CheckBox checkBox_Sesion;

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

    @OnCheckedChanged(R.id.checkBox_Sesion)
    public void checkBox_Sesion(boolean value){
        sesion = value;
    }


    /*******************
     Presentador¡¡ Logica de la  vista
     *******************/

    /*
    Metodo para ingreso a la plataforma
    Se autentica consumiendo un servicio web
     */
    private void ingresar() {
        if(validarDatos()){
            if(Util.textToMD5(editText_Contrasena.getText().toString()).length()>0){
                IRepository repository = new Repository();
                Login login = new Login(editText_Usuario.getText().toString(),Util.textToMD5(editText_Contrasena.getText().toString()), Constantes.LOGIN);
                repository.createRequets(this, login, Constantes.LOGIN_API);}}else{
            Toast.makeText(IngresoActivity.this, R.string.formularioIncompleto, Toast.LENGTH_LONG).show();
        }
    }

    private boolean validarDatos() {
        if((editText_Usuario != null & editText_Usuario.getText().toString().length()>0)&&(editText_Contrasena!=null&editText_Contrasena.getText().toString().length()>0))
            return true;
            else
            return false;
    }



    /*
Permite solicitar de nuevo la contraseña,
olvidada por el usuario
 */
    private void solicitar_Contraseña() {
        util.solicitar_Contraseña();
    }

    @Override
    public void succes(String succes, JsonElement jsonElement) {
        JsonObject jsonObject = (JsonObject)jsonElement;
        if(sesion)
            Preferences.setSession(IngresoActivity.this,sesion);
          Toast.makeText(IngresoActivity.this, succes, Toast.LENGTH_SHORT).show();
        Intent intentMenu = new Intent(IngresoActivity.this,MenuActivity.class);
        startActivity(intentMenu);
    }

    @Override
    public void error(String error) {
        Toast.makeText(IngresoActivity.this, error, Toast.LENGTH_LONG).show();
    }
}
