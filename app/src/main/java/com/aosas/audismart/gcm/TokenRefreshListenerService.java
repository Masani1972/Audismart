package com.aosas.audismart.gcm;

import android.content.Intent;

import com.google.android.gms.iid.InstanceIDListenerService;


/**
 * The type Token refresh listener service.
 * Gestiona las actualizaciones de los token
 */
public class TokenRefreshListenerService extends InstanceIDListenerService {

    /**
     * On token refresh.
     */
    @Override
    public void onTokenRefresh() {
        Intent intent = new Intent(this, RegistrationIntentService.class);
        startService(intent);
    }
}