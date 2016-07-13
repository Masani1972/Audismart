package com.aosas.audismart.adapters;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.TextView;
import android.widget.Toast;

import com.aosas.audismart.R;
import com.aosas.audismart.model.Notificacion;
import com.aosas.audismart.util.alarm.ScheduleClient;

import java.util.HashMap;
import java.util.List;

/**
 * Created by Lmartinez on 28/03/2016.
 */
public class ParentLevelNotificaciones extends BaseExpandableListAdapter
{
    private Context context;
    private List<String> _listDataHeader;
    private List<String> _listDataHeaderSecondLevel;
    private HashMap<String, List<Notificacion>> _listDataChild;

    public ParentLevelNotificaciones(Context context, List<String> listDataHeader, HashMap<String, List<Notificacion>> listChildData, List<String> listDataHeaderSecondLevel){
        this.context = context;
        this._listDataHeader = listDataHeader;
        this._listDataChild = listChildData;
        this._listDataHeaderSecondLevel = listDataHeaderSecondLevel;
    }

    @Override
    public Object getChild(int groupPosition, int childPosititon)
    {
        return this._listDataChild.get(this._listDataHeader.get(groupPosition))
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


        CustExpListview SecondLevelexplv = new CustExpListview(context);
        SecondLevelexplv.setAdapter(new SecondLevelNotificaciones(context,_listDataHeaderSecondLevel,_listDataChild));
        SecondLevelexplv.setGroupIndicator(null);



        // Listview on child click listener
        SecondLevelexplv.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {

            @Override
            public boolean onChildClick(ExpandableListView parent, View v,
                                        int groupPosition, int childPosition, long id) {

                Toast.makeText(context,
                        groupPosition + " Collapsed",
                        Toast.LENGTH_SHORT).show();
                return false;
            }
        });

        return SecondLevelexplv;
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
            case 2:
                return context.getResources().getColor(R.color.textperfil);
            default:
                return 0;
        }
    }


}