package com.aosas.audismart.comunication.proxy;

import android.app.Activity;
import android.content.Context;

import com.aosas.audismart.activitys.IngresoActivity;
import com.aosas.audismart.activitys.MenuPrincipalActivity;
import com.aosas.audismart.activitys.NotificacionesActivity;
import com.aosas.audismart.activitys.Registro_EmpresaActivity;
import com.aosas.audismart.activitys.Registro_UsuarioActivity;
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
                ((Registro_UsuarioActivity) activity).succes((jsonObject.get("message")).getAsString(), (jsonObject.get("id_cliente")));
            else
                ((Registro_UsuarioActivity) activity).error((jsonObject.get("message")).getAsString());
                break;
            case Constantes.LOGIN:
                if(errorCodigo.equals("0"))
                    ((IngresoActivity) activity).succes((jsonObject.get("message")).getAsString(), (jsonObject.get("datos")));
                else
                    ((IngresoActivity) activity).error((jsonObject.get("message")).getAsString());
                break;
            case Constantes.REGISTRO_EMPRESA:
                if(errorCodigo.equals("0"))
                    ((Registro_EmpresaActivity) activity).succes((jsonObject.get("message")).getAsString(), (jsonObject.get("id_empresa")));
                else
                    ((Registro_EmpresaActivity) activity).error((jsonObject.get("message")).getAsString());
                break;
            case Constantes.REGISTRO_DISPOSITIVO:
                if(errorCodigo.equals("0"))
                    ((IngresoActivity) activity).succes((jsonObject.get("message")).getAsString(), null);
                else
                    ((IngresoActivity) activity).error((jsonObject.get("message")).getAsString());
                break;
            case Constantes.REGISTRO_DISPOSITIVO_REGISTRO:
                if(errorCodigo.equals("0"))
                    ((Registro_UsuarioActivity) activity).succes((jsonObject.get("message")).getAsString(), (jsonObject.get("id_dispositivo")));
                else
                    ((Registro_UsuarioActivity) activity).error((jsonObject.get("message")).getAsString());
                break;
            case Constantes.CONSULTA_FECHASCLIENTE:
                if(errorCodigo.equals("0"))
                    ((MenuPrincipalActivity) activity).succes((jsonObject.get("message")).getAsString(), (jsonObject.get("datos")).getAsJsonArray());
                else
                    ((MenuPrincipalActivity) activity).error((jsonObject.get("message")).getAsString());
                break;
            case Constantes.ACTUALIZA_NOTIFICACION:
                if(errorCodigo.equals("0"))
                    ((NotificacionesActivity) activity).succes((jsonObject.get("message")).getAsString(),null);
                else
                    ((NotificacionesActivity) activity).error((jsonObject.get("message")).getAsString());
                break;
            case Constantes.EMPRESAS_RELACIONADA:

                if(errorCodigo.equals("0"))
                    ((IngresoActivity) activity).succes((jsonObject.get("message")).getAsString(),(jsonObject.get("datos")).getAsJsonArray());
                else
                    ((IngresoActivity) activity).error((jsonObject.get("message")).getAsString());
                break;

            case Constantes.CALENDARIOS:

                if(errorCodigo.equals("0"))
                    ((IngresoActivity) activity).succes((jsonObject.get("message")).getAsString(),(jsonObject.get("datos")).getAsJsonArray());
                else
                    ((IngresoActivity) activity).error((jsonObject.get("message")).getAsString());
                break;
            case Constantes.NOTIFICACIONES_CUMPLIO:

                if(errorCodigo.equals("0"))
                    ((MenuPrincipalActivity) activity).succes((jsonObject.get("message")).getAsString(),null);
                else
                    ((MenuPrincipalActivity) activity).error((jsonObject.get("message")).getAsString());
                break;
            case Constantes.BUSCAR_CLIENTE_UNICO:

                if(errorCodigo.equals("0"))
                    ((MenuPrincipalActivity) activity).succes((jsonObject.get("message")).getAsString(),(jsonObject.get("datos")).getAsJsonObject());
                else
                    ((MenuPrincipalActivity) activity).error((jsonObject.get("message")).getAsString());
                break;
            case Constantes.ACTUALIZA_EMPRESA:

                if(errorCodigo.equals("0"))
                    ((Registro_EmpresaActivity) activity).succes((jsonObject.get("message")).getAsString(),null);
                else
                    ((Registro_EmpresaActivity) activity).error((jsonObject.get("message")).getAsString());
                break;
            case Constantes.ACTUALIZA_CLIENTE:

                if(errorCodigo.equals("0"))
                    ((MenuPrincipalActivity) activity).succes((jsonObject.get("message")).getAsString(),null);
                else
                    ((MenuPrincipalActivity) activity).error((jsonObject.get("message")).getAsString());
                break;
        }
    }
}
