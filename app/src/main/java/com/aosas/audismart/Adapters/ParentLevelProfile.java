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
import com.aosas.audismart.model.User;
import com.aosas.audismart.repository.Preferences;

import java.util.List;

/**
 * Created by Lmartinez on 08/06/2016.
 */
public class ParentLevelProfile extends BaseExpandableListAdapter
{
    private Context context;
    private List<String> _listDataHeader;
    private EditText nombres,apellidos,email, departamento, ciudad, telefono;


    public ParentLevelProfile(Context context, List<String> listDataHeader){
        this.context = context;
        this._listDataHeader = listDataHeader;
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
                   /* TextView txtView = (TextView) parent.findViewById(R.id.lblListId);
                    ArrayList<Notificacion> notificaciones = Preferences.getNotificaciones(context);
                    for(int index =0;index<notificaciones.size();index++) {

                        if (notificaciones.get(index).id.equals(txtView.getText().toString())) {
                            Intent intentNotificaciones = new Intent(context, NotificacionesActivity.class);
                            intentNotificaciones.putExtra("notificacion", notificaciones.get(index));
                            context.startActivity(intentNotificaciones);
                        }
                    }*/
                    }
                }
            });

     /*   CheckBox cumplido = (CheckBox) convertView.findViewById(R.id.checkBoxCumplio);
        cumplido.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                View parent = (View) buttonView.getParent();
                String cumplio = "1";
                if (isChecked) {
                    Log.i("check", "true");
                    cumplio = "1";
                } else {
                    Log.i("check", "false");
                    cumplio = "0";
                }



            }
        });*/


            return convertView;
        }else{
            return null;
        }
    }

    @Override
    public int getChildrenCount(int groupPosition)
    {
        return 1;
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