package com.aosas.audismart.util.alarm;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

import com.aosas.audismart.model.Notificacion;
import com.aosas.audismart.util.Constantes;

import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by Lmartinez on 21/04/2016.
 */
public class AlarmTask implements Runnable{
    // The date selected for the alarm
    //private final Calendar date;
    // The android system alarm manager
    private final AlarmManager am;
    // Your context to retrieve the alarm manager from
    private final Context context;

    ArrayList<Notificacion> notificaciones;

    public AlarmTask(Context context, ArrayList<Notificacion> notificaciones) {
        this.context = context;
        this.am = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        //this.date = date;

        this.notificaciones =notificaciones;
    }

    @Override
    public void run() {
        // Request to start are service when the alarm date is upon us
        // We don't start an activity as we just want to pop up a notification into the system bar not a full activity


        // Sets an alarm - note this alarm will be lost if the phone is turned off and on again
        for (int i =0;i<notificaciones.size();i++) {
            Intent intent = new Intent(context, NotifyService.class);
            intent.putExtra(NotifyService.INTENT_NOTIFY, true);
            intent.putExtra(Constantes.EXTRA_NOTIFICACIONES, notificaciones.get(i));
            PendingIntent pendingIntent = PendingIntent.getService(context, i, intent, 0);
            am.set(AlarmManager.RTC, notificaciones.get(i).calendar.getTimeInMillis(), pendingIntent);
        }
    }
}
