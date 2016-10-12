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
import android.widget.ImageButton;
import android.widget.TextView;

import com.aosas.audismart.R;
import com.aosas.audismart.activitys.NotificacionesActivity;
import com.aosas.audismart.comunication.proxy.IRepository;
import com.aosas.audismart.comunication.proxy.Repository;
import com.aosas.audismart.model.Notificacion;
import com.aosas.audismart.model.NotificacionCumplio;
import com.aosas.audismart.repository.Preferences;
import com.aosas.audismart.util.Constantes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Lmartinez on 28/03/2016.
 */
public class SecondLevelNotificaciones extends BaseExpandableListAdapter
{
    Context context;
    private List<String> _listDataHeaderSecondLevel;
    private HashMap<String, List<Notificacion>> _listDataChild;
    private TextView fechaDia,fechaMes,fechaYear, descripcion ,nombreEmpresa,lblListId;
    private Notificacion notificacion;
    private IRepository repository = new Repository();

    public SecondLevelNotificaciones(Context context, List<String> listDataHeaderSecondLevel, HashMap<String, List<Notificacion>> listChildData){
        this.context = context;
        this._listDataChild = listChildData;
        this._listDataHeaderSecondLevel = listDataHeaderSecondLevel;
    }

    @Override
    public Object getChild(int groupPosition, int childPosititon)
    {
        return this._listDataChild.get(this._listDataHeaderSecondLevel.get(groupPosition))
                .get(childPosititon);
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


        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this.context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.list_item_notificacioes, null);
        }
            notificacion = (Notificacion)getChild(groupPosition, childPosition);
            fechaDia = (TextView) convertView.findViewById(R.id.lblListItemFechaDia);
            fechaMes = (TextView) convertView.findViewById(R.id.lblListItemFechaMes);
            fechaYear = (TextView) convertView.findViewById(R.id.lblListItemFechaYear);
            descripcion = (TextView) convertView.findViewById(R.id.lblListItemDescripcion);
            nombreEmpresa = (TextView) convertView.findViewById(R.id.lblListItemEmpresa);
            lblListId = (TextView) convertView.findViewById(R.id.lblListId);

            fechaDia.setText(notificacion.fecha.substring(8, 10));
            fechaMes.setText(notificacion.fecha.substring(5, 7));
            fechaYear.setText(notificacion.fecha.substring(0, 4));
            descripcion.setText(notificacion.nombre);
            nombreEmpresa.setText(notificacion.nombreEmpresa);
            lblListId.setText(notificacion.id);

            ImageButton editar = (ImageButton) convertView.findViewById(R.id.imageButtonEdit);
            editar.setOnClickListener(new View.OnClickListener() {

                public void onClick(View v) {

                    View parent = (View)v.getParent();
                    if(parent!=null){
                        TextView txtView = (TextView) parent.findViewById(R.id.lblListId);
                        ArrayList<Notificacion> notificaciones = Preferences.getNotificaciones(context);
                        for(int index =0;index<notificaciones.size();index++) {

                            if (notificaciones.get(index).id.equals(txtView.getText().toString())) {
                                Intent intentNotificaciones = new Intent(context, NotificacionesActivity.class);
                                intentNotificaciones.putExtra("notificacion", notificaciones.get(index));
                                context.startActivity(intentNotificaciones);
                            }
                        }
                    }
                }
            });

        CheckBox cumplido = (CheckBox) convertView.findViewById(R.id.checkBoxCumplio);
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


                TextView txtView = (TextView) parent.findViewById(R.id.lblListId);
                NotificacionCumplio notificacionCumplio = new NotificacionCumplio(txtView.getText().toString(), cumplio, Constantes.NOTIFICACIONES_CUMPLIO);
                repository.createRequets(context, notificacionCumplio, Constantes.NOTIFICACIONES_CUMPLIO);
            }
        });




        return convertView;
    }

    @Override
    public int getChildrenCount(int groupPosition)
    {
        if(this._listDataChild.get(this._listDataHeaderSecondLevel.get(groupPosition))!=null)
            return this._listDataChild.get(this._listDataHeaderSecondLevel.get(groupPosition)).size();
        else
            return 0;
    }

    @Override
    public Object getGroup(int groupPosition)
    {
        return this._listDataHeaderSecondLevel.get(groupPosition);
    }

    @Override
    public int getGroupCount()
    {
        return this._listDataHeaderSecondLevel.size();
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
            lblListHeader.setBackgroundColor(context.getResources().getColor(R.color.textbotones));
        }
        return convertView;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {

        return true;
    }
}
