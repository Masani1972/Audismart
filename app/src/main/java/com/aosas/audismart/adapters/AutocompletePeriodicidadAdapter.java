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
 * The type Autocomplete periodicidad adapter.
 *  Adaptador para la lista de periodicidad
 */
public class AutocompletePeriodicidadAdapter extends ArrayAdapter<Periodicidad> {

    private final Context mContext;
    private final List<Periodicidad> mPeriodicidad;
    private final List<Periodicidad> mPeriodicidad_All;
    private final List<Periodicidad> mPeriodicidad_Suggestion;
    private final int mLayoutResourceId;

    /**
     * Instantiates a new Autocomplete periodicidad adapter.
     *
     * @param context      the context
     * @param resource     the resource
     * @param periodicidad the periodicidad
     */
    public AutocompletePeriodicidadAdapter(Context context, int resource, List<Periodicidad> periodicidad) {
        super(context, resource, periodicidad);
        this.mContext = context;
        this.mLayoutResourceId = resource;
        this.mPeriodicidad = new ArrayList<>(periodicidad);
        this.mPeriodicidad_All = new ArrayList<>(periodicidad);
        this.mPeriodicidad_Suggestion = new ArrayList<>();
    }

    /**
     * Gets count.
     *
     * @return the count
     */
    public int getCount() {
        return mPeriodicidad.size();
    }

    /**
     * Gets item.
     *
     * @param position the position
     * @return the item
     */
    public Periodicidad getItem(int position) {
        return mPeriodicidad.get(position);
    }

    /**
     * Gets item id.
     *
     * @param position the position
     * @return the item id
     */
    public long getItemId(int position) {
        return position;
    }

    /**
     * Gets view.
     *
     * @param position    the position
     * @param convertView the convert view
     * @param parent      the parent
     * @return the view
     */
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

    /**
     * Gets filter.
     *
     * @return the filter
     */
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