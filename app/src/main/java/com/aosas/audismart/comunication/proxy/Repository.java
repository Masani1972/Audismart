package com.aosas.audismart.comunication.proxy;

import android.app.Activity;
import android.content.Context;

import com.aosas.audismart.activitys.CalificarTicketActivity;
import com.aosas.audismart.activitys.CrearTicketActivity;
import com.aosas.audismart.activitys.IngresoActivity;
import com.aosas.audismart.activitys.MenuPrincipalActivity;
import com.aosas.audismart.activitys.NoticiasActivity;
import com.aosas.audismart.activitys.NotificacionesActivity;
import com.aosas.audismart.activitys.PasswordActivity;
import com.aosas.audismart.activitys.Registro_EmpresaActivity;
import com.aosas.audismart.activitys.Registro_UsuarioActivity;
import com.aosas.audismart.activitys.TicketActivity;
import com.aosas.audismart.util.Constantes;
import com.google.gson.*;


/**
 * The type Repository.
 * Clase utilizada para direccinar el response de cada servicio web
 */
public class Repository implements IRepository {


    /**
     * Create requets.
     *
     * @param context the context
     * @param object  the object
     * @param metodo  the metodo
     */
    @Override
    public void createRequets(Context context, Object object, String metodo) {
        new ServiceTaskAsyn(context, metodo, object).execute();
    }

