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
import com.aosas.audismart.model.Periodicidad;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by intergrupo on 13/07/16.
 */

public class AutocompletePeriodicidadAdapter extends ArrayAdapter<Periodicidad> {

    private final Context mContext;
    private final List<Periodicidad> mPeriodicidad;
    private final List<Periodicidad> mPeriodicidad_All;
    private final List<Periodicidad> mPeriodicidad_Suggestion;
    private final int mLayoutResourceId;

    public AutocompletePeriodicidadAdapter(Context context, int resource, List<Periodicidad> periodicidad) {
        super(context, resource, periodicidad);
        this.mContext = context;
        this.mLayoutResourceId = resource;
        this.mPeriodicidad = new ArrayList<>(periodicidad);
        this.mPeriodicidad_All = new ArrayList<>(periodicidad);
        this.mPeriodicidad_Suggestion = new ArrayList<>();
    }

    public int getCount() {
        return mPeriodicidad.size();
    }

    public Periodicidad getItem(int position) {
        return mPeriodicidad.get(position);
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
            Periodicidad categoria = getItem(position);
            TextView name = (TextView) convertView.findViewById(R.id.autoText);
            name.setText(categoria.nombre);

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
                return ((Periodicidad) resultValue).nombre;
            }

            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                if (constraint != null) {
                    mPeriodicidad_Suggestion.clear();
                    for (Periodicidad periodicidad : mPeriodicidad_All) {
                        if (periodicidad.nombre.toLowerCase().startsWith(constraint.toString().toLowerCase())) {
                            mPeriodicidad_Suggestion.add(periodicidad);
                        }
                    }
                    FilterResults filterResults = new FilterResults();
                    filterResults.values = mPeriodicidad_Suggestion;
                    filterResults.count = mPeriodicidad_Suggestion.size();
                    return filterResults;
                } else {
                    return new FilterResults();
                }
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                mPeriodicidad.clear();
                if (results != null && results.count > 0) {
                    List<?> result = (List<?>) results.values;
                    for (Object object : result) {
                        if (object instanceof Periodicidad) {
                            mPeriodicidad.add((Periodicidad) object);
                        }
                    }
                } else if (constraint == null) {
                    mPeriodicidad.addAll(mPeriodicidad_All);
                }
                notifyDataSetChanged();
            }
        };
    }
}