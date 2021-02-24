package com.smart.textrunapp.camara2.filter;

import android.content.Context;
import android.opengl.GLES11Ext;
import android.opengl.GLES20;

import com.smart.textrunapp.R;


public class ScreenFilter extends BaseFilter {


    public ScreenFilter(Context mContext) {
        super(mContext, R.raw.screen_vert, R.raw.screen_frag);

    }


}
