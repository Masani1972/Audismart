package com.aosas.audismart.activitys;


import com.aosas.audismart.util.Constantes;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;


import com.aosas.audismart.adapters.AutocompleteCategoriaAdapter;
import com.aosas.audismart.adapters.AutocompleteCiudadAdapter;
import com.aosas.audismart.adapters.AutocompleteDepartamentoAdapter;
import com.aosas.audismart.adapters.AutocompleteDocumentoAdapter;
import com.aosas.audismart.R;
import com.aosas.audismart.adapters.AutocompletePeriodicidadAdapter;
import com.aosas.audismart.comunication.proxy.IRepository;
import com.aosas.audismart.comunication.proxy.Repository;
import com.aosas.audismart.model.Categoria;
import com.aosas.audismart.model.Ciudad;
import com.aosas.audismart.model.Departamento;
import com.aosas.audismart.model.DocumentoIdentidad;
import com.aosas.audismart.model.Empresa;
import com.aosas.audismart.model.Periodicidad;
import com.aosas.audismart.repository.FileAsserts;
import com.aosas.audismart.repository.Preferences;

import com.aosas.audismart.util.Util;

import com.google.gson.JsonElement;


import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

import static android.widget.Toast.*;


/**
 * The type Registro empresa activity.
 * Soporta el formulario para el registro de empresa
 */
public class Registro_EmpresaActivity extends AppCompatActivity implements BaseActivity {
    private String idDepartamento = "";
    private String idCiudad = "";
    private String impuestoConsumo = null;
    private String impuestoRiqueza = null;
    private String idCategoria = null;
    private String idDocumento = null;
    private String periodicidad = null;
    private IRepository repository = new Repository();
    private Empresa empresa;
    private Empresa empresaEdit;
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
     * The Edit text nombre empresa.
     */
    @InjectView(R.id.editText_Nombre_Empresa)
    EditText editText_Nombre_Empresa;

    /**
     * The Edit text departamento.
     */
    @InjectView(R.id.editText_Departamento)
    AutoCompleteTextView editText_Departamento;

    /**
     * The Edit text ciudad.
     */
    @InjectView(R.id.editText_Ciudad)
    AutoCompleteTextView editText_Ciudad;

    /**
     * The Edit text tipo documento.
     */
    @InjectView(R.id.editText_TipoDocumento)
    AutoCompleteTextView editText_TipoDocumento;

    /**
     * The Edit text num documento.
     */
    @InjectView(R.id.editText_NumDocumento)
    EditText editText_NumDocumento;

    /**
     * The Edit text ingresos.
     */
    @InjectView(R.id.editText_Ingresos)
    EditText editText_Ingresos;

    /**
     * The Edit text regimen.
     */
    @InjectView(R.id.editText_Regimen)
    AutoCompleteTextView editText_Regimen;

    /**
     * The Edit text fecha mercantil.
     */
    @InjectView(R.id.editText_FechaMercantil)
    EditText editText_FechaMercantil;

    /**
     * The Edit text periodicidad.
     */
    @InjectView(R.id.editText_Periodicidad)
    AutoCompleteTextView editText_Periodicidad;

    /**
     * The Text view tip.
     */
    @InjectView(R.id.textView_Tip)
    TextView textView_Tip;

    /**
     * The Button finalizar.
     */
    @InjectView(R.id.button_Finalizar)
    Button button_Finalizar;

    /**
     * The Button actualizar.
     */
    @InjectView(R.id.button_Actualizar)
    Button button_Actualizar;

    /**
     * The Button eliminar.
     */
    @InjectView(R.id.button_Eliminar)
    Button button_Eliminar;

    /**
     * The Boton fecha mercantil.
     */
    @InjectView(R.id.boton_FechaMercantil)
    Button boton_FechaMercantil;

    /**
     * The Radio button si.
     */
    @InjectView(R.id.radioButton_Si)
    RadioButton radioButton_Si;

    /**
     * The Radio button no.
     */
    @InjectView(R.id.radioButton_No)
    RadioButton radioButton_No;

    /**
     * The Radio button si contribuyente.
     */
    @InjectView(R.id.radioButton_SiContribuyente)
    RadioButton radioButton_SiContribuyente;

    /**
     * The Radio button no contribuyente.
     */
    @InjectView(R.id.radioButton_NoContribuyente)
    RadioButton radioButton_NoContribuyente;

