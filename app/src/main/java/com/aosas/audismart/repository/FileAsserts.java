package com.aosas.audismart.repository;

import android.content.Context;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;


/**
 * The type File asserts.
 * Lee y obtiene todos los archivos
 * de los assets
 */
public class FileAsserts {

    /**
     * Load json from asset string.
     * Metodo que se encarga de leer el archivo
     *
     * @param context       the context
     * @param nombreArchivo the nombre archivo
     * @return the string
     */
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

    /**
     * Read json descripcion string.
     * Obtiene la informacion especifica del archivo
     * @param context the context
     * @param id      the id
     * @return the string
     */
    public static String readJsonDescripcion(Context context, String id) {
        JSONArray obj;
        String archivo = "ciudades";
        //  Log.e("number exception", id);
        try {
            JSONArray jsonArray = new JSONArray(loadJSONFromAsset(context, archivo));
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject c = jsonArray.getJSONObject(i);

                String name = c.getString("name");
                if (c.getString("name").equals(id)) {
                    JSONArray jsonArrayc = c.getJSONArray("values");
                    return jsonArrayc.toString();
                }
            }

        } catch (JSONException e) {
        }
        return null;
    }
}


