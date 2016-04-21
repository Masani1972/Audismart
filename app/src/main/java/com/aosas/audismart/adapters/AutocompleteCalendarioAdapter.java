package com.aosas.audismart.adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.TextView;

import com.aosas.audismart.R;
import com.aosas.audismart.model.Calendario;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by lmartinez on 20/04/2016.
 */
public class AutocompleteCalendarioAdapter extends ArrayAdapter<Calendario> {

    private final Context mContext;
    private final List<Calendario> mCalendario;
    private final List<Calendario> mCalendario_All;
    private final List<Calendario> mCalendario_Suggestion;
    private final int mLayoutResourceId;

    public AutocompleteCalendarioAdapter(Context context, int resource, List<Calendario> calendario) {
        super(context, resource, calendario);
        this.mContext = context;
        this.mLayoutResourceId = resource;
        this.mCalendario = new ArrayList<>(calendario);
        this.mCalendario_All = new ArrayList<>(calendario);
        this.mCalendario_Suggestion = new ArrayList<>();
    }

    public int getCount() {
        return mCalendario.size();
    }

    public Calendario getItem(int position) {
        return mCalendario.get(position);
    }

    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        try {
            if (convertView == null) {
                LayoutInflater inflater = ((Activity) mContext).getLayoutInflater();
                convertView = inflater.inflate(mLayoutResourceId, parent, false);
            }
            Calendario calendario = getItem(position);
            TextView name = (TextView) convertView.findViewById(R.id.autoText);
            name.setText(calendario.nombre);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return convertView;
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            public String convertResultToString(Object resultValue) {
                return ((Calendario) resultValue).nombre;
            }

            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                if (constraint != null) {
                    mCalendario_Suggestion.clear();
                    for (Calendario calendario : mCalendario_All) {
                        if (calendario.nombre.toLowerCase().startsWith(constraint.toString().toLowerCase())) {
                            mCalendario_Suggestion.add(calendario);
                        }
                    }
                    FilterResults filterResults = new FilterResults();
                    filterResults.values = mCalendario_Suggestion;
                    filterResults.count = mCalendario_Suggestion.size();
                    return filterResults;
                } else {
                    return new FilterResults();
                }
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                mCalendario.clear();
                if (results != null && results.count > 0) {
                    List<?> result = (List<?>) results.values;
                    for (Object object : result) {
                        if (object instanceof Calendario) {
                            mCalendario.add((Calendario) object);
                        }
                    }
                } else if (constraint == null) {
                    mCalendario.addAll(mCalendario_All);
                }
                notifyDataSetChanged();
            }
        };
    }
}