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

/**
 * The type Home activity.
 * Pantalla inicial de la aplicacion , en la cual el usuario decide si
 * se registra o se autentica
 */
public class HomeActivity extends AppCompatActivity implements BaseActivity {
    private static final int PLAY_SERVICES_RESOLUTION_REQUEST = 9000;
    private BroadcastReceiver mRegistrationBroadcastReceiver;
    private boolean isReceiverRegistered;
    private static final String TAG = "HomeActivity";

    /**
     * The Button contrasena.
     */
    @InjectView(R.id.button_Contrasena)
    Button button_Contrasena;

    /**
     * The Button ingresar.
     */
    @InjectView(R.id.button_Ingresar)
    Button button_Ingresar;

    /**
     * The Button registro.
     */
    @InjectView(R.id.button_Registro)
    Button button_Registro;

    /**
     * On create.
     *
     * @param savedInstanceState the saved instance state
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.inject(this);

        //Registro de dispositivo para notificaciones push
        registrar_gcm();
    }


    /**
     * Ingresar.
     *
     * @param view the view
     */
    @OnClick(R.id.button_Ingresar)
    public void ingresar(View view) {
        Intent intent_ingreso = new Intent(HomeActivity.this, IngresoActivity.class);
        startActivity(intent_ingreso);
    }

    /**
     * Registrar.
     *
     * @param view the view
     */
    @OnClick(R.id.button_Registro)
    public void registrar(View view) {
        Intent intent_registro = new Intent(HomeActivity.this, Registro_UsuarioActivity.class);
        startActivity(intent_registro);
    }

    /**
     * Solicitar password.
     *
     * @param view the view
     */
    @OnClick(R.id.button_Contrasena)
    public void solicitar_Password(View view) {
        solicitar_Contrasena();
    }

    /**
     * On resume.
     */
    @Override
    protected void onResume() {
        super.onResume();
        registerReceiver();
    }

    /**
     * On pause.
     */
    @Override
    protected void onPause() {
        LocalBroadcastManager.getInstance(this).unregisterReceiver(mRegistrationBroadcastReceiver);
        isReceiverRegistered = false;
        super.onPause();
    }


    /*******************
     * Presentador¡¡ Logica de la  vista
     *******************/

    /*
    Permite solicitar de nuevo la contraseña,
    olvidada por el usuario
     */
    private void solicitar_Contrasena() {
        startActivity(new Intent(this, PasswordActivity.class));
    }

    /**
     * Registrar gcm.
     */
    public void registrar_gcm() {
        mRegistrationBroadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                String token = Preferences.getTokenGcm(HomeActivity.this);
                Log.i(TAG, token);
            }
        };
        registerReceiver();
        if (checkPlayServices()) {
            Intent intent = new Intent(this, RegistrationIntentService.class);
            startService(intent);
        }
    }

    private void registerReceiver() {
        if (!isReceiverRegistered) {
            LocalBroadcastManager.getInstance(this).registerReceiver(mRegistrationBroadcastReceiver,
                    new IntentFilter(Constantes.REGISTRATION_COMPLETE));
            isReceiverRegistered = true;
        }
    }


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

    /**
     * Succes.
     *
     * @param succes      the succes
     * @param jsonElement the json element
     */
    @Override
    public void succes(String succes, JsonElement jsonElement) {
    }

    /**
     * Error.
     *
     * @param error the error
     */
    @Override
    public void error(String error) {
    }
}
