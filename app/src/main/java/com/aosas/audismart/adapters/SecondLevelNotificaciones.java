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
import com.aosas.audismart.activitys.CalendarioActivity;
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
 * The type Second level notificaciones.
 * Maneja la lista desplegable de las notificaciones
 */
public class SecondLevelNotificaciones extends BaseExpandableListAdapter {
    /**
     * The Context.
     */
    private Context context;
    private List<String> _listDataHeaderSecondLevel;
    private HashMap<String, List<Notificacion>> _listDataChild;
    private TextView fechaDia, fechaMes, fechaYear, descripcion, nombreEmpresa, lblListId;
    private Notificacion notificacion;
    private IRepository repository = new Repository();

    /**
     * Instantiates a new Second level notificaciones.
     *
     * @param context                   the context
     * @param listDataHeaderSecondLevel the list data header second level
     * @param listChildData             the list child data
     */
    public SecondLevelNotificaciones(Context context, List<String> listDataHeaderSecondLevel, HashMap<String, List<Notificacion>> listChildData) {
        this.context = context;
        this._listDataChild = listChildData;
        this._listDataHeaderSecondLevel = listDataHeaderSecondLevel;
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
        return this._listDataChild.get(this._listDataHeaderSecondLevel.get(groupPosition))
                .get(childPosititon);
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


        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this.context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.list_item_notificacioes, null);
        }
        notificacion = (Notificacion) getChild(groupPosition, childPosition);
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

                View parent = (View) v.getParent();
                if (parent != null) {
                    TextView txtView = (TextView) parent.findViewById(R.id.lblListId);
                    ArrayList<Notificacion> notificaciones = Preferences.getNotificaciones(context);
                    for (int index = 0; index < notificaciones.size(); index++) {

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

    /**
     * Gets children count.
     *
     * @param groupPosition the group position
     * @return the children count
     */
    @Override
    public int getChildrenCount(int groupPosition) {
        if (this._listDataChild.get(this._listDataHeaderSecondLevel.get(groupPosition)) != null)
            return this._listDataChild.get(this._listDataHeaderSecondLevel.get(groupPosition)).size();
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
        return this._listDataHeaderSecondLevel.get(groupPosition);
    }

    /**
     * Gets group count.
     *
     * @return the group count
     */
    @Override
    public int getGroupCount() {
        return this._listDataHeaderSecondLevel.size() + 1;
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
        LayoutInflater infalInflater = (LayoutInflater) this.context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (groupPosition == 4) {
            convertView = infalInflater.inflate(R.layout.list_group_calendario, null);
            ImageButton button = (ImageButton) convertView.findViewById(R.id.button_Calendario);
            button.setOnClickListener(new View.OnClickListener() {

                public void onClick(View v) {
                    Intent intentCalendario = new Intent(context, CalendarioActivity.class);
                    context.startActivity(intentCalendario);

                }
            });
        } else {
            String headerTitle = (String) getGroup(groupPosition);
            convertView = infalInflater.inflate(R.layout.list_group_secondlevel, null);
            TextView lblListHeader = (TextView) convertView.findViewById(R.id.lblListHeader);
            lblListHeader.setText(headerTitle);

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
}
