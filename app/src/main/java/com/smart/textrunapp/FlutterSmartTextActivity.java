package com.smart.textrunapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.smart.textrunapp.annotation.ClickLimit;
import com.smart.textrunapp.flutter.FlutterRoutes;
import com.smart.textrunapp.flutter.PageRouter;
import com.smart.textrunapp.flutter.SmartFlutterActivity;
import com.smart.textrunapp.flutter.SmtFlutterActivity;

import java.util.HashMap;
import java.util.Map;

public class FlutterSmartTextActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_flutter_text);
        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @ClickLimit (value = 1000)
            @Override
            public void onClick(View v) {
                //默认启动模式
                startActivity(SmartFlutterActivity.createDefaultIntent(FlutterSmartTextActivity.this));
            }
        });

        findViewById(R.id.btnGallery).setOnClickListener(v -> {
            //指定路由启动模式
            Intent customFlutter = SmartFlutterActivity.withNewEngine()
                    .initialRoute(FlutterRoutes.buildRoutes("gallery_page", "hello flutter"))
                    .build(this);
            startActivity(customFlutter);
        });

        findViewById(R.id.btnCache).setOnClickListener(v -> {
            //带缓存的引擎
//            Intent customFlutter = FlutterActivity.withNewEngine()
//                    .initialRoute(FlutterRoutes.buildRoutes("gallery_page", "hello gallery_page"))
//                    .build(this);
//            startActivity(customFlutter);

            Intent customFlutter = SmartFlutterActivity.withCachedEngine("engine_id")
                    .build(this);
            startActivity(customFlutter);


        });
    }
}