    /**
     * On create.
     *
     * @param savedInstanceState the saved instance state
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_empresa);
        ButterKnife.inject(this);

        initViews();
    }

    private void initViews() {
         /*
        Permite adicionar un icono al action bar
         */
        ActionBar actionBar = getSupportActionBar();
        actionBar.setLogo(R.drawable.logoapp);
        actionBar.setDisplayUseLogoEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setSubtitle(getResources().getString(R.string.subtitulo_pasodos));

        if (this.getIntent().getExtras() != null && this.getIntent().getExtras().containsKey("empresa")) {
            empresaEdit = (Empresa) this.getIntent().getExtras().getSerializable("empresa");
            actualizarEmpresa(empresaEdit);
            actionBar.setSubtitle("");
        }

          /*listener  autocomplete no soportado por ButterKnife*/
        editText_Departamento.setThreshold(1);
        editText_Departamento.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                cargarListaDepartamentos();
                editText_Departamento.showDropDown();
                return false;
            }
        });

        /*listener  autocomplete no soportado por ButterKnife*/
        editText_Ciudad.setThreshold(1);
        editText_Ciudad.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                cargarListaCiudades();
                editText_Ciudad.showDropDown();
                return false;
            }
        });

          /*listener  autocomplete no soportado por ButterKnife*/
        editText_Regimen.setThreshold(1);
        editText_Regimen.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                cargarListaCategorias();
                editText_Regimen.showDropDown();
                return false;
            }
        });

        editText_TipoDocumento.setFocusable(false);
        editText_TipoDocumento.setClickable(true);

         /*listener  autocomplete no soportado por ButterKnife*/
        editText_TipoDocumento.setThreshold(1);
        editText_TipoDocumento.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                cargarListaDocumento();
                editText_TipoDocumento.showDropDown();
                return false;
            }
        });

        editText_Periodicidad.setFocusable(false);
        editText_Periodicidad.setClickable(true);

         /*listener  autocomplete no soportado por ButterKnife*/
        editText_Periodicidad.setThreshold(1);
        editText_Periodicidad.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                cargarListaPeriocidad();
                editText_Periodicidad.showDropDown();
                return false;
            }
        });

        Calendar c = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat(Constantes.FORMATOFECHANOTIDICACIONJSON);
        String formattedDate = df.format(c.getTime());
        year_x = c.get(Calendar.YEAR);
        month_x = c.get(Calendar.MONTH);
        day_x = c.get(Calendar.DAY_OF_MONTH);
        editText_FechaMercantil.setText(formattedDate);
    }

    /**
     * Button eliminar.
     *
     * @param view the view
     */
    @OnClick(R.id.button_Eliminar)
    public void button_Eliminar(View view) {
        eliminar_usuario();
    }

    /**
     * Button finalizar.
     *
     * @param view the view
     */
    @OnClick(R.id.button_Finalizar)
    public void button_Finalizar(View view) {
        validar_formulario_registro();
    }


    /**
     * Button actualizar.
     *
     * @param view the view
     */
    @OnClick(R.id.button_Actualizar)
    public void button_Actualizar(View view) {
        validar_formulario_actualizacion();
    }


    /**
     * Show calendar.
     */
    @OnClick(R.id.boton_FechaMercantil)
    public void showCalendar() {
        cargarCalendario();
    }

    /**
     * On activity result.
     *
     * @param requestCode the request code
     * @param resultCode  the result code
     * @param data        the data
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == Constantes.UPDATECOMPANY) {
            if (resultCode == RESULT_OK) {

            }
        }
    }

    /*******************
     * Presentador¡¡ Logica de la  vista
     *******************/

    /*
    Elminar empresa
     */
    private void eliminar_usuario() {
        empresaEdit.ACCION = Constantes.ELIMINAR_EMPRESA;
        repository.createRequets(this, empresaEdit, Constantes.ELIMINAR_EMPRESA);

    }

    /*
    Set valores para editar datos empresa
     */
    private void actualizarEmpresa(Empresa empresaEdit) {
        editText_Nombre_Empresa.setText(empresaEdit.nombre);
        editText_Departamento.setText(empresaEdit.departamento);
        editText_Ciudad.setText(empresaEdit.ciudad);
        editText_TipoDocumento.setText(empresaEdit.tipo_documento);
        editText_NumDocumento.setText(empresaEdit.documento);
        editText_Ingresos.setText(empresaEdit.ingresos);
        editText_Regimen.setText(empresaEdit.categoria);
        editText_FechaMercantil.setText(empresaEdit.fecharegistromercantil);
        editText_Periodicidad.setText(empresaEdit.id_periodo);

        if (empresaEdit.impuesto_consumo.equals("1"))
            radioButton_Si.setChecked(true);
        else
            radioButton_No.setChecked(true);

        if (empresaEdit.impuesto_riqueza.equals("1"))
            radioButton_SiContribuyente.setChecked(true);
        else
            radioButton_NoContribuyente.setChecked(true);

        textView_Tip.setVisibility(View.INVISIBLE);
        button_Finalizar.setVisibility(View.INVISIBLE);
        button_Actualizar.setVisibility(View.VISIBLE);
        button_Eliminar.setVisibility(View.VISIBLE);
    }

    /**
     * On radio button clicked impuesto.
     *
     * @param view the view
     */
