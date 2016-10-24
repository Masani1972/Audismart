package com.aosas.audismart.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.aosas.audismart.R;
import com.aosas.audismart.activitys.Registro_EmpresaActivity;
import com.aosas.audismart.comunication.proxy.IRepository;
import com.aosas.audismart.comunication.proxy.Repository;
import com.aosas.audismart.model.Ciudad;
import com.aosas.audismart.model.Departamento;
import com.aosas.audismart.model.Empresa;
import com.aosas.audismart.model.User;
import com.aosas.audismart.repository.FileAsserts;
import com.aosas.audismart.repository.Preferences;
import com.aosas.audismart.util.Constantes;
import com.aosas.audismart.util.Util;

import java.util.ArrayList;
import java.util.List;


/**
 * The type Second level profile.
 * Maneja la lista desplegable de  perfil
 */
public class SecondLevelProfile extends BaseExpandableListAdapter {
    private Context context;
    private List<String> _listDataHeader;
    private EditText nombres, apellidos, email, telefono, contrasena;
    private LinearLayout layout_Form;
    private CheckBox chTerminos, chEnvioInfo;
    private AutoCompleteTextView departamento, ciudad;
    private ArrayList<Empresa> empresas = null;
    private TextView nombreEmpresa, lblListId;
    private boolean _isChild = false;
    private final static String TAG = "SecondLevelProfile";
    private String idDepartamento, idCiudad;
    private boolean aceptaTerminos = false;
    private boolean envioInfo = false;


    /**
     * Instantiates a new Second level profile.
     *
     * @param context        the context
     * @param listDataHeader the list data header
     * @param isChild        the is child
     */
    public SecondLevelProfile(Context context, List<String> listDataHeader, boolean isChild) {
        this.context = context;
        this._listDataHeader = listDataHeader;
        if (Preferences.getEmpresas(context) != null)
            empresas = Preferences.getEmpresas(context);
        this._isChild = isChild;
    }

    /**
     * Gets child.
     *
     * @param groupPosition  the group position
     * @param childPosititon the child posititon
     * @return the child
     */
    @Override
    public Object getChild(int groupPosition, int childPosititon) {
        return this.empresas.get(childPosititon);
    }

