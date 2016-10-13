package com.aosas.audismart.util.alarm;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.aosas.audismart.model.Notificacion;
import com.aosas.audismart.repository.Preferences;
import com.aosas.audismart.util.Constantes;

import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by Lmartinez on 21/04/2016.
 */
public class AlarmTask extends BroadcastReceiver {
    // The date selected for the alarm
    //private final Calendar date;
    // The android system alarm manager
    private  AlarmManager am;
    // Your context to retrieve the alarm manager from
    private  Context context;






    @Override
    public void onReceive(Context context, Intent intent) {
        this.context = context;
        this.am = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        if(Preferences.getNotificaciones(context)!=null) {
            ArrayList<Notificacion> notificaciones = Preferences.getNotificaciones(context);
            for (int index = 0; index < notificaciones.size(); index++) {
                Intent i = new Intent(context, NotifyService.class);
                i.putExtra(NotifyService.INTENT_NOTIFY, true);
                i.putExtra(Constantes.EXTRA_NOTIFICACIONES, notificaciones.get(index));
                PendingIntent pendingIntent = PendingIntent.getService(context, index, intent, 0);
                am.set(AlarmManager.RTC, notificaciones.get(index).calendar.getTimeInMillis(), pendingIntent);
            }
        }
    }
}
