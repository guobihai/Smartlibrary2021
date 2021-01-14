package com.smart.library.utils;

import android.app.Activity;
import android.app.Application;
import android.content.ComponentCallbacks;
import android.content.res.Configuration;
import android.util.DisplayMetrics;
/**
 * @author guobihai
 * 创建日期：2020/12/2
 * desc：屏幕适配
 *
 */
public class MyDisplayMetrics {
    private static float sNoncompatDensity;
    private static float sNoncompatScaledDensity;

    public static void setCustomDensity(Activity activity, final Application application){
        final DisplayMetrics appDisplayMetrics=application.getResources().getDisplayMetrics();
        if(sNoncompatDensity==0){
            sNoncompatDensity=appDisplayMetrics.density;
            sNoncompatScaledDensity=appDisplayMetrics.scaledDensity;
            application.registerComponentCallbacks(new ComponentCallbacks() {
                @Override
                public void onConfigurationChanged(Configuration newConfig) {
                    if(null!=newConfig&&newConfig.fontScale>0){
                        sNoncompatScaledDensity=application.getResources().getDisplayMetrics().scaledDensity;
                    }
                }

                @Override
                public void onLowMemory() {

                }
            });
        }

        final float targetDensity=appDisplayMetrics.widthPixels/360f;
        final float targetScaledDensity=targetDensity*(sNoncompatScaledDensity/sNoncompatDensity);
        final int targetDensityDpi= (int) (160*targetDensity);
        appDisplayMetrics.density=targetDensity;
        appDisplayMetrics.scaledDensity=targetScaledDensity;
        appDisplayMetrics.densityDpi=targetDensityDpi;

        final DisplayMetrics activityDisplayMetrics=activity.getResources().getDisplayMetrics();
        activityDisplayMetrics.density=targetDensity;
        activityDisplayMetrics.scaledDensity=targetScaledDensity;
        activityDisplayMetrics.densityDpi=targetDensityDpi;
    }

}
