package com.aosas.audismart.util.alarm;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

import com.aosas.audismart.model.Notificacion;
import com.aosas.audismart.repository.Preferences;
import com.aosas.audismart.util.Constantes;
import com.aosas.audismart.util.Util;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;

/**
 * Created by Lmartinez on 21/04/2016.
 */
public class ScheduleService extends Service {
    // This is the object that receives interactions from clients. See
    private final IBinder mBinder = new ServiceBinder();

    /**
     * Class for clients to access
     */
    public class ServiceBinder extends Binder {
        ScheduleService getService() {
            return ScheduleService.this;
        }
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (Preferences.getNotificaciones(this) !=null) {
            ArrayList<Notificacion> notificaciones = Preferences.getNotificaciones(this);


            for (int i = 0; i < notificaciones.size(); i++) {
                Log.i("notificacion", notificaciones.get(i).id+"hora "+notificaciones.get(i).antesHora);

                String fecha = notificaciones.get(i).antesFecha;
                Calendar cal = Calendar.getInstance();
                Date date = Util.stringToDate(Constantes.FORMATOFECHANOTIDICACIONJSONNOTIFICACION, fecha);
                cal.setTime(date);
                notificaciones.get(i).calendar = cal;
            }
            Preferences.setNotificaciones(this,notificaciones);
            // scheduleClient.setAlarmForNotification(notificaciones);
        }
        return START_NOT_STICKY;
    }

    @Override
    public IBinder onBind(Intent intent) {
        Log.d("bound", "bound");
        return mBinder;
    }




}