    /**
     * Gets child id.
     *
     * @param groupPosition the group position
     * @param childPosition the child position
     * @return the child id
     */
    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    /**
     * Gets child view.
     *
     * @param groupPosition the group position
     * @param childPosition the child position
     * @param isLastChild   the is last child
     * @param convertView   the convert view
     * @param parent        the parent
     * @return the child view
     */
    @Override
    public View getChildView(int groupPosition, int childPosition,
                             boolean isLastChild, View convertView, ViewGroup parent) {
        if (groupPosition == 0) {

            LayoutInflater infalInflater = (LayoutInflater) this.context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.datos_personales_perfil, null);
            return cargarDatosPersonales(convertView);
        } else if (groupPosition == 1) {

            LayoutInflater infalInflater = (LayoutInflater) this.context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.list_item_empresa, null);
            return listarEmpresas(convertView, childPosition, isLastChild);

        } else {
            return null;
        }
    }

    private View cargarDatosPersonales(View convertView) {
        User user = Preferences.getUsuario(context);
        nombres = (EditText) convertView.findViewById(R.id.editText_Nombres_Usuario);
        nombres.setText(user.nombres);
        apellidos = (EditText) convertView.findViewById(R.id.editText_Apellidos);
        apellidos.setText(user.apellidos);
        email = (EditText) convertView.findViewById(R.id.editText_email);
        email.setText(user.email);
        departamento = (AutoCompleteTextView) convertView.findViewById(R.id.editText_Departamento);
        idDepartamento = user.id_departamento.replaceAll(" ", "");
        departamento.setText(Util.buscarDepartamento(context, user.id_departamento.replaceAll(" ", "")));
        ciudad = (AutoCompleteTextView) convertView.findViewById(R.id.editText_Ciudad);
        ciudad.setText(Util.buscarCiudad(context, user.id_departamento.replaceAll(" ", ""), user.id_ciudad.replaceAll(" ", "")));
        telefono = (EditText) convertView.findViewById(R.id.editText_Telefono);
        telefono.setText(user.telefono);
        layout_Form = (LinearLayout) convertView.findViewById(R.id.layout_Form);


        /*Listener Checkbox*/
        chTerminos = (CheckBox) convertView.findViewById(R.id.checkBox_terminos);
        if (user.acepto_terminos == "1")
            chTerminos.setChecked(true);
        chTerminos.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView,
                                         boolean isChecked) {

                if (isChecked) {
                    aceptaTerminos = true;
                } else {
                    aceptaTerminos = false;
                }
            }
        });

        /*Listener checkbox*/
        chEnvioInfo = (CheckBox) convertView.findViewById(R.id.checkBox_envio_inf);
        if (user.acepto_envio == "1")
            chEnvioInfo.setChecked(true);
        chEnvioInfo.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView,
                                         boolean isChecked) {

                if (isChecked) {
                    envioInfo = true;
                } else {
                    envioInfo = false;
                }
            }
        });


        /*cargar losta de departamentio*/
        departamento.setThreshold(1);
        departamento.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                ArrayList arrayListDepartamentos = Util.jsontoArrayList(FileAsserts.loadJSONFromAsset(context, "departamentos"), new Departamento());
                AutocompleteDepartamentoAdapter itemadapter = new AutocompleteDepartamentoAdapter(context, R.layout.adapter_autotext, arrayListDepartamentos);
                departamento.setAdapter(itemadapter);
                departamento.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    public void onItemClick(AdapterView<?> listView, View view, int position, long id) {
                        Departamento departamento = (Departamento) listView.getItemAtPosition(position);
                        idDepartamento = departamento.Id_departamento;
                    }
                });
                departamento.showDropDown();
                return false;
            }
        });

        /*Cargar lista de ciudad*/
        ciudad.setThreshold(1);
        ciudad.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (idDepartamento.length() > 0) {
                    ArrayList arrayListCiudades = Util.jsontoArrayList(FileAsserts.readJsonDescripcion(context, idDepartamento), new Ciudad());
                    AutocompleteCiudadAdapter itemadapter = new AutocompleteCiudadAdapter(context, R.layout.adapter_autotext, arrayListCiudades);
                    ciudad.setAdapter(itemadapter);
                    ciudad.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        public void onItemClick(AdapterView<?> listView, View view, int position, long id) {
                            Ciudad ciudad = (Ciudad) listView.getItemAtPosition(position);
                            idCiudad = ciudad.Id_ciudad;
                        }
                    });
                } else {
                    Toast.makeText(context, R.string.campoDepartamentoIvalido, Toast.LENGTH_LONG).show();
                }
                ciudad.showDropDown();
                return false;
            }
        });

        Button butonActualizar = (Button) convertView.findViewById(R.id.button_Actualizar);
        butonActualizar.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                validar_formulario();

            }
        });
        return convertView;
    }

    private View listarEmpresas(View convertView, int childPosition, boolean isLastChild) {
        nombreEmpresa = (TextView) convertView.findViewById(R.id.lblListItemEmpresa);
        nombreEmpresa.setText(empresas.get(childPosition).nombre);

        Button botonAgregar = (Button) convertView.findViewById(R.id.button_Agregar);
        lblListId = (TextView) convertView.findViewById(R.id.lblListId);
        lblListId.setText(empresas.get(childPosition).id_empresa);

        ImageButton editar = (ImageButton) convertView.findViewById(R.id.imageButtonEdit);
        editar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intentEmpresa = new Intent(context, Registro_EmpresaActivity.class);
                for (int i = 0; i < empresas.size(); i++) {
                    if (empresas.get(i).id_empresa == lblListId.getText().toString()) {
                        intentEmpresa.putExtra("empresa", empresas.get(i));
                        context.startActivity(intentEmpresa);
                    }
                }
            }
        });
        if (isLastChild) {
            botonAgregar.setVisibility(View.VISIBLE);
            botonAgregar.setOnClickListener(new View.OnClickListener() {

                public void onClick(View v) {
                    Intent intentEmpresa = new Intent(context, Registro_EmpresaActivity.class);
                    context.startActivity(intentEmpresa);
                }
            });

        }
        return convertView;
    }

    /**
     * Gets children count.
     *
     * @param groupPosition the group position
     * @return the children count
     */
    @Override
    public int getChildrenCount(int groupPosition) {
        if (groupPosition == 0)
            return 1;
        else if (groupPosition == 1)
            if (empresas != null)
                return empresas.size();
            else
                return 0;
        else
            return 0;
    }

    /**
     * Gets group.
     *
     * @param groupPosition the group position
     * @return the group
     */
    @Override
    public Object getGroup(int groupPosition) {
        return this._listDataHeader.get(groupPosition);
    }

    /**
     * Gets group count.
     *
     * @return the group count
     */
    @Override
    public int getGroupCount() {
        return this._listDataHeader.size();
    }

    /**
     * Gets group id.
     *
     * @param groupPosition the group position
     * @return the group id
     */
    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    /**
     * Gets group view.
     *
     * @param groupPosition the group position
     * @param isExpanded    the is expanded
     * @param convertView   the convert view
     * @param parent        the parent
     * @return the group view
     */
    @Override
    public View getGroupView(int groupPosition, boolean isExpanded,
                             View convertView, ViewGroup parent) {
        String headerTitle = (String) getGroup(groupPosition);

        LayoutInflater infalInflater = (LayoutInflater) this.context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (_isChild) {
            convertView = infalInflater.inflate(R.layout.list_group_secondlevel, null);
            TextView lblListHeader = (TextView) convertView.findViewById(R.id.lblListHeader);
            lblListHeader.setText(headerTitle);
        } else {
            convertView = infalInflater.inflate(R.layout.list_group, null);
            TextView lblListHeader = (TextView) convertView.findViewById(R.id.lblListHeader);
            lblListHeader.setText(headerTitle);
            lblListHeader.setBackgroundColor(getColor(groupPosition));
        }

        return convertView;

    }

    /**
     * Has stable ids boolean.
     *
     * @return the boolean
     */
    @Override
    public boolean hasStableIds() {
        return true;
    }

    /**
     * Is child selectable boolean.
     *
     * @param groupPosition the group position
     * @param childPosition the child position
     * @return the boolean
     */
    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    /**
     * Gets color.
     *
     * @param position the position
     * @return the color
     */
    public int getColor(int position) {
        switch (position) {
            case 0:
                return context.getResources().getColor(R.color.colorAccent);
            case 1:
                return context.getResources().getColor(R.color.texttickets);
            default:
                return 0;
        }
    }


    /*
   Validar los campos EditText del formulario
   mediante el ciclo onteniendo cada una de las vista y validando la longitud del texto
    */
    private void validar_formulario() {
        if (aceptaTerminos & envioInfo) {
            if (Util.validateFormularioLinear(layout_Form)) {
                User user = new User(nombres.getText().toString(), apellidos.getText().toString(), email.getText().toString(), idDepartamento, idCiudad, telefono.getText().toString(), "", "1", "1", Constantes.ACTUALIZA_CLIENTE);
                IRepository repository = new Repository();
                repository.createRequets(context, user, Constantes.ACTUALIZA_CLIENTE);

            } else {
                Toast.makeText(context, R.string.formularioIncompleto, Toast.LENGTH_LONG).show();
            }
        } else {
            Toast.makeText(context, R.string.campoTerminos, Toast.LENGTH_LONG).show();
        }
    }


}