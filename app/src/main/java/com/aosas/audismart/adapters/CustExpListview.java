package com.aosas.audismart.adapters;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.ExpandableListView;


/**
 * The type Cust exp listview.
 * Permite el manejo de dos niveles en las vistas de tipo
 * ExpandibleList
 */
public class CustExpListview extends ExpandableListView {
    /**
     * Instantiates a new Cust exp listview.
     *
     * @param context the context
     */
    public CustExpListview(Context context) {
        super(context);
    }

    /**
     * On measure.
     *
     * @param widthMeasureSpec  the width measure spec
     * @param heightMeasureSpec the height measure spec
     */
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int expandSpec = MeasureSpec.makeMeasureSpec(
                Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);

        ViewGroup.LayoutParams params = getLayoutParams();
        params.height = getMeasuredHeight();
    }

}
