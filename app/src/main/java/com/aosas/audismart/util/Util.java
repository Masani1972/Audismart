package com.aosas.audismart.util;

import android.util.Log;

import com.aosas.audismart.model.Ciudad;
import com.aosas.audismart.model.Departamento;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONObject;

import java.lang.reflect.Type;
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
        for (Departamento departamento : departamentos){
            Log.i("Contact Details", departamento.Id_departamento + "-" + departamento.Nombre );
        }
            return departamentos;
        }
        else if(object instanceof Ciudad){
            Gson gson = new Gson();
            Type type = new TypeToken<List<Ciudad>>(){}.getType();
            ArrayList<Ciudad> ciudades = gson.fromJson(json, type);
            for (Ciudad ciudad : ciudades){
                Log.i("Contact Details", ciudad.Id_ciudad + "-" + ciudad.Nombre );
            }
            return ciudades;
        }
return  null;

    }

}
