package com.idlefish.flutterboost.init;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import com.idlefish.flutterboost.FlutterBoost;
import com.idlefish.flutterboost.FlutterBoostPlugin;
import com.idlefish.flutterboost.Platform;
import com.idlefish.flutterboost.interfaces.INativeRouter;
import com.smart.aliflutterboost.TextPlatformViewFactory;


import io.flutter.embedding.android.FlutterView;
import io.flutter.embedding.engine.plugins.shim.ShimPluginRegistry;
import io.flutter.plugin.common.StandardMessageCodec;
import io.flutter.plugin.platform.PlatformViewRegistry;

public class FlutterInit {
    /**
     * 初始化闲鱼
     */
    public static void initFlutterBoost(Application application, INativeRouter router) {
//        INativeRouter router = new INativeRouter() {
//            @Override
//            public void openContainer(Context context, String url, Map<String, Object> urlParams, int requestCode, Map<String, Object> exts) {
//                String assembleUrl = Utils.assembleUrl(url, urlParams);
//                PageRouter.openPageByUrl(context, assembleUrl, urlParams);
//            }
//
//        };
        if (null == application) throw new IllegalArgumentException("application is not null");
        if (null == router) throw new IllegalArgumentException("INativeRouter is not init");

        FlutterBoost.BoostLifecycleListener boostLifecycleListener = new FlutterBoost.BoostLifecycleListener() {

            @Override
            public void beforeCreateEngine() {
                Log.d("Engine", "beforeCreateEngine");
            }

            @Override
            public void onEngineCreated() {
                Log.d("Engine", "onEngineCreated");
            }

            @Override
            public void onPluginsRegistered() {
                Log.d("Engine", "onPluginsRegistered");
            }

            @Override
            public void onEngineDestroy() {
                Log.d("Engine", "onEngineDestroy");
            }

        };

        Platform platform = new FlutterBoost
                .ConfigBuilder(application, router)
                .isDebug(true)
                .whenEngineStart(FlutterBoost.ConfigBuilder.IMMEDIATELY)
                .renderMode(FlutterView.RenderMode.texture)
                .lifecycleListener(boostLifecycleListener)
                .build();

        FlutterBoost.instance().init(platform);

        // whenEngineStart(FlutterBoost.ConfigBuilder.IMMEDIATELY) 时候，engine才初始化好。
        if (FlutterBoost.instance().engineProvider() != null) {
            ShimPluginRegistry shimPluginRegistry = new ShimPluginRegistry(FlutterBoost.instance().engineProvider());
            FlutterBoostPlugin.registerWith(shimPluginRegistry.registrarFor(FlutterBoostPlugin.class.getSimpleName()));

            //flutter 界面添加原生view
//            PlatformViewRegistry registry = FlutterBoost.instance().engineProvider().getPlatformViewsController().getRegistry();
//            registry.registerViewFactory("plugins.test/view", new TextPlatformViewFactory(StandardMessageCodec.INSTANCE));
        }

    }
}
