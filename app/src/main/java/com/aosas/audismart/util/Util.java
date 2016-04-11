package com.aosas.audismart.util;

import android.os.Build;
import android.text.TextUtils;
import java.security.MessageDigest;
import com.aosas.audismart.model.Categoria;
import com.aosas.audismart.model.Ciudad;
import com.aosas.audismart.model.Departamento;
import com.aosas.audismart.model.DocumentoIdentidad;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

/**
 * Created by lmartinez on 14/12/2015.
 */
public class Util {


    /*
    Convierte json en Arrays
     */
    public static ArrayList jsontoArrayList(String json, Object object) {
        if (object instanceof Departamento) {
            Gson gson = new Gson();
            Type type = new TypeToken<List<Departamento>>() {
            }.getType();
            ArrayList<Departamento> departamentos = gson.fromJson(json, type);

            return departamentos;
        } else if (object instanceof Ciudad) {
            Gson gson = new Gson();
            Type type = new TypeToken<List<Ciudad>>() {
            }.getType();
            ArrayList<Ciudad> ciudades = gson.fromJson(json, type);
            return ciudades;
        } else if (object instanceof Categoria) {
            Gson gson = new Gson();
            Type type = new TypeToken<List<Categoria>>() {
            }.getType();
            ArrayList<Categoria> categorias = gson.fromJson(json, type);

            return categorias;
        } else if (object instanceof DocumentoIdentidad) {
            Gson gson = new Gson();
            Type type = new TypeToken<List<DocumentoIdentidad>>() {
            }.getType();
            ArrayList<DocumentoIdentidad> documentos = gson.fromJson(json, type);
            return documentos;
        }
        return null;
    }

    /*
    Se obtiene un ND5 a partir de un texto
     */
    public static String textToMD5(String text) {
        String original = text;
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        md.update(original.getBytes());
        byte[] digest = md.digest();
        StringBuffer sb = new StringBuffer();
        for (byte b : digest) {
            sb.append(String.format("%02x", b & 0xff));
        }

        return  sb.toString();
    }

    /** Returns the consumer friendly device name */
    public static String getDeviceName() {
        String manufacturer = Build.MANUFACTURER;
        String model = Build.MODEL;
        if (model.startsWith(manufacturer)) {
            return capitalize(model);
        }
        return capitalize(manufacturer) + " " + model;
    }

    private static String capitalize(String str) {
        if (TextUtils.isEmpty(str)) {
            return str;
        }
        char[] arr = str.toCharArray();
        boolean capitalizeNext = true;
        String phrase = "";
        for (char c : arr) {
            if (capitalizeNext && Character.isLetter(c)) {
                phrase += Character.toUpperCase(c);
                capitalizeNext = false;
                continue;
            } else if (Character.isWhitespace(c)) {
                capitalizeNext = true;
            }
            phrase += c;
        }
        return phrase;
    }

    public static Date stringToDate(String fecha){
        SimpleDateFormat formatter = new SimpleDateFormat(Constantes.FORMATOFECHANOTIDICACIONJSON, Locale.US);
        formatter.setTimeZone(TimeZone.getTimeZone("GMT-5:00"));
        try {
            Date date = (Date)formatter.parse(fecha);
            return date;
        } catch (ParseException e) {
         return null;
        }
    }
}
