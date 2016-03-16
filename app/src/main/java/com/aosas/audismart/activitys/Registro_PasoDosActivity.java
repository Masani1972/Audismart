package com.aosas.audismart.activitys;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.aosas.audismart.R;
import com.aosas.audismart.adapters.AutocompleteCategoriaAdapter;
import com.aosas.audismart.adapters.AutocompleteCiudadAdapter;
import com.aosas.audismart.adapters.AutocompleteDepartamentoAdapter;
import com.aosas.audismart.adapters.AutocompleteDocumentoAdapter;
import com.aosas.audismart.comunication.IRepository;
import com.aosas.audismart.comunication.Repository;
import com.aosas.audismart.model.Categoria;
import com.aosas.audismart.model.Ciudad;
import com.aosas.audismart.model.Departamento;
import com.aosas.audismart.model.DocumentoIdentidad;
import com.aosas.audismart.model.Empresa;
import com.aosas.audismart.repository.FileAsserts;
import com.aosas.audismart.repository.Preferences;
import com.aosas.audismart.util.Constantes;
import com.aosas.audismart.util.Util;
import com.google.gson.JsonElement;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;


public class Registro_PasoDosActivity extends AppCompatActivity implements BaseActivity {
    private String idDepartamento = "";
    private String idCiudad = "";
    private String impuestoConsumo = null;
    private String impuestoRiqueza = null;
    private String idCategoria = null;
    private String idDocumento = null;

    @InjectView(R.id.layout_Form)
    RelativeLayout layout_Form;

    @InjectView(R.id.editText_Nombre_Empresa)
    EditText editText_Nombre_Empresa;

    @InjectView(R.id.editText_Departamento)
    AutoCompleteTextView editText_Departamento;

    @InjectView(R.id.editText_Ciudad)
    AutoCompleteTextView editText_Ciudad;

    @InjectView(R.id.editText_TipoDocumento)
    AutoCompleteTextView editText_TipoDocumento;

    @InjectView(R.id.editText_NumDocumento)
    EditText editText_NumDocumento;

    @InjectView(R.id.editText_Ingresos)
    EditText editText_Ingresos;

    @InjectView(R.id.editText_Regimen)
    AutoCompleteTextView editText_Regimen;

    @InjectView(R.id.button_Finalizar)
    Button button_Finalizar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_pasodos);
        ButterKnife.inject(this);

           /*
        Permite adicionar un icono al action bar
         */
        ActionBar actionBar = getSupportActionBar();
        actionBar.setLogo(R.mipmap.ic_launcher);
        actionBar.setDisplayUseLogoEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setSubtitle(getResources().getString(R.string.subtitulo_pasodos));

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
    }


    @OnClick(R.id.button_Finalizar)
    public void button_Finalizar(View view) {
        validar_formulario();
    }

    /*******************
     Presentador¡¡ Logica de la  vista
     *******************/

    /*
    Valor para impuesto al consumo RadioButton
     */
    public void onRadioButtonClickedImpuesto(View view){
        boolean checked = ((RadioButton) view).isChecked();

        switch(view.getId()) {
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

    /*
    Valor para impuesto riqueza RadioButton
     */
    public void onRadioButtonClickedRiqueza(View view){
        boolean checked = ((RadioButton) view).isChecked();

        switch(view.getId()) {
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
        AutocompleteCategoriaAdapter itemadapter = new AutocompleteCategoriaAdapter(this, R.layout.adapter_autotext,arrayListCategorias);
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
        AutocompleteDocumentoAdapter itemadapter = new AutocompleteDocumentoAdapter(this, R.layout.adapter_autotext,arrayListDocumentos);
        editText_TipoDocumento.setAdapter(itemadapter);
        editText_TipoDocumento.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> listView, View view, int position, long id) {
                DocumentoIdentidad documento = (DocumentoIdentidad) listView.getItemAtPosition(position);
                idDocumento = documento.id_Documento;
            }
        });
    }

    /*
    Solicita la lista de departamento al repositorio local
    */
    private void cargarListaDepartamentos() {
        ArrayList arrayListDepartamentos = Util.jsontoArrayList(FileAsserts.loadJSONFromAsset(this, "departamentos"), new Departamento());
        AutocompleteDepartamentoAdapter itemadapter = new AutocompleteDepartamentoAdapter(this, R.layout.adapter_autotext,arrayListDepartamentos);
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
        if(idDepartamento.length()>0) {
            ArrayList arrayListCiudades = Util.jsontoArrayList(FileAsserts.readJsonDescripcion(this, idDepartamento), new Ciudad());
            AutocompleteCiudadAdapter itemadapter = new AutocompleteCiudadAdapter(this, R.layout.adapter_autotext,arrayListCiudades);
            editText_Ciudad.setAdapter(itemadapter);
            editText_Ciudad.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                public void onItemClick(AdapterView<?> listView, View view, int position, long id) {
                    Ciudad ciudad = (Ciudad) listView.getItemAtPosition(position);
                    idCiudad = ciudad.Id_ciudad;
                }
            });
        }else{
            Toast.makeText(Registro_PasoDosActivity.this, R.string.campoDepartamentoIvalido,Toast.LENGTH_LONG).show();
        }

    }

    /*
   Validar los campos EditText del formulario
   mediante el ciclo onteniendo cada una de las vista y validando la longitud del texto
    */
    private void validar_formulario() {
        int editTextOk = 0;
        int childcount = layout_Form.getChildCount();
        for (int i = 1; i < childcount; i=i+2) {
            View v = layout_Form.getChildAt(i);
            EditText tv = (EditText) v;
            if ((tv != null && tv.getText().toString().length() > 0))
                editTextOk++;
            continue;
        }

         if (editTextOk == childcount/2 & impuestoConsumo!=null & impuestoRiqueza!=null) {
             if (Preferences.getClient(this).length() > 0) {
                 Empresa empresa = new Empresa(Preferences.getClient(this), editText_Nombre_Empresa.getText().toString(), idDepartamento, idCiudad, idDocumento, editText_NumDocumento.getText().toString(), editText_Ingresos.getText().toString(), idCategoria, impuestoConsumo, impuestoRiqueza, Constantes.REGISTRO_EMPRESA);
                 IRepository repository = new Repository();
                 repository.createRequets(this, empresa, Constantes.REGISTRO_EMPRESA_API);
             } else {
                 Toast.makeText(Registro_PasoDosActivity.this, R.string.errorPreferencias, Toast.LENGTH_LONG).show();
             }
         } else {
             Toast.makeText(Registro_PasoDosActivity.this, R.string.formularioIncompleto, Toast.LENGTH_LONG).show();
         }
    }


    @Override
    public void succes(String succes, JsonElement jsonElement) {
        String idEmpresa=jsonElement.getAsString();
        Toast.makeText(Registro_PasoDosActivity.this, succes,Toast.LENGTH_SHORT).show();
        Intent intent_menu = new Intent(Registro_PasoDosActivity.this, MenuActivity.class);
        startActivity(intent_menu);
    }

    @Override
    public void error(String error) {
        Toast.makeText(Registro_PasoDosActivity.this, error,Toast.LENGTH_LONG).show();
    }
}
