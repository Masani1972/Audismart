package com.aosas.audismart.util;

import android.util.Log;

import com.aosas.audismart.model.Categoria;
import com.aosas.audismart.model.Ciudad;
import com.aosas.audismart.model.Departamento;
import com.aosas.audismart.model.DocumentoIdentidad;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by lmartinez on 14/12/2015.
 */
public class Util {

    public void solicitar_Contraseña(){

    }

    public static ArrayList jsontoArrayList(String json, Object object){
        if(object instanceof  Departamento){
        Gson gson = new Gson();
        Type type = new TypeToken<List<Departamento>>(){}.getType();
            ArrayList<Departamento> departamentos = gson.fromJson(json, type);

            return departamentos;
        }
        else if(object instanceof Ciudad){
            Gson gson = new Gson();
            Type type = new TypeToken<List<Ciudad>>(){}.getType();
            ArrayList<Ciudad> ciudades = gson.fromJson(json, type);
            return ciudades;
        }
        else if(object instanceof Categoria){
            Gson gson = new Gson();
            Type type = new TypeToken<List<Categoria>>(){}.getType();
            ArrayList<Categoria> categorias = gson.fromJson(json, type);

            return categorias;
        }
        else if(object instanceof DocumentoIdentidad){
            Gson gson = new Gson();
            Type type = new TypeToken<List<DocumentoIdentidad>>(){}.getType();
            ArrayList<DocumentoIdentidad> documentos = gson.fromJson(json, type);
            return documentos;
        }
return  null;
    }

    public static String textToMD5 (String text){
        try {
            // Create MD5 Hash
            MessageDigest digest = java.security.MessageDigest.getInstance("MD5");
            digest.update(text.getBytes());
            byte messageDigest[] = digest.digest();

            // Create Hex String
            StringBuffer hexString = new StringBuffer();
            for (int i=0; i<messageDigest.length; i++)
                hexString.append(Integer.toHexString(0xFF & messageDigest[i]));
            return hexString.toString();

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }

}
