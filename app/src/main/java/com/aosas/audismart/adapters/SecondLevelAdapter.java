package com.aosas.audismart.adapters;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.aosas.audismart.R;
import com.aosas.audismart.activitys.MenuPrincipalActivity;

import java.util.HashMap;
import java.util.List;

/**
 * Created by Lmartinez on 28/03/2016.
 */
public class SecondLevelAdapter extends BaseExpandableListAdapter
{
    Context context;
    private List<String> _listDataHeaderSecondLevel;
    private HashMap<String, List<String>> _listDataChild;

    public SecondLevelAdapter (Context context,List<String> listDataHeaderSecondLevel,HashMap<String, List<String>> listChildData){
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
        final String childText = (String) getChild(groupPosition, childPosition);

        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this.context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.list_item, null);
        }

        NotificacionView txtListChild = (NotificacionView) convertView
                .findViewById(R.id.lblListItem);

        txtListChild.setText(childText);
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
