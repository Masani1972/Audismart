package com.aosas.audismart.comunication;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

import com.aosas.audismart.Object.User;
import com.squareup.okhttp.ResponseBody;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import retrofit.Call;
import retrofit.Response;

/**
 * Created by Lmartinez on 08/01/2016.
 */
public class ServiceTaskAsyn extends AsyncTask<Void, Void, Response> {
    private ProgressDialog dialog;

    public ServiceTaskAsyn (Context context,String metodo,Object object){
        dialog = new ProgressDialog(context);
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
        Call<ResponseBody> call = taskService.createUser("ffddfdfdfdf", "fdfdffdffdfdfd", "REGISTRO CLIENTE", "4444@h.com", "1", "1", "24455", "2", "1","1");
        try {
            reponseBody = call.execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return reponseBody;
    }






    protected void onPostExecute(Response resultado) {
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

        if (dialog.isShowing()) {
            dialog.dismiss();
        }

    }
}
