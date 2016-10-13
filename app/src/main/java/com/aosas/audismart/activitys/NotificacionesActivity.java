package com.aosas.audismart.activitys;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.aosas.audismart.R;
import com.aosas.audismart.comunication.proxy.IRepository;
import com.aosas.audismart.comunication.proxy.Repository;
import com.aosas.audismart.model.Notificacion;
import com.aosas.audismart.util.Constantes;
import com.aosas.audismart.util.Util;
import com.google.gson.JsonElement;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

import static android.widget.Toast.LENGTH_LONG;
import static android.widget.Toast.makeText;

public class NotificacionesActivity extends AppCompatActivity implements BaseActivity {
    private Notificacion notificacion;
    private IRepository repository = new Repository();
    private int year_x, month_x,day_x;

    static final int DIALOG_ID=0;

    @InjectView(R.id.layout_Form)
    RelativeLayout layout_Form;

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

    @InjectView(R.id.boton_FechaNotificacion)
    Button boton_FechaNotificacion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notificaciones);
        ButterKnife.inject(this);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setLogo(R.drawable.logoapp);
        actionBar.setDisplayUseLogoEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);

        notificacion = (Notificacion) getIntent().getSerializableExtra(Constantes.EXTRA_NOTIFICACIONES);
        cargarDatos(notificacion);
    }

    private void cargarDatos(Notificacion notificacion) {
        editText_impuestoNotificacion.setText(notificacion.nombre);
        editText_empresaNotificacion.setText(notificacion.nombreEmpresa);
        editText_fechaNotificacion.setText(notificacion.fecha);
        editText_horaNotificacion.setText(notificacion.hora);
        editText_diasNotificacion.setText(notificacion.antesDias);
        editText_horaAntesNotificacion.setText(notificacion.antesHora);

        Calendar c = Calendar.getInstance();

        year_x= c.get(Calendar.YEAR);
        month_x = c.get(Calendar.MONTH);
        day_x = c.get(Calendar.DAY_OF_MONTH);
        editText_fechaNotificacion.setText(notificacion.fecha.substring(0,10));
    }

    @OnClick(R.id.button_Editar)
    public void button_Editar(View view) {
        validar_formulario();
    }

    @OnClick(R.id.boton_FechaNotificacion)
    public void showCalendar()
    {
        cargarCalendario();
    }

    private void validar_formulario() {
        if(Util.validateFormularioRelative(layout_Form,6)){
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


    private void cargarCalendario() {
        showDialog(DIALOG_ID);
    }

    protected Dialog onCreateDialog(int id){
        if (id == DIALOG_ID)
            return new DatePickerDialog(this,dpickerListener,year_x,month_x,day_x);
        return null;
    }

    private DatePickerDialog.OnDateSetListener dpickerListener = new DatePickerDialog.OnDateSetListener(


    ) {
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            monthOfYear++;
            editText_fechaNotificacion.setText(year+"-"+monthOfYear+"-"+dayOfMonth);
        }
    };

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
