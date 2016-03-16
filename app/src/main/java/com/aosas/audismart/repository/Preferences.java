package com.aosas.audismart.repository;

import android.content.Context;
import android.content.SharedPreferences;

import com.aosas.audismart.util.Constantes;

/**
 * Created by Lmartinez on 21/01/2016.
 */
public class Preferences {

    private static SharedPreferences getSharedPreferences(Context context) {
        return context.getSharedPreferences("AudiSmart", Context.MODE_PRIVATE);
    }

    public static void setSession(Context context, Boolean sesion) {
        SharedPreferences.Editor editor = Preferences.getSharedPreferences(context).edit();
        if(sesion != null)
            editor.putBoolean(Constantes.SESION,sesion);
        else
            editor.remove(Constantes.SESION);
        editor.commit();
    }

    public static Boolean getSesion(Context context) {
        SharedPreferences sharedPref =  Preferences.getSharedPreferences(context);
       return sharedPref.getBoolean(Constantes.SESION, false);
    }


    public static void setIdClient(Context context, String id) {
        SharedPreferences.Editor editor = Preferences.getSharedPreferences(context).edit();
        if(id != null)
            editor.putString(Constantes.IDCLIENT, id);
        else
            editor.remove(Constantes.IDCLIENT);
        editor.commit();
    }

    public static String getClient(Context context) {
        SharedPreferences sharedPref =  Preferences.getSharedPreferences(context);
        return sharedPref.getString(Constantes.IDCLIENT, "");
    }

    public static void setTokenGcm(Context context, String token) {
        SharedPreferences.Editor editor = Preferences.getSharedPreferences(context).edit();
        if(token != null)
            editor.putString(Constantes.SENT_TOKEN_TO_SERVER, token);
        else
            editor.remove(Constantes.SENT_TOKEN_TO_SERVER);
        editor.commit();
    }

    public static String getTokenGcm(Context context) {
        SharedPreferences sharedPref =  Preferences.getSharedPreferences(context);
        return sharedPref.getString(Constantes.SENT_TOKEN_TO_SERVER, "");
    }
}
