package com.smart.textrunapp.app;

import android.app.Application;

import com.idlefish.flutterboost.Utils;
import com.idlefish.flutterboost.init.FlutterInit;
import com.smart.textrunapp.flutter.PageRouter;
import com.smart.library.network.RetrofitManager;
import com.smart.library.utils.AppContext;
import com.smart.textrunapp.BuildConfig;


import io.flutter.embedding.engine.FlutterEngine;
import io.flutter.embedding.engine.FlutterEngineCache;
import io.flutter.embedding.engine.dart.DartExecutor;
import io.flutter.view.FlutterMain;

public class SmartApplication extends Application {
    private FlutterEngine flutterEngine;

    @Override
    public void onCreate() {
        super.onCreate();
        new AppContext(this);
        RetrofitManager.getInstance().init(BuildConfig.BASE_HOST);

        /**
         * 初始化闲鱼-flutter 引擎
         */
        FlutterInit.initFlutterBoost(this, (context, url, urlParams, requestCode, exts) -> {
            //解析 flutter 调用原生界面路由跳转
            String assembleUrl = Utils.assembleUrl(url, urlParams);
            PageRouter.openPageByUrl(context, assembleUrl, urlParams);
        });
    }

    /**
     * 初始化原生
     */
    private void initSmartFlutter() {
        FlutterMain.startInitialization(this);
        flutterEngine = new FlutterEngine(this);
        flutterEngine.getNavigationChannel().setInitialRoute("main_page");
//        初始化flutter引擎，为了复用

        flutterEngine.getDartExecutor().executeDartEntrypoint(DartExecutor.DartEntrypoint.createDefault());
        FlutterEngineCache
                .getInstance()
                .put("engine_id", flutterEngine);

    }


    @Override
    public void onTerminate() {
        super.onTerminate();
        if (null != flutterEngine) {
            flutterEngine.destroy();
            flutterEngine = null;
        }
    }
}
