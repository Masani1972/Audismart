package com.aosas.audismart.activitys;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.aosas.audismart.R;
import com.google.gson.JsonElement;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;


public class Registro_PasoDosActivity extends AppCompatActivity implements BaseActivity {

    @InjectView(R.id.layout_Form)
    RelativeLayout layout_Form;

    @InjectView(R.id.button_Finalizar)
    Button button_Finalizar;

    @InjectView(R.id.editText_Departamento)
    AutoCompleteTextView editText_Departamento;

    @InjectView(R.id.editText_Ciudad)
    AutoCompleteTextView editText_Ciudad;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_pasodos);
        ButterKnife.inject(this);
    }

    @OnClick(R.id.button_Finalizar)
    public void button_Finalizar(View view) {
        validar_formulario();
    }

    /*******************
     Presentador¡¡ Logica de la  vista
     *******************/


    /*
   Validar los campos EditText del formulario
   mediante el ciclo onteniendo cada una de las vista y validando la longitud del texto
    */
    private void validar_formulario() {
        int editTextOk = 0;
        int childcount = layout_Form.getChildCount();
        for (int i = 0; i < childcount; i++) {
            View v = layout_Form.getChildAt(i);
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


    @Override
    public void succes(String succes, JsonElement jsonElement) {

    }

    @Override
    public void error(String error) {

    }
}
