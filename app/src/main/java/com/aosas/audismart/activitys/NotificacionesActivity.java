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
import android.widget.RelativeLayout;

import com.aosas.audismart.R;
import com.aosas.audismart.comunication.proxy.IRepository;
import com.aosas.audismart.comunication.proxy.Repository;
import com.aosas.audismart.model.Notificacion;
import com.aosas.audismart.util.Constantes;
import com.aosas.audismart.util.Util;
import com.google.gson.JsonElement;

import java.util.Calendar;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

import static android.widget.Toast.LENGTH_LONG;
import static android.widget.Toast.makeText;

/**
 * The type Notificaciones activity.
 * Permite ver una notificacion en detalle y editarla
 */
public class NotificacionesActivity extends AppCompatActivity implements BaseActivity {
    private Notificacion notificacion;
    private IRepository repository = new Repository();
    private int year_x, month_x, day_x;

    /**
     * The Dialog id.
     */
    static final int DIALOG_ID = 0;

    /**
     * The Layout form.
     */
    @InjectView(R.id.layout_Form)
    RelativeLayout layout_Form;

    /**
     * The Edit text impuesto notificacion.
     */
    @InjectView(R.id.editText_impuestoNotificacion)
    EditText editText_impuestoNotificacion;

    /**
     * The Edit text empresa notificacion.
     */
    @InjectView(R.id.editText_empresaNotificacion)
    EditText editText_empresaNotificacion;

    /**
     * The Edit text fecha notificacion.
     */
    @InjectView(R.id.editText_fechaNotificacion)
    EditText editText_fechaNotificacion;

    /**
     * The Edit text hora notificacion.
     */
    @InjectView(R.id.editText_horaNotificacion)
    EditText editText_horaNotificacion;

    /**
     * The Edit text dias notificacion.
     */
    @InjectView(R.id.editText_diasNotificacion)
    EditText editText_diasNotificacion;

    /**
     * The Edit text hora antes notificacion.
     */
    @InjectView(R.id.editText_horaAntesNotificacion)
    EditText editText_horaAntesNotificacion;

    /**
     * The Button editar.
     */
    @InjectView(R.id.button_Editar)
    Button button_Editar;

    /**
     * The Button eliminar.
     */
    @InjectView(R.id.button_Eliminar)
    Button button_Eliminar;

    /**
     * The Boton fecha notificacion.
     */
    @InjectView(R.id.boton_FechaNotificacion)
    Button boton_FechaNotificacion;

    /**
     * On create.
     *
     * @param savedInstanceState the saved instance state
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notificaciones);
        ButterKnife.inject(this);

        //Se adiciona el icono a la barra de la actividad
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

        year_x = c.get(Calendar.YEAR);
        month_x = c.get(Calendar.MONTH);
        day_x = c.get(Calendar.DAY_OF_MONTH);
        editText_fechaNotificacion.setText(notificacion.fecha.substring(0, 10));
    }

    /**
     * Button editar.
     *
     * @param view the view
     */
    @OnClick(R.id.button_Editar)
    public void button_Editar(View view) {
        validar_formulario();
    }

    /**
     * Show calendar.
     */
    @OnClick(R.id.boton_FechaNotificacion)
    public void showCalendar() {
        cargarCalendario();
    }

    private void validar_formulario() {
        if (Util.validateFormularioRelative(layout_Form, 6)) {
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

    /**
     * On create dialog dialog.
     *
     * @param id the id
     * @return the dialog
     */
    protected Dialog onCreateDialog(int id) {
        if (id == DIALOG_ID)
            return new DatePickerDialog(this, dpickerListener, year_x, month_x, day_x);
        return null;
    }

    private DatePickerDialog.OnDateSetListener dpickerListener = new DatePickerDialog.OnDateSetListener(


    ) {
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            monthOfYear++;
            editText_fechaNotificacion.setText(year + "-" + monthOfYear + "-" + dayOfMonth);
        }
    };

    /**
     * Succes.
     *
     * @param succes      the succes
     * @param jsonElement the json element
     */
    @Override
    public void succes(String succes, JsonElement jsonElement) {
        makeText(this, succes, LENGTH_LONG).show();
        Intent intentMenu = new Intent(this, MenuPrincipalActivity.class);
        startActivity(intentMenu);
    }

    /**
     * Error.
     *
     * @param error the error
     */
    @Override
    public void error(String error) {
        makeText(this, error, LENGTH_LONG).show();
    }
}
