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
       return sharedPref.getBoolean(Constantes.SESION,false);
    }
}
