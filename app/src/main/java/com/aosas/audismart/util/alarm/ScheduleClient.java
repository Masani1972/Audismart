package com.aosas.audismart.util.alarm;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.util.Log;

import com.aosas.audismart.model.Notificacion;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Map;

/**
 * Created by Lmartinez on 21/04/2016.
 */
public class ScheduleClient extends BroadcastReceiver {



    @Override
    public void onReceive(Context context, Intent intent) {
        Intent service = new Intent(context, ScheduleService.class);
        context.startService(service);
    }
}