package com.aosas.audismart.activitys;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.aosas.audismart.R;
import com.aosas.audismart.adapters.AlertDialogCustom;
import com.aosas.audismart.comunication.IRepository;
import com.aosas.audismart.comunication.Repository;
import com.aosas.audismart.model.Calendario;
import com.aosas.audismart.model.CalendariosCliente;
import com.aosas.audismart.model.ClienteUnico;
import com.aosas.audismart.model.Empresa;
import com.aosas.audismart.model.EmpresasUsuarios;
import com.aosas.audismart.model.Login;
import com.aosas.audismart.model.User;
import com.aosas.audismart.repository.Preferences;
import com.aosas.audismart.util.Constantes;
import com.aosas.audismart.util.Util;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnCheckedChanged;
import butterknife.OnClick;

public class IngresoActivity extends AppCompatActivity implements BaseActivity{
    private Util util;
    private boolean sesion = false;
    private IRepository repository = new Repository();
    private static final String TAG ="IngresoActivity";

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

        //button_Contrasena.setPaintFlags(button_Contrasena.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
    }

    @OnClick(R.id.button_Ingresar)
    public void ingresar(View view) {
        ingresar();
    }

    @OnClick(R.id.button_Contrasena)
    public void solicitar_Contraseña(View view) {
        recuperar_Contraseña();
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
                Login login = new Login(editText_Usuario.getText().toString(),Util.textToMD5(editText_Contrasena.getText().toString()), Constantes.LOGIN);
                repository.createRequets(this, login, Constantes.LOGIN);}}else{
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
    private void recuperar_Contraseña() {
        AlertDialogCustom alertDialog = new AlertDialogCustom(this);
        alertDialog.initAlert("Email", "Ingrese Email");
    }

    @Override
    public void succes(String succes, JsonElement jsonElement) {
        Log.i(TAG,succes);
        if(succes.equals("Login exitoso")){
            JsonObject jsonObject = (JsonObject)jsonElement;
            if(sesion)
                Preferences.setSession(IngresoActivity.this, sesion);
                String idClient = jsonObject.get("id_cliente").toString().replaceAll("\"", "");
                Preferences.setIdClient(this, idClient);
                User user = new User(jsonObject.get("nombres").toString(),jsonObject.get("apellidos").toString(),jsonObject.get("email").toString(),jsonObject.get("id_departamento").toString(),jsonObject.get("id_ciudad").toString(),jsonObject.get("telefono").toString(),"","","","");
                Preferences.setUsuario(this,user);
                EmpresasUsuarios empresasUsuarios = new EmpresasUsuarios(idClient,"0",Constantes.EMPRESAS_RELACIONADA);
                repository.createRequets(this, empresasUsuarios, Constantes.EMPRESAS_RELACIONADA);
        }

        else if(succes.substring(0,20).equals("Empresas encontradas")){
            JsonArray jsonArray =  (JsonArray)jsonElement;
            almacenarEmpresas(jsonArray);
            CalendariosCliente calendariosCliente = new CalendariosCliente(Preferences.getIdClient(this),"0",Constantes.CALENDARIOS);
            repository.createRequets(this, calendariosCliente, Constantes.CALENDARIOS);
        }
        else if(succes.substring(0,23).equals("Calendarios encontrados")){
            JsonArray jsonArray =  (JsonArray)jsonElement;
            almacenarCalendarios(jsonArray);
            //Toast.makeText(IngresoActivity.this, succes, Toast.LENGTH_SHORT).show();
            Intent intentMenu = new Intent(IngresoActivity.this,MenuPrincipalActivity.class);
            startActivity(intentMenu);
        }
    }

    public void almacenarCalendarios(JsonArray result){
        if (result != null) {
            ArrayList<Calendario>  calendarios = new ArrayList<Calendario>();
            for (int i = 0; i < result.size(); i++) {
                JsonParser parser = new JsonParser();
                JsonElement tradeElement = parser.parse(result.get(i).toString());
                JsonObject jsonObject = tradeElement.getAsJsonObject();
                Calendario calendario = new Calendario(jsonObject.get("id_calendario").toString(),jsonObject.get("nombre").toString(),jsonObject.get("nombrecorto").toString(),jsonObject.get("ano").toString());
                calendarios.add(i,calendario);
            }
            Preferences.setCalendarios(this,calendarios);
        }

    }

    public void almacenarEmpresas(JsonArray result){
        if (result != null) {
            ArrayList<Empresa> empresas = new ArrayList<Empresa>();
            for (int i = 0; i < result.size(); i++) {
                JsonParser parser = new JsonParser();
                JsonElement tradeElement = parser.parse(result.get(i).toString());
                JsonObject jsonObject = tradeElement.getAsJsonObject();
                Empresa empresa = new Empresa(jsonObject.get("id_cliente").toString(),jsonObject.get("nombre").toString(),jsonObject.get("id_departamento").toString(),jsonObject.get("id_ciudad").toString(),jsonObject.get("tipo_documento").toString(),jsonObject.get("documento").toString(),jsonObject.get("ingresos").toString(),jsonObject.get("categoria").toString(),jsonObject.get("impuesto_consumo").toString(),jsonObject.get("impuesto_riqueza").toString(),"",jsonObject.get("id_empresa").toString());
                empresas.add(i,empresa);
            }
            Preferences.setEmpresas(this,empresas);
        }

    }

    @Override
    public void error(String error) {
        Toast.makeText(IngresoActivity.this, error, Toast.LENGTH_LONG).show();
    }
}
