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
import com.aosas.audismart.model.Empresa;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lmartinez on 20/04/2016.
 */
public class AutocompleteEmpresaAdapter extends ArrayAdapter<Empresa> {

    private final Context mContext;
    private final List<Empresa> mEmpresa;
    private final List<Empresa> mEmpresa_All;
    private final List<Empresa> mEmpresa_Suggestion;
    private final int mLayoutResourceId;

    public AutocompleteEmpresaAdapter(Context context, int resource, List<Empresa> empresa) {
        super(context, resource, empresa);
        this.mContext = context;
        this.mLayoutResourceId = resource;
        this.mEmpresa = new ArrayList<>(empresa);
        this.mEmpresa_All = new ArrayList<>(empresa);
        this.mEmpresa_Suggestion = new ArrayList<>();
    }

    public int getCount() {
        return mEmpresa.size();
    }

    public Empresa getItem(int position) {
        return mEmpresa.get(position);
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
            Empresa empresa = getItem(position);
            TextView name = (TextView) convertView.findViewById(R.id.autoText);
            name.setText(empresa.nombre);

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
                return ((Empresa) resultValue).nombre;
            }

            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                if (constraint != null) {
                    mEmpresa_Suggestion.clear();
                    for (Empresa empresa : mEmpresa_All) {
                        if (empresa.nombre.toLowerCase().startsWith(constraint.toString().toLowerCase())) {
                            mEmpresa_Suggestion.add(empresa);
                        }
                    }
                    FilterResults filterResults = new FilterResults();
                    filterResults.values = mEmpresa_Suggestion;
                    filterResults.count = mEmpresa_Suggestion.size();
                    return filterResults;
                } else {
                    return new FilterResults();
                }
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                mEmpresa.clear();
                if (results != null && results.count > 0) {
                    List<?> result = (List<?>) results.values;
                    for (Object object : result) {
                        if (object instanceof Empresa) {
                            mEmpresa.add((Empresa) object);
                        }
                    }
                } else if (constraint == null) {
                    mEmpresa.addAll(mEmpresa_All);
                }
                notifyDataSetChanged();
            }
        };
    }
}