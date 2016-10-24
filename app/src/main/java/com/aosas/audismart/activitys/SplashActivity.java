package com.aosas.audismart.activitys;

import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.aosas.audismart.R;
import com.aosas.audismart.repository.Preferences;


/**
 * The type Splash activity.
 * Pantalla inicial de la aplicación
 */
public class SplashActivity extends AppCompatActivity {
    private PendingIntent pendingIntent;


    /**
     * On create.
     *
     * @param savedInstanceState the saved instance state
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);


        //Tiempo del splash
        Thread timer = new Thread() {
            //El nuevo Thread exige el metodo run
            public void run() {
                try {
                    sleep(5000);

                } catch (InterruptedException e) {

                    e.printStackTrace();
                } finally {
                    //Se llama  a la nueva actividad
                    if (Preferences.getSesion(SplashActivity.this)) {
                        Intent intent = new Intent(SplashActivity.this,
                                MenuPrincipalActivity.class);
                        startActivity(intent);
                    } else {
                        Intent intent = new Intent(SplashActivity.this,
                                HomeActivity.class);
                        startActivity(intent);
                    }

                    finish();
                }
            }
        };
        //se ejecuta el hilo
        timer.start();
    }

}
