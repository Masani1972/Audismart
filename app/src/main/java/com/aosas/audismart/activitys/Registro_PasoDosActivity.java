package com.aosas.audismart.activitys;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.aosas.audismart.Object.User;
import com.aosas.audismart.R;
import com.aosas.audismart.comunication.ServiceGenerator;
import com.aosas.audismart.comunication.APIService;
import com.aosas.audismart.comunication.ServiceTaskAsyn;
import com.squareup.okhttp.ResponseBody;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import retrofit.Call;
import retrofit.Response;


public class Registro_PasoDosActivity extends AppCompatActivity {
    RelativeLayout layout_form;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_pasodos);

        //Get Views
        layout_form = (RelativeLayout) findViewById(R.id.layout_Form);

    }


    /*
 Eventos del todos los botones del layout
  */
    public void click(View view) {
        switch (view.getId()) {
            case R.id.button_Finalizar:
                validar_formulario();
                break;
        }
    }

    /*
   Validar los campos EditText del formulario
   mediante el ciclo onteniendo cada una de las vista y validando la longitud del texto
    */
    private void validar_formulario() {
        int editTextOk = 0;
        int childcount = layout_form.getChildCount();
        for (int i = 0; i < childcount; i++) {
            View v = layout_form.getChildAt(i);
            EditText tv = (EditText) v;
            if ((tv != null && tv.getText().toString().length() > 0))
                editTextOk++;
            continue;
        }
        if (editTextOk == childcount) {




        } else {
            Toast.makeText(Registro_PasoDosActivity.this, "Por favor diligenciar el formulario", Toast.LENGTH_LONG).show();
        }
    }




}
