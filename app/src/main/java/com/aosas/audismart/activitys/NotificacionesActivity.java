package com.aosas.audismart.activitys;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.aosas.audismart.R;
import com.aosas.audismart.comunication.proxy.IRepository;
import com.aosas.audismart.comunication.proxy.Repository;
import com.aosas.audismart.model.Notificacion;
import com.aosas.audismart.util.Constantes;
import com.aosas.audismart.util.Util;
import com.google.gson.JsonElement;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

import static android.widget.Toast.LENGTH_LONG;
import static android.widget.Toast.makeText;

public class NotificacionesActivity extends AppCompatActivity implements BaseActivity {
    private Notificacion notificacion;
    private IRepository repository = new Repository();

    @InjectView(R.id.layout_Form)
    LinearLayout layout_Form;

    @InjectView(R.id.editText_impuestoNotificacion)
    EditText editText_impuestoNotificacion;

    @InjectView(R.id.editText_empresaNotificacion)
    EditText editText_empresaNotificacion;

    @InjectView(R.id.editText_fechaNotificacion)
    EditText editText_fechaNotificacion;

    @InjectView(R.id.editText_horaNotificacion)
    EditText editText_horaNotificacion;

    @InjectView(R.id.editText_diasNotificacion)
    EditText editText_diasNotificacion;

    @InjectView(R.id.editText_horaAntesNotificacion)
    EditText editText_horaAntesNotificacion;

    @InjectView(R.id.button_Editar)
    Button button_Editar;

    @InjectView(R.id.button_Eliminar)
    Button button_Eliminar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notificaciones);
        ButterKnife.inject(this);

        notificacion = (Notificacion) getIntent().getSerializableExtra("notificacion");
        cargarDatos(notificacion);
    }

    private void cargarDatos(Notificacion notificacion) {
        editText_impuestoNotificacion.setText(notificacion.nombre);
        editText_empresaNotificacion.setText(notificacion.nombreEmpresa);
        editText_fechaNotificacion.setText(notificacion.fecha);
        editText_horaNotificacion.setText(notificacion.hora);
        editText_diasNotificacion.setText(notificacion.antesDias);
        editText_horaAntesNotificacion.setText(notificacion.antesHora);
    }

    @OnClick(R.id.button_Editar)
    public void button_Editar(View view) {
        validar_formulario();
    }

    private void validar_formulario() {
        if(Util.validateFormularioLinear(layout_Form)){
           notificacion.nombre = editText_impuestoNotificacion.getText().toString();
           notificacion.nombreEmpresa = editText_empresaNotificacion.getText().toString();
           notificacion.fecha = editText_fechaNotificacion.getText().toString();
           notificacion.hora = editText_horaNotificacion.getText().toString();
           notificacion.antesDias = editText_horaNotificacion.getText().toString();
            notificacion.ACCION = Constantes.ACTUALIZA_NOTIFICACION;
            repository.createRequets(this, notificacion, Constantes.ACTUALIZA_NOTIFICACION);

        } else {
        makeText(this, R.string.formularioIncompleto, LENGTH_LONG).show();
    }
    }


    @Override
    public void succes(String succes, JsonElement jsonElement) {
        makeText(this, succes, LENGTH_LONG).show();
        Intent intentMenu = new Intent(this,MenuPrincipalActivity.class);
        startActivity(intentMenu);
    }

    @Override
    public void error(String error) {
        makeText(this,error, LENGTH_LONG).show();
    }
}
