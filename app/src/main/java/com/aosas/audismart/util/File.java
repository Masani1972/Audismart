package com.aosas.audismart.util;

import android.content.Context;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by Lmartinez on 16/01/2016.
 */
public class File {

    //Metodo que se encarga de leer el archivo
    public static String loadJSONFromAsset(Context context, String nombreArchivo) {
        String json = null;
        try {

            InputStream is = context.getAssets().open(nombreArchivo);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;

    }

    //Obtiene la info
    public static String readJsonDescripcion(Context context, String id){
        JSONArray obj;
        String archivo = "ciudades";
        Log.e("number exception", id);


        try {



            JSONArray jsonArray = new JSONArray(loadJSONFromAsset(context,archivo));




            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject c = jsonArray.getJSONObject(i);

               String name= c.getString("name");
                   Log.i("name",name);
                if(c.getString("name").equals(id)){
                    JSONArray jsonArrayc = c.getJSONArray("values");
String s=jsonArrayc.toString();
                    Log.i("s",s);
                    return jsonArrayc.toString();}
            }


//return null;
        } catch (JSONException e) {
            // TODO Auto-generated catch block
            Log.e("error",e.getMessage());

        }
        return null;
    }

    /*
    Convertir info a List
     */
}
