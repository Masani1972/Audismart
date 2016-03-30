package com.aosas.audismart.adapters;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.ExpandableListView;

/**
 * Created by Lmartinez on 28/03/2016.
 */
public class CustExpListview extends ExpandableListView
{

    int intGroupPosition, intChildPosition, intGroupid;

    public CustExpListview(Context context)
    {
        super(context);
    }

    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec)
    {
      //  widthMeasureSpec = MeasureSpec.makeMeasureSpec(960, MeasureSpec.AT_MOST);
        //heightMeasureSpec = MeasureSpec.makeMeasureSpec(1200, MeasureSpec.AT_MOST);
       // super.onMeasure(widthMeasureSpec, heightMeasureSpec);

            // Calculate entire height by providing a very large height hint.
            // But do not use the highest 2 bits of this integer; those are
            // reserved for the MeasureSpec mode.
            int expandSpec = MeasureSpec.makeMeasureSpec(
                    Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST);
            super.onMeasure(widthMeasureSpec, expandSpec);

            ViewGroup.LayoutParams params = getLayoutParams();
            params.height = getMeasuredHeight();
        }

    }
