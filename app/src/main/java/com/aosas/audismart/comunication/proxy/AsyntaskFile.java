package com.aosas.audismart.comunication.proxy;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;

import com.aosas.audismart.util.Constantes;

import java.util.Map;


/**
 * The type Asyntask file.
 * Proceso Asincrono, solo para envio de archivos
 */
public class AsyntaskFile extends AsyncTask<Void, Void, String> {
    private ProgressDialog dialog;
    private String path;
    private String nombreArchivo;
    private Map<String, String> parametros;
    private Activity activity;
    private String metodo;

    /**
     * Instantiates a new Asyntask file.
     *
     * @param path          the path
     * @param nombreArchivo the nombre archivo
     * @param params        the params
     * @param activity      the activity
     * @param metodo        the metodo
     */
    public AsyntaskFile(String path, String nombreArchivo, Map<String, String> params, Activity activity, String metodo) {
        this.path = path;
        this.nombreArchivo = nombreArchivo;
        this.parametros = params;
        this.activity = activity;
        this.metodo = metodo;
        dialog = new ProgressDialog(activity);
    }

    /**
     * On pre execute.
     */
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        dialog.setMessage("Cargando información..");
        dialog.show();


    }

    /**
     * Do in background string.
     *
     * @param params the params
     * @return the string
     */
    @Override
    protected String doInBackground(Void... params) {
        String result = new HttpFileUpload().multipartRequest(Constantes.URLSERVICES + Constantes.SERVICES, parametros, path, nombreArchivo, "image/jpeg");

        return result;
    }


    /**
     * On post execute.
     *
     * @param result the result
     */
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

