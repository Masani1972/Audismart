package com.aosas.audismart.activitys;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.aosas.audismart.R;
import com.aosas.audismart.repository.Preferences;
import com.aosas.audismart.util.AlarmReceiver;

import java.util.Calendar;

public  class SplashActivity extends AppCompatActivity {
    private PendingIntent pendingIntent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        initAlarma();

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
                                MenuPrincipalActivity.class);
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

    private void initAlarma() {



        Calendar cal=Calendar.getInstance();
        cal.set(Calendar.MONTH,04);
        cal.set(Calendar.YEAR,2016);
        cal.set(Calendar.DAY_OF_MONTH,21);
        cal.set(Calendar.HOUR_OF_DAY,15);
        cal.set(Calendar.MINUTE,20);

        Calendar current = Calendar.getInstance();
        if(cal.compareTo(current) <= 0){
            //The set Date/Time already passed
            Toast.makeText(getApplicationContext(),
                    "Invalid Date/Time",
                    Toast.LENGTH_LONG).show();
        }else{
            // setAlarm(cal);
        }



    }

    public void setAlarm(Calendar cal){
        Intent intent = new Intent(getBaseContext(), AlarmReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(getBaseContext(), 1, intent, 0);
        AlarmManager alarmManager = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
        alarmManager.set(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(), pendingIntent);
    }


}
