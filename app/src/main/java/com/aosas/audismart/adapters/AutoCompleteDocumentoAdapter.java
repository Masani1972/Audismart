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
import com.aosas.audismart.model.DocumentoIdentidad;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lmartinez on 24/01/2016.
 */
public class AutocompleteDocumentoAdapter extends ArrayAdapter<DocumentoIdentidad> {

    private final Context mContext;
    private final List<DocumentoIdentidad> mDocumento;
    private final List<DocumentoIdentidad> mDocumento_All;
    private final List<DocumentoIdentidad> mDocuemnto_Suggestion;
    private final int mLayoutResourceId;

    public AutocompleteDocumentoAdapter(Context context, int resource, List<DocumentoIdentidad> documentoIdentidad) {
        super(context, resource, documentoIdentidad);
        this.mContext = context;
        this.mLayoutResourceId = resource;
        this.mDocumento = new ArrayList<>(documentoIdentidad);
        this.mDocumento_All = new ArrayList<>(documentoIdentidad);
        this.mDocuemnto_Suggestion = new ArrayList<>();
    }

    public int getCount() {
        return mDocumento.size();
    }

    public DocumentoIdentidad getItem(int position) {
        return mDocumento.get(position);
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
            DocumentoIdentidad documentoIdentidad = getItem(position);
            TextView name = (TextView) convertView.findViewById(R.id.autoText);
            name.setText(documentoIdentidad.nombre);

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
                return ((DocumentoIdentidad) resultValue).nombre;
            }

            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                if (constraint != null) {
                    mDocuemnto_Suggestion.clear();
                    for (DocumentoIdentidad documentoIdentidad : mDocumento_All) {
                        if (documentoIdentidad.nombre.toLowerCase().startsWith(constraint.toString().toLowerCase())) {
                            mDocuemnto_Suggestion.add(documentoIdentidad);
                        }
                    }
                    FilterResults filterResults = new FilterResults();
                    filterResults.values = mDocuemnto_Suggestion;
                    filterResults.count = mDocuemnto_Suggestion.size();
                    return filterResults;
                } else {
                    return new FilterResults();
                }
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                mDocumento.clear();
                if (results != null && results.count > 0) {
                    List<?> result = (List<?>) results.values;
                    for (Object object : result) {
                        if (object instanceof DocumentoIdentidad) {
                            mDocumento.add((DocumentoIdentidad) object);
                        }
                    }
                } else if (constraint == null) {
                    mDocumento.addAll(mDocumento_All);
                }
                notifyDataSetChanged();
            }
        };
    }
}

