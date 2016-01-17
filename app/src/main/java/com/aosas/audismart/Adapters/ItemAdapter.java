package com.aosas.audismart.Adapters;

import android.content.Context;
import android.widget.ArrayAdapter;

import java.util.List;

/**
 * Created by Lmartinez on 16/01/2016.
 */
public class ItemAdapter extends ArrayAdapter {


    public ItemAdapter(Context context, int resource, List objects) {
        super(context, resource, objects);
    }
}
