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
import com.aosas.audismart.model.Categoria;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lmartinez on 23/01/2016.
 */
public class AutocompleteCategoriaAdapter extends ArrayAdapter<Categoria> {

    private final Context mContext;
    private final List<Categoria> mCategoria;
    private final List<Categoria> mCategoria_All;
    private final List<Categoria> mCategoria_Suggestion;
    private final int mLayoutResourceId;

    public AutocompleteCategoriaAdapter(Context context, int resource, List<Categoria> categoria) {
        super(context, resource, categoria);
        this.mContext = context;
        this.mLayoutResourceId = resource;
        this.mCategoria = new ArrayList<>(categoria);
        this.mCategoria_All = new ArrayList<>(categoria);
        this.mCategoria_Suggestion = new ArrayList<>();
    }

    public int getCount() {
        return mCategoria.size();
    }

    public Categoria getItem(int position) {
        return mCategoria.get(position);
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
            Categoria categoria = getItem(position);
            TextView name = (TextView) convertView.findViewById(R.id.autoText);
            name.setText(categoria.nombre_Categoria);

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
                return ((Categoria) resultValue).nombre_Categoria;
            }

            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                if (constraint != null) {
                    mCategoria_Suggestion.clear();
                    for (Categoria categoria : mCategoria_All) {
                        if (categoria.nombre_Categoria.toLowerCase().startsWith(constraint.toString().toLowerCase())) {
                            mCategoria_Suggestion.add(categoria);
                        }
                    }
                    FilterResults filterResults = new FilterResults();
                    filterResults.values = mCategoria_Suggestion;
                    filterResults.count = mCategoria_Suggestion.size();
                    return filterResults;
                } else {
                    return new FilterResults();
                }
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                mCategoria.clear();
                if (results != null && results.count > 0) {
                    List<?> result = (List<?>) results.values;
                    for (Object object : result) {
                        if (object instanceof Categoria) {
                            mCategoria.add((Categoria) object);
                        }
                    }
                } else if (constraint == null) {
                    mCategoria.addAll(mCategoria_All);
                }
                notifyDataSetChanged();
            }
        };
    }
}

