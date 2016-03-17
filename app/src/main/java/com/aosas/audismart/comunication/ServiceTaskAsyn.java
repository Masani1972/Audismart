package com.aosas.audismart.comunication;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.res.Resources;
import android.os.AsyncTask;
import android.util.Log;

import com.aosas.audismart.R;
import com.aosas.audismart.model.Empresa;
import com.aosas.audismart.model.GCM;
import com.aosas.audismart.model.Login;
import com.aosas.audismart.model.User;
import com.aosas.audismart.util.Constantes;
import com.squareup.okhttp.ResponseBody;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import retrofit.Call;
import retrofit.Response;


/**
 * Created by Lmartinez on 08/01/2016.
 */
public class ServiceTaskAsyn extends AsyncTask<Void, Void, Response> {
    private ProgressDialog dialog;
    private Object object;
    private String metodo;
    Activity activity;
    private static final String TAG = "ServiceTaskAsyn";

    public ServiceTaskAsyn (Context context,String metodo,Object object){
         activity= (Activity)context;
        dialog = new ProgressDialog(context);
        this.metodo = metodo;
        this.object = object;
    }

    @Override
    protected void onPreExecute() {
        // TODO i18n
        dialog.setMessage("Consultando información..");
        dialog.show();

    }

    @Override
    protected Response doInBackground(Void... unused) {
        Response reponseBody = null;
        APIService taskService = ServiceGenerator.createService(APIService.class);
        Call<ResponseBody> call = null;
        switch (metodo){
            case Constantes.REGISTRO_USUARIO:
                User user = (User) object;
                call = taskService.createUser(user.nombres, user.apellidos, user.ACCION, user.email, user.id_departamento, user.id_ciudad, user.telefono, user.contrasena, user.acepto_terminos, user.acepto_envio);
            break;
            case Constantes.LOGIN:
                Login login = (Login) object;
                call = taskService.loginUser(login.email, login.contrasena, login.ACCION);
                break;
            case Constantes.REGISTRO_EMPRESA:
                Empresa empresa = (Empresa) object;
                call = taskService.createCompany(empresa.id_cliente, empresa.nombre, empresa.id_departamento, empresa.id_ciudad, empresa.tipo_documento, empresa.documento, empresa.ingresos, empresa.categoria, empresa.impuesto_consumo, empresa.impuesto_riqueza, empresa.ACCION);
                break;
            case Constantes.REGISTRO_DISPOSITIVO:
                GCM gcm = (GCM) object;
                call = taskService.registerDevice(gcm.id_cliente, gcm.so,gcm.dispositivo,gcm.identificador,gcm.ACCION);
                break;
            case "":
               call = null;
                break;
        }
        try {
            if(call!=null)
            reponseBody = call.execute();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return reponseBody;
    }


    protected void onPostExecute(Response resultado) {
        if(resultado!=null){
        BufferedReader bf = null;
        StringBuilder sb = new StringBuilder();
        try {
            bf = new BufferedReader(new InputStreamReader(((ResponseBody)resultado.body()).byteStream()));
            String line;
            while((line = bf.readLine())!=null){
                sb.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        String result = sb.toString();
        Log.i(TAG, result);
        IRepository presenter = new Repository();
        presenter.createResponse(result, metodo, activity);

        if (dialog.isShowing()) {
            dialog.dismiss();
        }
    }
    else{
            IRepository presenter = new Repository();
        presenter.createResponse(Resources.getSystem().getString(R.string.errorServiciosWEb),metodo,activity);}
    }
}
