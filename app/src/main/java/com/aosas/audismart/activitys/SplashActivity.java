package com.aosas.audismart.activitys;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.aosas.audismart.R;
import com.aosas.audismart.repository.Preferences;

public  class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        //Tiempo del splash
        Thread timer = new Thread(){
            //El nuevo Thread exige el metodo run
            public void run(){
                try{
                    sleep(5000);

                }catch(InterruptedException e){

                    e.printStackTrace();
                }finally{
                    //Se llama  a la nueva actividad
                    if(Preferences.getSesion(SplashActivity.this)) {
                        Intent intent = new Intent(SplashActivity.this,
                                MenuActivity.class);
                        startActivity(intent);
                    }else
                    { Intent intent = new Intent(SplashActivity.this,
                            HomeActivity.class);
                        startActivity(intent);}

                    finish();
                }
            }
        };
        //se ejecuta el hilo
        timer.start();
    }


}
