package com.aosas.audismart.gcm;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.NotificationCompat;

import com.aosas.audismart.R;
import com.aosas.audismart.activitys.NoticiasDetalleActivity;
import com.aosas.audismart.model.Noticia;
import com.aosas.audismart.util.Constantes;
import com.google.android.gms.gcm.GcmListenerService;


/**
 * The type Notifications listener service.
 * Servicio que recibe las notificaciones push
 */
public class NotificationsListenerService extends GcmListenerService {

    private static final String TAG = "MyGcmListenerService";
    private static final int NOTIFICATION = 1;


    /**
     * On message received.
     *
     * @param from the from
     * @param data the data
     */
    @Override
    public void onMessageReceived(String from, Bundle data) {
        String message = data.getString("message");
        sendNotification(message);
    }

    private void sendNotification(String message) {
        Intent intent = new Intent(this, NoticiasDetalleActivity.class);
        intent.putExtra(Constantes.EXTRA_NOTICIA, new Noticia("", message, "", "", "", "", "", "", "", ""));
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent,
                PendingIntent.FLAG_ONE_SHOT);

        Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        android.support.v4.app.NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this)
                .setSmallIcon(R.drawable.ic_launcher)
                .setContentTitle("Aosmart")
                .setContentText(message)
                .setAutoCancel(true)
                .setSound(defaultSoundUri)
                .setContentIntent(pendingIntent);

        NotificationManager notificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        notificationManager.notify(NOTIFICATION, notificationBuilder.build());
    }
}