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
 * Created by Lmartinez on 28/03/2016.
 */
public class LevelMenuPrincipal extends BaseExpandableListAdapter
{
    private Context context;
    private List<String> listDataHeader;
    private List<String> _listDataHeaderLevelNotifications;
    private HashMap<String, List<Notificacion>> _listDataChildNotificaciones;
    private List<String> listDataHeaderLevelProfile;
    private List<String> _listDataHeaderLevelTickets;
    private HashMap<String, List<Ticket>> _listDataChildTickets;

    public LevelMenuPrincipal(Context context, HashMap<String, List<Notificacion>> listChildDataNotificaciones, List<String> listDataHeaderLevelNotifications, HashMap<String, List<Ticket>> listChildDataTickets, List<String> listDataHeaderLevelTickets){
        this.context = context;
        intilist();
        this._listDataChildNotificaciones = listChildDataNotificaciones;
        this._listDataHeaderLevelNotifications = listDataHeaderLevelNotifications;
        this._listDataHeaderLevelTickets = listDataHeaderLevelTickets;
        this._listDataChildTickets = listChildDataTickets;
    }

    private void intilist() {
        listDataHeader = Arrays.asList(context.getResources().getStringArray(R.array.title_menuprincipal));
        listDataHeaderLevelProfile =Arrays.asList(context.getResources().getStringArray(R.array.title_perfil));

    }

    @Override
    public Object getChild(int groupPosition, int childPosititon)
    {
        return this._listDataChildNotificaciones.get(this.listDataHeader.get(groupPosition))
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
        if(groupPosition == 0) {

        CustExpListview SecondLevelexplv = new CustExpListview(context);
        SecondLevelexplv.setAdapter(new SecondLevelNotificaciones(context,_listDataHeaderLevelNotifications,_listDataChildNotificaciones));
        SecondLevelexplv.setGroupIndicator(null);

        return SecondLevelexplv;}
        else if(groupPosition == 1){
            CustExpListview SecondLevelexplv = new CustExpListview(context);
            SecondLevelexplv.setAdapter(new SecondLevelTickets(context,_listDataHeaderLevelTickets,_listDataChildTickets));
            SecondLevelexplv.setGroupIndicator(null);
            return SecondLevelexplv;
        }
        else if(groupPosition == 2){
            CustExpListview SecondLevelexplv = new CustExpListview(context);
            SecondLevelexplv.setAdapter(new SecondLevelProfile(context,listDataHeaderLevelProfile,true));
            SecondLevelexplv.setGroupIndicator(null);
            return SecondLevelexplv;
        }

        return null;
    }

    @Override
    public int getChildrenCount(int groupPosition)
    {
        return 1;
    }

    @Override
    public Object getGroup(int groupPosition)
    {
        return this.listDataHeader.get(groupPosition);
    }

    @Override
    public int getGroupCount()
    {
        return this.listDataHeader.size();
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
            case 2:
                return context.getResources().getColor(R.color.textperfil);
            default:
                return 0;
        }
    }


}