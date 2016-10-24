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
import com.aosas.audismart.model.Departamento;

import java.util.ArrayList;
import java.util.List;

/**
 * The type Autocomplete departamento adapter.
 *  Adaptador para la lista de departamentos
 */
public class AutocompleteDepartamentoAdapter extends ArrayAdapter<Departamento> {

    private final Context mContext;
    private final List<Departamento> mDepartments;
    private final List<Departamento> mDepartments_All;
    private final List<Departamento> mDepartments_Suggestion;
    private final int mLayoutResourceId;

    /**
     * Instantiates a new Autocomplete departamento adapter.
     *
     * @param context     the context
     * @param resource    the resource
     * @param departments the departments
     */
    public AutocompleteDepartamentoAdapter(Context context, int resource, List<Departamento> departments) {
        super(context, resource, departments);
        this.mContext = context;
        this.mLayoutResourceId = resource;
        this.mDepartments = new ArrayList<>(departments);
        this.mDepartments_All = new ArrayList<>(departments);
        this.mDepartments_Suggestion = new ArrayList<>();
    }

    /**
     * Gets count.
     *
     * @return the count
     */
    public int getCount() {
        return mDepartments.size();
    }

    /**
     * Gets item.
     *
     * @param position the position
     * @return the item
     */
    public Departamento getItem(int position) {
        return mDepartments.get(position);
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
            Departamento department = getItem(position);
            TextView name = (TextView) convertView.findViewById(R.id.autoText);
            name.setText(department.Nombre);

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
                return ((Departamento) resultValue).Nombre;
            }

            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                if (constraint != null) {
                    mDepartments_Suggestion.clear();
                    for (Departamento department : mDepartments_All) {
                        if (department.Nombre.toLowerCase().startsWith(constraint.toString().toLowerCase())) {
                            mDepartments_Suggestion.add(department);
                        }
                    }
                    FilterResults filterResults = new FilterResults();
                    filterResults.values = mDepartments_Suggestion;
                    filterResults.count = mDepartments_Suggestion.size();
                    return filterResults;
                } else {
                    return new FilterResults();
                }
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                mDepartments.clear();
                if (results != null && results.count > 0) {
                    List<?> result = (List<?>) results.values;
                    for (Object object : result) {
                        if (object instanceof Departamento) {
                            mDepartments.add((Departamento) object);
                        }
                    }
                } else if (constraint == null) {
                    mDepartments.addAll(mDepartments_All);
                }
                notifyDataSetChanged();
            }
        };
    }
}


