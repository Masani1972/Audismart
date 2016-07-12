package com.aosas.audismart.adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.aosas.audismart.R;
import com.aosas.audismart.model.Empresa;
import com.aosas.audismart.model.User;
import com.aosas.audismart.repository.Preferences;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lmartinez on 08/06/2016.
 */
public class ParentLevelProfile extends BaseExpandableListAdapter
{
    private Context context;
    private List<String> _listDataHeader;
    private EditText nombres,apellidos,email, departamento, ciudad, telefono;
    private ArrayList<Empresa> empresas;
    private TextView nombreEmpresa,lblListId;


    public ParentLevelProfile(Context context, List<String> listDataHeader){
        this.context = context;
        this._listDataHeader = listDataHeader;
        empresas = Preferences.getEmpresas(context);
    }

    @Override
    public Object getChild(int groupPosition, int childPosititon)
    {
         return 0;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition)
    {
        return childPosition;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition,
                             boolean isLastChild, View convertView, ViewGroup parent)
    {
        Log.i("groupPosition", ""+groupPosition);
        if(groupPosition == 0) {
            if (convertView == null) {
                LayoutInflater infalInflater = (LayoutInflater) this.context
                        .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = infalInflater.inflate(R.layout.datos_personales_perfil, null);
            }




            return cargarDatosPersonales(convertView);
        } else if(groupPosition == 1) {
            if (convertView == null) {
                LayoutInflater infalInflater = (LayoutInflater) this.context
                        .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = infalInflater.inflate(R.layout.list_item_empresa, null);
            }


            return listarEmpresas(convertView,childPosition);

        }else{
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
        departamento = (EditText) convertView.findViewById(R.id.editText_Departamento);
        ciudad = (EditText) convertView.findViewById(R.id.editText_Ciudad);
        telefono = (EditText) convertView.findViewById(R.id.editText_Telefono);


        ImageButton editar = (ImageButton) convertView.findViewById(R.id.button_check_editPerfil);
        editar.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                View parent = (View) v.getParent();
                if (parent != null) {

                }
            }
        });

     return convertView;

    }

    private View listarEmpresas(View convertView, int childPosition) {
        nombreEmpresa = (TextView) convertView.findViewById(R.id.lblListItemEmpresa);
        nombreEmpresa.setText(empresas.get(childPosition).nombre);

        lblListId = (TextView) convertView.findViewById(R.id.lblListId);
        lblListId.setText(empresas.get(childPosition).id_empresa);

        ImageButton editar = (ImageButton) convertView.findViewById(R.id.imageButtonEdit);
        editar.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                View parent = (View) v.getParent();
                if (parent != null) {

                }
            }
        });
        return convertView;

    }

    @Override
    public int getChildrenCount(int groupPosition)
    {
        if(groupPosition ==0)
            return 1;
        else if (groupPosition ==1)
            return empresas.size();
        else
            return 0;
    }

    @Override
    public Object getGroup(int groupPosition)
    {
        return this._listDataHeader.get(groupPosition);
    }

    @Override
    public int getGroupCount()
    {
        return this._listDataHeader.size();
    }

    @Override
    public long getGroupId(int groupPosition)
    {
        return groupPosition;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded,
                             View convertView, ViewGroup parent)
    {
        String headerTitle = (String) getGroup(groupPosition);

        if (convertView == null) {

            LayoutInflater infalInflater = (LayoutInflater) this.context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.list_group, null);
            TextView lblListHeader = (TextView) convertView.findViewById(R.id.lblListHeader);
            lblListHeader.setTypeface(null, Typeface.BOLD);
            lblListHeader.setText(headerTitle);
            lblListHeader.setBackgroundColor(getColor(groupPosition));

        }
        return convertView;

    }

    @Override
    public boolean hasStableIds()
    {
        return true;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition)
    {
        return true;
    }

    public int getColor(int position){
        switch (position){
            case 0:
                return  context.getResources().getColor(R.color.colorAccent);
            case 1:
                return  context.getResources().getColor(R.color.texttickets);

            default:
                return 0;
        }
    }


}