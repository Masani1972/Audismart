package com.aosas.audismart.gcm;

import android.app.IntentService;
import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;

import com.aosas.audismart.R;

import com.aosas.audismart.repository.Preferences;
import com.aosas.audismart.util.Constantes;
import com.google.android.gms.gcm.GoogleCloudMessaging;
import com.google.android.gms.iid.InstanceID;


/**
 * The type Registration intent service.
 */
public class RegistrationIntentService extends IntentService {

    private static final String TAG = "RegIntentService";

    /**
     * Instantiates a new Registration intent service.
     */
    public RegistrationIntentService() {
        super(TAG);
    }

    /**
     * On handle intent.
     *
     * @param intent the intent
     */
    @Override
    protected void onHandleIntent(Intent intent) {
        try {
            InstanceID instanceID = InstanceID.getInstance(this);
            String token = instanceID.getToken(getString(R.string.gcm_defaultSenderId),
                    GoogleCloudMessaging.INSTANCE_ID_SCOPE, null);
            Preferences.setTokenGcm(this, token);
        } catch (Exception e) {
            Preferences.setTokenGcm(this, "");
        }
        Intent registrationComplete = new Intent(Constantes.REGISTRATION_COMPLETE);
        LocalBroadcastManager.getInstance(this).sendBroadcast(registrationComplete);
    }
}