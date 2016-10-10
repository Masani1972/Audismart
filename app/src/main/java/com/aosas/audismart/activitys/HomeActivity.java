package com.aosas.audismart.activitys;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

import com.aosas.audismart.R;
import com.aosas.audismart.gcm.RegistrationIntentService;
import com.aosas.audismart.repository.Preferences;
import com.aosas.audismart.util.Constantes;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.gson.JsonElement;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class HomeActivity extends AppCompatActivity implements BaseActivity{

    private static final int PLAY_SERVICES_RESOLUTION_REQUEST = 9000;

    private BroadcastReceiver mRegistrationBroadcastReceiver;
    private boolean isReceiverRegistered;
    private static final String TAG="HomeActivity";

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

        registrar_gcm();
    }


    @OnClick(R.id.button_Ingresar)
    public void ingresar(View view) {
        Intent intent_ingreso = new Intent(HomeActivity.this, IngresoActivity.class);
        startActivity(intent_ingreso);
    }

    @OnClick(R.id.button_Registro)
    public void registrar(View view) {
        Intent intent_registro = new Intent(HomeActivity.this, Registro_UsuarioActivity.class);
        startActivity(intent_registro);
    }

    @OnClick(R.id.button_Contrasena)
    public void solicitar_Password(View view) {
        solicitar_Contrasena();
    }

    @Override
    protected void onResume() {
        super.onResume();
        registerReceiver();
    }

    @Override
    protected void onPause() {
        LocalBroadcastManager.getInstance(this).unregisterReceiver(mRegistrationBroadcastReceiver);
        isReceiverRegistered = false;
        super.onPause();
    }


    /*******************
     Presentador¡¡ Logica de la  vista
     *******************/

    /*
    Permite solicitar de nuevo la contraseña,
    olvidada por el usuario
     */
    private void solicitar_Contrasena() {

        startActivity(new Intent(this,PasswordActivity.class));
    }

    public void registrar_gcm(){
        mRegistrationBroadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
              String token=  Preferences.getTokenGcm(HomeActivity.this);
                Log.i(TAG,token );
            }
        };

        // Registering BroadcastReceiver
        registerReceiver();

        if (checkPlayServices()) {
            // Start IntentService to register this application with GCM.
            Intent intent = new Intent(this, RegistrationIntentService.class);
            startService(intent);
        }
    }

    private void registerReceiver(){
        if(!isReceiverRegistered) {
            LocalBroadcastManager.getInstance(this).registerReceiver(mRegistrationBroadcastReceiver,
                    new IntentFilter(Constantes.REGISTRATION_COMPLETE));
            isReceiverRegistered = true;
        }
    }

    /**
     * Check the device to make sure it has the Google Play Services APK. If
     * it doesn't, display a dialog that allows users to download the APK from
     * the Google Play Store or enable it in the device's system settings.
     */
    private boolean checkPlayServices() {
        GoogleApiAvailability apiAvailability = GoogleApiAvailability.getInstance();
        int resultCode = apiAvailability.isGooglePlayServicesAvailable(this);
        if (resultCode != ConnectionResult.SUCCESS) {
            if (apiAvailability.isUserResolvableError(resultCode)) {
                apiAvailability.getErrorDialog(this, resultCode, PLAY_SERVICES_RESOLUTION_REQUEST)
                        .show();
            } else {
                finish();
            }
            return false;
        }
        return true;
    }

    @Override
    public void succes(String succes, JsonElement jsonElement) {


    }

    @Override
    public void error(String error) {

    }


}
