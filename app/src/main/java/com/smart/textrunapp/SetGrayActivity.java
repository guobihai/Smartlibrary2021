package com.smart.textrunapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewbinding.ViewBinding;

import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;

import com.smart.textrunapp.databinding.ActivitySetGrayBinding;

/**
 * @author guobihai
 * 创建日期：2021/1/18
 * desc：把Activity 置灰
 */
public class SetGrayActivity extends AppCompatActivity {
    Paint paint = new Paint();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivitySetGrayBinding viewBinding = ActivitySetGrayBinding.inflate(getLayoutInflater());
        setContentView(viewBinding.getRoot());
        viewBinding.btnSetGray.setOnClickListener(v -> {
            setGray();
        });
        viewBinding.restSetGray.setOnClickListener(v -> {
            resetGray();
        });
    }

    /**
     * 置灰
     */
    void setGray() {
       float[] array ={
               -1, 0, 0, 0, 255,
               0, -1, 0, 0, 255,
               0, 0, -1, 0, 255,
               0, 0, 0, 1, 0};
        View view = this.getWindow().getDecorView();
        ColorMatrix cm = new ColorMatrix();
        //设置饱和度
        cm.setSaturation(0);
        cm.set(array);
        paint.setColorFilter(new ColorMatrixColorFilter(cm));
        view.setLayerType(View.LAYER_TYPE_HARDWARE, paint);
    }

    /**
     * 置灰
     */
    void resetGray() {
        View view = this.getWindow().getDecorView();
        ColorMatrix cm = new ColorMatrix();
        cm.setSaturation(0);
        paint.setColorFilter(null);
        view.setLayerType(View.LAYER_TYPE_HARDWARE, paint);
    }
}