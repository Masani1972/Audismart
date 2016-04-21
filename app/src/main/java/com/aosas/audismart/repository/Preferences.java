package com.aosas.audismart.repository;

import android.content.Context;
import android.content.SharedPreferences;

import com.aosas.audismart.model.Calendario;
import com.aosas.audismart.model.Empresa;
import com.aosas.audismart.model.Notificacion;
import com.aosas.audismart.util.Constantes;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lmartinez on 21/01/2016.
 */
public class Preferences {

    private static SharedPreferences getSharedPreferences(Context context) {
        return context.getSharedPreferences("AudiSmart", Context.MODE_PRIVATE);
    }

    /*
    Almacena si el usuario eligio guardar la sesion
     */
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


    /*
    Almacenamiento del id del cliente o usuario
     */
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

    /*
   Almacenamiento clientes
    */
    public static void setEmpresas(Context context, ArrayList<Empresa> empresas ) {
        SharedPreferences.Editor editor = Preferences.getSharedPreferences(context).edit();
        if(empresas != null){
            Gson gson = new Gson();
            String json = gson.toJson(empresas);
            editor.putString(Constantes.EMPRESAS, json);}
        else
            editor.remove(Constantes.EMPRESAS);
        editor.commit();
    }

    public static ArrayList<Empresa> getEmpresas(Context context) {
        SharedPreferences sharedPref =  Preferences.getSharedPreferences(context);
        Gson gson = new Gson();
        String json = sharedPref.getString(Constantes.EMPRESAS, "0");
        Type type = new TypeToken<List<Empresa>>() {}.getType();
        ArrayList<Empresa> empresas = gson.fromJson(json, type);
        return empresas;
    }

    /*
    Almacenamiento token de GCM
     */
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

    /*
    Almacenamiento id de la empresa
     */
    public static void setIdCompany(Context context, String id) {
        SharedPreferences.Editor editor = Preferences.getSharedPreferences(context).edit();
        if(id != null)
            editor.putString(Constantes.IDCOMPANY, id);
        else
            editor.remove(Constantes.IDCOMPANY);
        editor.commit();
    }

    public static String getIdCompany(Context context) {
        SharedPreferences sharedPref =  Preferences.getSharedPreferences(context);
        return sharedPref.getString(Constantes.IDCOMPANY, "0");
    }

    /*
    Almacenamiento notificaciones
     */
    public static void setNotificaciones(Context context, ArrayList<Notificacion> notificaciones ) {
        SharedPreferences.Editor editor = Preferences.getSharedPreferences(context).edit();
        if(notificaciones != null){
        Gson gson = new Gson();
        String json = gson.toJson(notificaciones);
            editor.putString(Constantes.NOTIFICACIONES, json);}
        else
            editor.remove(Constantes.NOTIFICACIONES);
        editor.commit();
    }

    public static void clearNotificaciones(Context context) {
        SharedPreferences.Editor editor = Preferences.getSharedPreferences(context).edit();

            editor.remove(Constantes.NOTIFICACIONES);
        editor.commit();
    }

    public static ArrayList<Notificacion> getNotificaciones(Context context) {
        SharedPreferences sharedPref =  Preferences.getSharedPreferences(context);
        Gson gson = new Gson();
        String json = sharedPref.getString(Constantes.NOTIFICACIONES, "0");
        Type type = new TypeToken<List<Notificacion>>() {}.getType();
        ArrayList<Notificacion> notificaciones = gson.fromJson(json, type);
        return notificaciones;
    }

    /*
   Almacenamiento calendarios
    */
    public static void setCalendarios(Context context, ArrayList<Calendario> calendarios ) {
        SharedPreferences.Editor editor = Preferences.getSharedPreferences(context).edit();
        if(calendarios != null){
            Gson gson = new Gson();
            String json = gson.toJson(calendarios);
            editor.putString(Constantes.CALENDARIOS_PREFERENCES, json);}
        else
            editor.remove(Constantes.CALENDARIOS_PREFERENCES);
        editor.commit();
    }



    public static ArrayList<Calendario> getCalendarios(Context context) {
        SharedPreferences sharedPref =  Preferences.getSharedPreferences(context);
        Gson gson = new Gson();
        String json = sharedPref.getString(Constantes.CALENDARIOS_PREFERENCES, "0");
        Type type = new TypeToken<List<Calendario>>() {}.getType();
        ArrayList<Calendario> calendarios = gson.fromJson(json, type);
        return calendarios;
    }
}
