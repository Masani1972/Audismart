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
import com.aosas.audismart.model.Ciudad;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lmartinez on 21/01/2016.
 */
public class AutocompleteCiudadAdapter extends ArrayAdapter<Ciudad> {

    private final Context mContext;
    private final List<Ciudad> mCiudad;
    private final List<Ciudad> mCiudad_All;
    private final List<Ciudad> mCiudad_Suggestion;
    private final int mLayoutResourceId;

    public AutocompleteCiudadAdapter(Context context, int resource, List<Ciudad> ciudad) {
        super(context, resource, ciudad);
        this.mContext = context;
        this.mLayoutResourceId = resource;
        this.mCiudad = new ArrayList<>(ciudad);
        this.mCiudad_All = new ArrayList<>(ciudad);
        this.mCiudad_Suggestion = new ArrayList<>();
    }

    public int getCount() {
        return mCiudad.size();
    }

    public Ciudad getItem(int position) {
        return mCiudad.get(position);
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
            Ciudad ciudad = getItem(position);
            TextView name = (TextView) convertView.findViewById(R.id.autoText);
            name.setText(ciudad.Nombre);

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
                return ((Ciudad) resultValue).Nombre;
            }

            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                if (constraint != null) {
                    mCiudad_Suggestion.clear();
                    for (Ciudad ciudad : mCiudad_All) {
                        if (ciudad.Nombre.toLowerCase().startsWith(constraint.toString().toLowerCase())) {
                            mCiudad_Suggestion.add(ciudad);
                        }
                    }
                    FilterResults filterResults = new FilterResults();
                    filterResults.values = mCiudad_Suggestion;
                    filterResults.count = mCiudad_Suggestion.size();
                    return filterResults;
                } else {
                    return new FilterResults();
                }
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                mCiudad.clear();
                if (results != null && results.count > 0) {
                    List<?> result = (List<?>) results.values;
                    for (Object object : result) {
                        if (object instanceof Ciudad) {
                            mCiudad.add((Ciudad) object);
                        }
                    }
                } else if (constraint == null) {
                    // no filter, add entire original list back in
                    mCiudad.addAll(mCiudad_All);
                }
                notifyDataSetChanged();
            }
        };
    }
}