/*
    Valor para impuesto al consumo RadioButton
     */
    public void onRadioButtonClickedImpuesto(View view) {
        boolean checked = ((RadioButton) view).isChecked();

        switch (view.getId()) {
            case R.id.radioButton_Si:
                if (checked)
                    impuestoConsumo = "1";
                break;
            case R.id.radioButton_No:
                if (checked)
                    impuestoConsumo = "0";
                break;
        }

    }

    /**
     * On radio button clicked riqueza.
     *
     * @param view the view
     */
/*
    Valor para impuesto riqueza RadioButton
     */
    public void onRadioButtonClickedRiqueza(View view) {
        boolean checked = ((RadioButton) view).isChecked();

        switch (view.getId()) {
            case R.id.radioButton_SiContribuyente:
                if (checked)
                    impuestoRiqueza = "1";
                break;
            case R.id.radioButton_NoContribuyente:
                if (checked)
                    impuestoRiqueza = "0";
                break;
        }

    }

    /*
   Carga la lista de categorias al repositorio local
   */
    private void cargarListaCategorias() {
        ArrayList arrayListCategorias = Util.jsontoArrayList(FileAsserts.loadJSONFromAsset(this, "categorias"), new Categoria());
        com.aosas.audismart.adapters.AutocompleteCategoriaAdapter itemadapter = new AutocompleteCategoriaAdapter(this, R.layout.adapter_autotext, arrayListCategorias);
        editText_Regimen.setAdapter(itemadapter);
        editText_Regimen.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> listView, View view, int position, long id) {
                Categoria categoria = (Categoria) listView.getItemAtPosition(position);
                idCategoria = categoria.id_Categoria;
            }
        });
    }

    /*
    Carga la lista de documentos al repositorio local
    */
    private void cargarListaDocumento() {
        ArrayList arrayListDocumentos = Util.jsontoArrayList(FileAsserts.loadJSONFromAsset(this, "documentos"), new DocumentoIdentidad());
        AutocompleteDocumentoAdapter itemadapter = new AutocompleteDocumentoAdapter(this, R.layout.adapter_autotext, arrayListDocumentos);
        editText_TipoDocumento.setAdapter(itemadapter);
        editText_TipoDocumento.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> listView, View view, int position, long id) {
                DocumentoIdentidad documento = (DocumentoIdentidad) listView.getItemAtPosition(position);
                idDocumento = documento.id_Documento;
            }
        });
    }

    /*
   Carga la lista de documentos al repositorio local
   */
    private void cargarListaPeriocidad() {
        ArrayList arrayListPeriodo = Util.jsontoArrayList(FileAsserts.loadJSONFromAsset(this, "periodicidad"), new Periodicidad());
        AutocompletePeriodicidadAdapter itemadapter = new AutocompletePeriodicidadAdapter(this, R.layout.adapter_autotext, arrayListPeriodo);
        editText_Periodicidad.setAdapter(itemadapter);
        editText_Periodicidad.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> listView, View view, int position, long id) {
                Periodicidad periodo = (Periodicidad) listView.getItemAtPosition(position);
                periodicidad = periodo.id;
            }
        });
    }

    /*
    Solicita la lista de departamento al repositorio local
    */
    private void cargarListaDepartamentos() {
        ArrayList arrayListDepartamentos = Util.jsontoArrayList(FileAsserts.loadJSONFromAsset(this, "departamentos"), new Departamento());
        AutocompleteDepartamentoAdapter itemadapter = new AutocompleteDepartamentoAdapter(this, R.layout.adapter_autotext, arrayListDepartamentos);
        editText_Departamento.setAdapter(itemadapter);
        editText_Departamento.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> listView, View view, int position, long id) {
                Departamento departamento = (Departamento) listView.getItemAtPosition(position);
                idDepartamento = departamento.Id_departamento;
            }
        });
    }

    /*
    Solicita la lista de ciudades al repositorio local de acuerdo al departamento
     */
    private void cargarListaCiudades() {
        if (idDepartamento.length() > 0) {
            ArrayList arrayListCiudades = Util.jsontoArrayList(FileAsserts.readJsonDescripcion(this, idDepartamento), new Ciudad());
            AutocompleteCiudadAdapter itemadapter = new AutocompleteCiudadAdapter(this, R.layout.adapter_autotext, arrayListCiudades);
            editText_Ciudad.setAdapter(itemadapter);
            editText_Ciudad.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                public void onItemClick(AdapterView<?> listView, View view, int position, long id) {
                    Ciudad ciudad = (Ciudad) listView.getItemAtPosition(position);
                    idCiudad = ciudad.Id_ciudad;
                }
            });
        } else {
            makeText(this, R.string.campoDepartamentoIvalido, LENGTH_LONG).show();
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
            editText_FechaMercantil.setText(year + "-" + monthOfYear + "-" + dayOfMonth);
        }
    };

    /*
   Validar los campos EditText del formulario
   mediante el ciclo onteniendo cada una de las vista y validando la longitud del texto
    */
    private void validar_formulario_registro() {
        if (Util.validateFormularioRelative(layout_Form, 9) & impuestoConsumo != null & impuestoRiqueza != null) {
            if (Preferences.getIdClient(this).length() > 0) {
                empresa = new Empresa(Preferences.getIdClient(this), editText_Nombre_Empresa.getText().toString(), idDepartamento, editText_Departamento.getText().toString(), idCiudad, editText_Ciudad.getText().toString(), idDocumento, editText_NumDocumento.getText().toString(), editText_Ingresos.getText().toString(), idCategoria, impuestoConsumo, editText_FechaMercantil.getText().toString(), periodicidad, impuestoRiqueza, Constantes.REGISTRO_EMPRESA, "");
                repository.createRequets(this, empresa, Constantes.REGISTRO_EMPRESA);

            } else {
                makeText(this, R.string.errorPreferencias, LENGTH_LONG).show();
            }
        } else {
            makeText(this, R.string.formularioIncompleto, LENGTH_LONG).show();
        }
    }

    /*
  Validar los campos EditText del formulario
  mediante el ciclo onteniendo cada una de las vista y validando la longitud del texto
   */
    private void validar_formulario_actualizacion() {
        if (Util.validateFormularioRelative(layout_Form, 9) & impuestoConsumo != null & impuestoRiqueza != null) {
            if (Preferences.getIdClient(this).length() > 0) {
                empresa = new Empresa(Preferences.getIdClient(this), editText_Nombre_Empresa.getText().toString(), idDepartamento, editText_Departamento.getText().toString(), idCiudad, editText_Ciudad.getText().toString(), idDocumento, editText_NumDocumento.getText().toString(), editText_Ingresos.getText().toString(), idCategoria, impuestoConsumo, editText_FechaMercantil.getText().toString(), periodicidad, impuestoRiqueza, Constantes.ACTUALIZA_EMPRESA, "");
                repository.createRequets(this, empresa, Constantes.ACTUALIZA_EMPRESA);

            } else {
                makeText(this, R.string.errorPreferencias, LENGTH_LONG).show();
            }
        } else {
            makeText(this, R.string.formularioIncompleto, LENGTH_LONG).show();
        }
    }


    /**
     * Succes.
     *
     * @param succes      the succes
     * @param jsonElement the json element
     */
    @Override
    public void succes(String succes, JsonElement jsonElement) {

        if (succes.equals("Se ha ingresado la empresa con exito")) {
            empresa.id_empresa = jsonElement.getAsString();
            if (Preferences.getEmpresas(this) != null) {
                ArrayList<Empresa> empresas = Preferences.getEmpresas(this);
                empresas.add(empresa);
            } else {
                ArrayList<Empresa> empresas = new ArrayList<Empresa>();
                empresas.add(empresa);
            }
            Intent intent_menu = new Intent(this, MenuPrincipalActivity.class);
            startActivity(intent_menu);
        } else if (succes.equals(Constantes.ACTUALIZA_EMPRESA_RESPONSE)) {
            finish();
        } else if (succes.equals(Constantes.ELIMINAR_EMPRESA_RESPONSE)) {
            finish();
        }
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
