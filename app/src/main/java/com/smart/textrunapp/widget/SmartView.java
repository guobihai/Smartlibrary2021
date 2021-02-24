package com.smart.textrunapp.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.ViewGroup;

public class SmartView extends ViewGroup {
    public SmartView(Context context) {
        super(context);
    }

    public SmartView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SmartView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }
}
