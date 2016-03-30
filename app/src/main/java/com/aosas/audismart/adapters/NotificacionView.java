package com.aosas.audismart.adapters;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.aosas.audismart.R;

/**
 * Created by Lmartinez on 29/03/2016.
 */
public class NotificacionView extends LinearLayout {

    private View mValue;
    private TextView title;

    public NotificacionView(Context context, AttributeSet attrs) {
        super(context, attrs);

        TypedArray a = context.obtainStyledAttributes(attrs,R.styleable.ColorOptionsView, 0, 0);
        //String titleText = a.getString(R.styleable.ColorOptionsView_titleText);
        int valueColor = a.getColor(R.styleable.ColorOptionsView_valueColor,Color.GRAY);
        a.recycle();

        setOrientation(LinearLayout.HORIZONTAL);
        setGravity(Gravity.CENTER_VERTICAL);

        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.view_color_options, this, true);

        title = (TextView) getChildAt(0);
       // title.setText(titleText);

        mValue = getChildAt(1);
        mValue.setBackgroundColor(valueColor);

    }

    public NotificacionView(Context context) {
        this(context, null);
    }

    public void setText(String text) {
        title.setText(text);
    }


}

