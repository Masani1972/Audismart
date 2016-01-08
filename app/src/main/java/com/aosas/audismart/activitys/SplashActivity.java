package com.aosas.audismart.activitys;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.aosas.audismart.R;

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
                    //Llamo a la nueva actividad
                    Intent intent = new Intent(SplashActivity.this,
                            HomeActivity.class);
                    startActivity(intent);

                    finish();
                }
            }
        };
        //se ejecuta el hilo
        timer.start();
    }


}
