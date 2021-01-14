package com.smart.library.utils;

import android.content.Context;

/**
 * @author  guobihai
 * @date 2019/3/20
 */
public class AppContext {
    private boolean isDebug = false;//BuildConfig.DEBUG;

    private static Context context = null;
    private static AppContext appContext = null;

    public AppContext() {
    }

    public AppContext(Context context) {
        AppContext.context = context;
    }

    public static AppContext getInstance(){
        if(appContext == null){
            appContext = new AppContext();
        }
        return appContext;
    }

    public static Context getAppContext(){
        return AppContext.context;
    }


}