    /**
     * Create response.
     *
     * @param response the response
     * @param metodo   the metodo
     * @param activity the activity
     */
    @Override
    public void createResponse(String response, String metodo, Activity activity) {
        JsonObject jsonObject = new JsonParser().parse(response).getAsJsonObject();
        String errorCodigo = (jsonObject.get("error")).getAsString();
        switch (metodo) {
            case Constantes.REGISTRO_USUARIO:
                if (errorCodigo.equals("0"))
                    ((Registro_UsuarioActivity) activity).succes((jsonObject.get("message")).getAsString(), (jsonObject.get("id_cliente")));
                else
                    ((Registro_UsuarioActivity) activity).error((jsonObject.get("message")).getAsString());
                break;
            case Constantes.LOGIN:
                if (errorCodigo.equals("0"))
                    ((IngresoActivity) activity).succes((jsonObject.get("message")).getAsString(), (jsonObject.get("datos")));
                else
                    ((IngresoActivity) activity).error((jsonObject.get("message")).getAsString());
                break;
            case Constantes.REGISTRO_EMPRESA:
                if (errorCodigo.equals("0"))
                    ((Registro_EmpresaActivity) activity).succes((jsonObject.get("message")).getAsString(), (jsonObject.get("id_empresa")));
                else
                    ((Registro_EmpresaActivity) activity).error((jsonObject.get("message")).getAsString());
                break;
            case Constantes.REGISTRO_DISPOSITIVO:
                if (errorCodigo.equals("0"))
                    ((IngresoActivity) activity).succes((jsonObject.get("message")).getAsString(), null);
                else
                    ((IngresoActivity) activity).error((jsonObject.get("message")).getAsString());
                break;
            case Constantes.REGISTRO_DISPOSITIVO_REGISTRO:
                if (errorCodigo.equals("0"))
                    ((Registro_UsuarioActivity) activity).succes((jsonObject.get("message")).getAsString(), (jsonObject.get("id_dispositivo")));
                else
                    ((Registro_UsuarioActivity) activity).error((jsonObject.get("message")).getAsString());
                break;
            case Constantes.CONSULTA_FECHASCLIENTE:
                if (errorCodigo.equals("0"))
                    ((MenuPrincipalActivity) activity).succes((jsonObject.get("message")).getAsString(), (jsonObject.get("datos")).getAsJsonArray());
                else
                    ((MenuPrincipalActivity) activity).error((jsonObject.get("message")).getAsString());
                break;
            case Constantes.ACTUALIZA_NOTIFICACION:
                if (errorCodigo.equals("0"))
                    ((NotificacionesActivity) activity).succes((jsonObject.get("message")).getAsString(), null);
                else
                    ((NotificacionesActivity) activity).error((jsonObject.get("message")).getAsString());
                break;
            case Constantes.EMPRESAS_RELACIONADA:

                if (errorCodigo.equals("0"))
                    ((IngresoActivity) activity).succes((jsonObject.get("message")).getAsString(), (jsonObject.get("datos")).getAsJsonArray());
                else
                    ((IngresoActivity) activity).error((jsonObject.get("message")).getAsString());
                break;

            case Constantes.CALENDARIOS:

                if (errorCodigo.equals("0"))
                    ((IngresoActivity) activity).succes((jsonObject.get("message")).getAsString(), (jsonObject.get("datos")).getAsJsonArray());
                else
                    ((IngresoActivity) activity).error((jsonObject.get("message")).getAsString());
                break;
            case Constantes.NOTIFICACIONES_CUMPLIO:

                if (errorCodigo.equals("0"))
                    ((MenuPrincipalActivity) activity).succes((jsonObject.get("message")).getAsString(), null);
                else
                    ((MenuPrincipalActivity) activity).error((jsonObject.get("message")).getAsString());
                break;
            case Constantes.BUSCAR_CLIENTE_UNICO:

                if (errorCodigo.equals("0"))
                    ((MenuPrincipalActivity) activity).succes((jsonObject.get("message")).getAsString(), (jsonObject.get("datos")).getAsJsonObject());
                else
                    ((MenuPrincipalActivity) activity).error((jsonObject.get("message")).getAsString());
                break;
            case Constantes.ACTUALIZA_EMPRESA:

                if (errorCodigo.equals("0"))
                    ((Registro_EmpresaActivity) activity).succes((jsonObject.get("message")).getAsString(), null);
                else
                    ((Registro_EmpresaActivity) activity).error((jsonObject.get("message")).getAsString());
                break;
            case Constantes.ACTUALIZA_CLIENTE:

                if (errorCodigo.equals("0"))
                    ((MenuPrincipalActivity) activity).succes((jsonObject.get("message")).getAsString(), null);
                else
                    ((MenuPrincipalActivity) activity).error((jsonObject.get("message")).getAsString());
                break;
            case Constantes.ELIMINAR_EMPRESA:

                if (errorCodigo.equals("0"))
                    ((Registro_EmpresaActivity) activity).succes((jsonObject.get("message")).getAsString(), null);
                else
                    ((Registro_EmpresaActivity) activity).error((jsonObject.get("message")).getAsString());
                break;
            case Constantes.BUSCAR_TICKET:
                if (errorCodigo.equals("0"))
                    ((MenuPrincipalActivity) activity).succes((jsonObject.get("message")).getAsString(), (jsonObject.get("datos")).getAsJsonArray());
                else
                    ((MenuPrincipalActivity) activity).error((jsonObject.get("message")).getAsString());
                break;
            case Constantes.BUSCAR_TICKET_RESPUESTA:
                if (errorCodigo.equals("0"))
                    ((TicketActivity) activity).succes((jsonObject.get("message")).getAsString(), (jsonObject.get("datos")).getAsJsonArray());
                else
                    ((TicketActivity) activity).error((jsonObject.get("message")).getAsString());
                break;
            case Constantes.CERRAR_TICKET:
                if (errorCodigo.equals("0"))
                    ((TicketActivity) activity).succes((jsonObject.get("message")).getAsString(), null);
                else
                    ((TicketActivity) activity).error((jsonObject.get("message")).getAsString());
                break;
            case Constantes.RESPONDER_TICKET:
                if (errorCodigo.equals("0"))
                    ((TicketActivity) activity).succes((jsonObject.get("message")).getAsString(), null);
                else
                    ((TicketActivity) activity).error((jsonObject.get("message")).getAsString());
                break;
            case Constantes.CALIFICAR_TICKET:
                if (errorCodigo.equals("0"))
                    ((CalificarTicketActivity) activity).succes((jsonObject.get("message")).getAsString(), null);
                else
                    ((CalificarTicketActivity) activity).error((jsonObject.get("message")).getAsString());
                break;
            case Constantes.BUSCAR_NOTICIA:
                if (errorCodigo.equals("0"))
                    ((NoticiasActivity) activity).succes((jsonObject.get("message")).getAsString(), (jsonObject.get("datos")).getAsJsonArray());
                else
                    ((NoticiasActivity) activity).error((jsonObject.get("message")).getAsString());
                break;
            case Constantes.RECUPERAR_CONTRASENA:
                if (errorCodigo.equals("0"))
                    ((PasswordActivity) activity).succes((jsonObject.get("message")).getAsString(), null);
                else
                    ((PasswordActivity) activity).error((jsonObject.get("message")).getAsString());
                break;
            case Constantes.REGISTRO_TICKET:
                if (errorCodigo.equals("0"))
                    ((CrearTicketActivity) activity).succes((jsonObject.get("message")).getAsString(), null);
                else
                    ((CrearTicketActivity) activity).error((jsonObject.get("message")).getAsString());
                break;
        }
    }
}
