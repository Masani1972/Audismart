package com.aosas.audismart.gcm;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.NotificationCompat;
import android.util.Log;

import com.aosas.audismart.R;
import com.aosas.audismart.activitys.MenuPrincipalActivity;
import com.aosas.audismart.activitys.NoticiasDetalleActivity;
import com.aosas.audismart.model.Noticia;
import com.google.android.gms.gcm.GcmListenerService;

/**
 * Created by lmartinez on 14/03/2016.
 */
public class NotificationsListenerService  extends GcmListenerService {

    private static final String TAG = "MyGcmListenerService";
    private static final int NOTIFICATION = 1;


    @Override
    public void onMessageReceived(String from, Bundle data) {
        String message = data.getString("message");
        Log.d(TAG, "From: " + from);
        Log.d(TAG, "Message: " + message);

        sendNotification(message);
    }

    private void sendNotification(String message) {
        Intent intent = new Intent(this, NoticiasDetalleActivity.class);
        intent.putExtra("noticia",new Noticia("",message,"","","","","","","",""));
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0 , intent,
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