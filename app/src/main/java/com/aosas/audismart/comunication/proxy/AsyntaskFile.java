package com.aosas.audismart.comunication.proxy;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.widget.Toast;

import com.aosas.audismart.util.Constantes;

import java.util.Map;

/**
 * Created by dayanamartinez on 07-10-16.
 */
public class AsyntaskFile extends AsyncTask<Void, Void, String>
{
    private ProgressDialog dialog;
    private String path;
    private String nombreArchivo;
    private Map<String,String> parametros;
    private Activity activity;
    private String metodo;

    public AsyntaskFile(String path,String nombreArchivo,Map<String,String> params,Activity activity,String metodo){
    this.path = path;
    this.nombreArchivo = nombreArchivo;
    this.parametros = params;
        this.activity = activity;
        this.metodo = metodo;
        dialog = new ProgressDialog(activity);
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        dialog.setMessage("Cargando información..");
        dialog.show();


    }

    @Override
    protected String doInBackground(Void... params) {
        String result = new HttpFileUpload().multipartRequest(Constantes.URLSERVICES+Constantes.SERVICES, parametros, path, nombreArchivo, "image/jpeg");

        return result;
    }


@Override
protected void onPostExecute(String result) {
        super.onPostExecute(result);
        IRepository presenter = new Repository();
        presenter.createResponse(result, metodo, activity);
    if (dialog.isShowing()) {
        dialog.dismiss();
    }

        }

        }

