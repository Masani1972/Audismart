package com.aosas.audismart.comunication;

import android.app.Activity;
import android.content.Context;

import com.aosas.audismart.activitys.IngresoActivity;
import com.aosas.audismart.activitys.Registro_PasoDosActivity;
import com.aosas.audismart.activitys.Registro_PasoUnoActivity;
import com.aosas.audismart.util.Constantes;
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
        case Constantes.REGISTRO_USUARIO:
            if(errorCodigo.equals("0"))
                ((Registro_PasoUnoActivity) activity).succes((jsonObject.get("message")).getAsString(), (jsonObject.get("id_cliente")));
            else
                ((Registro_PasoUnoActivity) activity).error((jsonObject.get("message")).getAsString());
        break;
            case Constantes.LOGIN:
                if(errorCodigo.equals("0"))
                    ((IngresoActivity) activity).succes((jsonObject.get("message")).getAsString(), (jsonObject.get("datos")));
                else
                    ((IngresoActivity) activity).error((jsonObject.get("message")).getAsString());
                break;
            case Constantes.REGISTRO_EMPRESA:
                if(errorCodigo.equals("0"))
                    ((Registro_PasoDosActivity) activity).succes((jsonObject.get("message")).getAsString(), (jsonObject.get("id_empresa")));
                else
                    ((Registro_PasoDosActivity) activity).error((jsonObject.get("message")).getAsString());
                break;
            case Constantes.REGISTRO_DISPOSITIVO:
                if(errorCodigo.equals("0"))
                    ((Registro_PasoDosActivity) activity).succes((jsonObject.get("message")).getAsString(), (jsonObject.get("id_dispositivo")));
                else
                    ((Registro_PasoDosActivity) activity).error((jsonObject.get("message")).getAsString());
                break;
        }
    }
}
