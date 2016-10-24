package com.aosas.audismart.adapters;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.aosas.audismart.R;
import com.aosas.audismart.model.Notificacion;
import com.aosas.audismart.model.Ticket;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;


/**
 * The type Level menu principal.
 * Maneja las listas desplegables de primer nivel
 * del menú principal
 */
public class LevelMenuPrincipal extends BaseExpandableListAdapter {
    private Context context;
    private List<String> listDataHeader;
    private List<String> _listDataHeaderLevelNotifications;
    private HashMap<String, List<Notificacion>> _listDataChildNotificaciones;
    private List<String> listDataHeaderLevelProfile;
    private List<String> _listDataHeaderLevelTickets;
    private HashMap<String, List<Ticket>> _listDataChildTickets;

    /**
     * Instantiates a new Level menu principal.
     *
     * @param context                          the context
     * @param listChildDataNotificaciones      the list child data notificaciones
     * @param listDataHeaderLevelNotifications the list data header level notifications
     * @param listChildDataTickets             the list child data tickets
     * @param listDataHeaderLevelTickets       the list data header level tickets
     */
    public LevelMenuPrincipal(Context context, HashMap<String, List<Notificacion>> listChildDataNotificaciones, List<String> listDataHeaderLevelNotifications, HashMap<String, List<Ticket>> listChildDataTickets, List<String> listDataHeaderLevelTickets) {
        this.context = context;
        intilist();
        this._listDataChildNotificaciones = listChildDataNotificaciones;
        this._listDataHeaderLevelNotifications = listDataHeaderLevelNotifications;
        this._listDataHeaderLevelTickets = listDataHeaderLevelTickets;
        this._listDataChildTickets = listChildDataTickets;
    }

    private void intilist() {
        listDataHeader = Arrays.asList(context.getResources().getStringArray(R.array.title_menuprincipal));
        listDataHeaderLevelProfile = Arrays.asList(context.getResources().getStringArray(R.array.title_perfil));

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
        return this._listDataChildNotificaciones.get(this.listDataHeader.get(groupPosition))
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
        if (groupPosition == 0) {

            CustExpListview SecondLevelexplv = new CustExpListview(context);
            SecondLevelexplv.setAdapter(new SecondLevelNotificaciones(context, _listDataHeaderLevelNotifications, _listDataChildNotificaciones));
            SecondLevelexplv.setGroupIndicator(null);

            return SecondLevelexplv;
        } else if (groupPosition == 1) {
            CustExpListview SecondLevelexplv = new CustExpListview(context);
            SecondLevelexplv.setAdapter(new SecondLevelTickets(context, _listDataHeaderLevelTickets, _listDataChildTickets));
            SecondLevelexplv.setGroupIndicator(null);
            return SecondLevelexplv;
        } else if (groupPosition == 2) {
            CustExpListview SecondLevelexplv = new CustExpListview(context);
            SecondLevelexplv.setAdapter(new SecondLevelProfile(context, listDataHeaderLevelProfile, true));
            SecondLevelexplv.setGroupIndicator(null);
            return SecondLevelexplv;
        }

        return null;
    }

    /**
     * Gets children count.
     *
     * @param groupPosition the group position
     * @return the children count
     */
    @Override
    public int getChildrenCount(int groupPosition) {
        return 1;
    }

    /**
     * Gets group.
     *
     * @param groupPosition the group position
     * @return the group
     */
    @Override
    public Object getGroup(int groupPosition) {
        return this.listDataHeader.get(groupPosition);
    }

    /**
     * Gets group count.
     *
     * @return the group count
     */
    @Override
    public int getGroupCount() {
        return this.listDataHeader.size();
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
            case 2:
                return context.getResources().getColor(R.color.textperfil);
            default:
                return 0;
        }
    }


}