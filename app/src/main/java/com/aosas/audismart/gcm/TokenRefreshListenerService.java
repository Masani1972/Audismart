package com.aosas.audismart.gcm;

import android.content.Intent;

import com.google.android.gms.iid.InstanceIDListenerService;

/**
 * Created by lmartinez on 14/03/2016.
 */
public class TokenRefreshListenerService extends InstanceIDListenerService {

    private static final String TAG = "MyInstanceIDLS";


    @Override
    public void onTokenRefresh() {
        Intent intent = new Intent(this, RegistrationIntentService.class);
        startService(intent);
    }
}