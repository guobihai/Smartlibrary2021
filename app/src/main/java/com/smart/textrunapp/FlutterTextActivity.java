package com.smart.textrunapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.smart.textrunapp.annotation.ClickLimit;
import com.smart.textrunapp.flutter.PageRouter;
import com.smart.textrunapp.flutter.SmtFlutterActivity;

import java.util.HashMap;
import java.util.Map;

public class FlutterTextActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_flutter_text);
        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @ClickLimit(value = 1000)
            @Override
            public void onClick(View v) {

                //默认启动模式
//            startActivity(SmtFlutterActivity.createDefaultIntent(this));

                Map<String, Object> param = new HashMap<>();
                param.put("name", "hello key");
                PageRouter.openPageByUrl(FlutterTextActivity.this, "homeGallery", param);
            }
        });

        findViewById(R.id.btnGallery).setOnClickListener(v -> {
            //指定路由启动模式
//            Intent customFlutter = SmtFlutterActivity.withNewEngine()
//                    .initialRoute(FlutterRoutes.buildRoutes("gallery_page", "hello flutter"))
//                    .build(this);
//            startActivity(customFlutter);

            Map<String, Object> param = new HashMap<>();
            param.put("name", "hello key");
            PageRouter.openPageByUrl(this, "home", param);
        });

        findViewById(R.id.btnCache).setOnClickListener(v -> {
            //带缓存的引擎
//            Intent customFlutter = FlutterActivity.withNewEngine()
//                    .initialRoute(FlutterRoutes.buildRoutes("gallery_page", "hello gallery_page"))
//                    .build(this);
//            startActivity(customFlutter);

//            Intent customFlutter = SmtFlutterActivity.withCachedEngine("engine_id")
//                    .build(this);
//            startActivity(customFlutter);

//            Map params = new HashMap();
//            params.put("test1","v_test1");
//            params.put("test2","v_test2");
//            PageRouter.openPageByUrl(this, "home" , params);
            startActivity(new Intent(this, FlutterFragmentPageActivity.class));
        });
    }
}