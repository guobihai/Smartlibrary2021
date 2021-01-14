package com.smart.textrunapp.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.smart.textrunapp.R;
import com.smart.textrunapp.databinding.BannerImageBinding;

import java.util.List;

public class TextLayout extends FrameLayout {
    public TextLayout(@NonNull Context context) {
        super(context);
        init();
    }

    public TextLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs, 0);
        init();
    }

    public TextLayout(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();


    }

    private void init() {
        BannerImageBinding binding = BannerImageBinding.bind(inflate(getContext(), R.layout.banner_image, this));
    }
}
