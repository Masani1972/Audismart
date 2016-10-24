package com.aosas.audismart.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.aosas.audismart.R;
import com.aosas.audismart.activitys.CrearTicketActivity;
import com.aosas.audismart.activitys.TicketActivity;
import com.aosas.audismart.comunication.proxy.IRepository;
import com.aosas.audismart.comunication.proxy.Repository;
import com.aosas.audismart.model.Ticket;
import com.aosas.audismart.repository.Preferences;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


/**
 * The type Second level tickets.
 * Maneja la lista desplegable de tickets
 */
public class SecondLevelTickets extends BaseExpandableListAdapter {

    private Context context;
    private final static String TAG = "SecondLevelTickets";
    private List<String> _listDataHeaderSecondLevel;
    private HashMap<String, List<Ticket>> _listDataChild;
    private TextView fechaDia, fechaMes, fechaYear, descripcion, nombreEmpresa, lblListId;
    private Ticket ticket;
    private IRepository repository = new Repository();


    /**
     * Instantiates a new Second level tickets.
     *
     * @param context        the context
     * @param listDataHeader the list data header
     * @param listChildData  the list child data
     */
    public SecondLevelTickets(Context context, List<String> listDataHeader, HashMap<String, List<Ticket>> listChildData) {
        this.context = context;
        this._listDataChild = listChildData;
        this._listDataHeaderSecondLevel = listDataHeader;

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
            convertView = infalInflater.inflate(R.layout.list_item_ticket, null);
        }
        ticket = (Ticket) getChild(groupPosition, childPosition);
        fechaDia = (TextView) convertView.findViewById(R.id.lblListItemFechaDia);
        fechaMes = (TextView) convertView.findViewById(R.id.lblListItemFechaMes);
        fechaYear = (TextView) convertView.findViewById(R.id.lblListItemFechaYear);
        descripcion = (TextView) convertView.findViewById(R.id.lblListItemDescripcion);
        nombreEmpresa = (TextView) convertView.findViewById(R.id.lblListItemEmpresa);
        lblListId = (TextView) convertView.findViewById(R.id.lblListId);

        fechaDia.setText(ticket.fecha.substring(8, 10));
        fechaMes.setText(ticket.fecha.substring(5, 7));
        fechaYear.setText(ticket.fecha.substring(0, 4));
        descripcion.setText(ticket.titulo);
        nombreEmpresa.setText(ticket.empresa);
        lblListId.setText(ticket.id_ticket);

        ImageButton editar = (ImageButton) convertView.findViewById(R.id.imageButtonEdit);
        editar.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                View parent = (View) v.getParent();
                if (parent != null) {
                    TextView txtView = (TextView) parent.findViewById(R.id.lblListId);
                    ArrayList<Ticket> tickets = Preferences.getTickets(context);
                    for (int index = 0; index < tickets.size(); index++) {

                        if (tickets.get(index).id_ticket.equals(txtView.getText().toString())) {
                            Intent intentTicket = new Intent(context, TicketActivity.class);
                            intentTicket.putExtra("ticket", tickets.get(index));
                            context.startActivity(intentTicket);
                        }
                    }
                }
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
            return this._listDataChild.get(this._listDataHeaderSecondLevel.get(groupPosition))
                    .size();
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
            convertView = infalInflater.inflate(R.layout.list_group_newticket, null);
            LinearLayout layout_NewTicket = (LinearLayout) convertView.findViewById(R.id.layout_NewTicket);
            layout_NewTicket.setOnClickListener(new View.OnClickListener() {

                public void onClick(View v) {
                    Intent intentCalendario = new Intent(context, CrearTicketActivity.class);
                    context.startActivity(intentCalendario);

                }
            });
            return convertView;
        } else {
            String headerTitle = (String) getGroup(groupPosition);
            convertView = infalInflater.inflate(R.layout.list_group_secondlevel, null);
            TextView lblListHeader = (TextView) convertView.findViewById(R.id.lblListHeader);
            lblListHeader.setText(headerTitle);
            return convertView;
        }
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
