package com.aosas.audismart.comunication;

import android.app.Activity;
import android.content.Context;

import com.aosas.audismart.activitys.IngresoActivity;
import com.aosas.audismart.activitys.Registro_PasoUnoActivity;
import com.google.gson.*;

/**
 * Created by Lmartinez on 08/01/2016.
 */
public class Repository implements IRepository {



    @Override
    public void createRequets(Context context,Object object, String metodo) {
        new ServiceTaskAsyn(context,metodo,object).execute();
    }

    @Override
    public void createResponse(String response,String metodo,Activity activity) {
        JsonObject jsonObject = new JsonParser().parse(response).getAsJsonObject();
        String errorCodigo = (jsonObject.get("error")).getAsString();
        switch (metodo){
        case "createUser":
            if(errorCodigo.equals("0"))
                ((Registro_PasoUnoActivity) activity).succes((jsonObject.get("message")).getAsString(), (jsonObject.get("id_cliente")));
            if(!errorCodigo.equals("0"))
                ((Registro_PasoUnoActivity) activity).error((jsonObject.get("message")).getAsString());
        break;
            case "loginUser":
                if(errorCodigo.equals("0"))
                    ((IngresoActivity) activity).succes((jsonObject.get("message")).getAsString(), (jsonObject.get("datos")));
                if(!errorCodigo.equals("0"))
                    ((IngresoActivity) activity).error((jsonObject.get("message")).getAsString());
                break;
        }
    }
}
