package com.aosas.audismart.adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.os.Parcelable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.aosas.audismart.R;
import com.aosas.audismart.activitys.MenuPrincipalActivity;
import com.aosas.audismart.activitys.NotificacionesActivity;
import com.aosas.audismart.model.Notificacion;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Lmartinez on 28/03/2016.
 */
public class SecondLevelAdapter extends BaseExpandableListAdapter
{
    Context context;
    private List<String> _listDataHeaderSecondLevel;
    private HashMap<String, List<Notificacion>> _listDataChild;
    private TextView fecha, descripcion ,nombreEmpresa;
    private Notificacion notificacion;

    public SecondLevelAdapter (Context context,List<String> listDataHeaderSecondLevel,HashMap<String, List<Notificacion>> listChildData){
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

         notificacion = (Notificacion)getChild(groupPosition, childPosition);

        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this.context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.list_item, null);
        }

        fecha = (TextView) convertView.findViewById(R.id.lblListItemFecha);
        descripcion = (TextView) convertView.findViewById(R.id.lblListItemDescripcion);
        nombreEmpresa = (TextView) convertView.findViewById(R.id.lblListItemEmpresa);

        fecha.setText(notificacion.fecha.substring(0, 10));
        descripcion.setText(notificacion.nombre);
        nombreEmpresa.setText(notificacion.nombreEmpresa);

        ImageButton editar = (ImageButton) convertView.findViewById(R.id.imageButtonEdit);
        editar.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Intent intentNotificaciones = new Intent(context, NotificacionesActivity.class);
                intentNotificaciones.putExtra("notificacion", (Parcelable) notificacion);
                context.startActivity(intentNotificaciones);
            }
        });
        return convertView;
    }

    @Override
    public int getChildrenCount(int groupPosition)
    {
        return   this._listDataChild.get(this._listDataHeaderSecondLevel.get(groupPosition))
            .size();
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
