package com.aosas.audismart.util.alarm;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

import com.aosas.audismart.R;
import com.aosas.audismart.activitys.NoticiasDetalleActivity;
import com.aosas.audismart.activitys.NotificacionesActivity;
import com.aosas.audismart.model.Noticia;
import com.aosas.audismart.model.Notificacion;
import com.aosas.audismart.util.Constantes;

/**
 * Created by Lmartinez on 21/04/2016.
 */
public class NotifyService extends Service {

    /**
     * Class for clients to access
     */
    public class ServiceBinder extends Binder {
        NotifyService getService() {
            return NotifyService.this;
        }
    }

    // Unique id to identify the notification.
    private static final int NOTIFICATION = 123;
    // Name of an intent extra we can use to identify if this service was started to create a notification
    public static final String INTENT_NOTIFY = "com.blundell.tut.service.INTENT_NOTIFY";
    // The system notification manager
    private NotificationManager mNM;

   private Notificacion notificacion ;

    @Override
    public void onCreate() {
        Log.i("NotifyService", "onCreate()");

        mNM = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i("LocalService", "Received start id " + startId + ": " + intent);

        // If this service was started by out AlarmTask intent then we want to show our notification
        if(intent.getBooleanExtra(INTENT_NOTIFY, false)){
            notificacion = (Notificacion) intent.getSerializableExtra(Constantes.EXTRA_NOTIFICACIONES);
            showNotification();
       }

        // We don't care if this service is stopped as we have already delivered our notification
        return START_NOT_STICKY;
    }

    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    // This is the object that receives interactions from clients
    private final IBinder mBinder = new ServiceBinder();

    /**
     * Creates a notification and shows it in the OS drag-down status bar
     */
    private void showNotification() {
        // This is the 'title' of the notification
        CharSequence title = "AOsmart!!";
        // This is the icon to use on the notification
        int icon = R.drawable.ic_launcher;
        // This is the scrolling text of the notification
        CharSequence text = notificacion.nombre+"\n"+notificacion.nombreEmpresa;
        // What time to show on the notification
        long time = System.currentTimeMillis();


       // Notification notification = new Notification(icon, text, time);

        Intent intent = new Intent(this, NotificacionesActivity.class);
        intent.putExtra(Constantes.EXTRA_NOTIFICACIONES,notificacion);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        // The PendingIntent to launch our activity if the user selects this notification
        PendingIntent contentIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_ONE_SHOT);

        Notification notification = new Notification.Builder(this)
                .setContentTitle(title)
                .setContentText(text)
                .setSmallIcon(icon)
                .setAutoCancel(true)
                .setContentIntent(contentIntent)
                .build();

        // Send the notification to the system.
        mNM.notify(NOTIFICATION, notification);

        // Stop the service when we are finished
        stopSelf();
    }
}
