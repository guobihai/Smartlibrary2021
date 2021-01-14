package com.smart.textrunapp;

import android.os.Build;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.idlefish.flutterboost.containers.FlutterFragment;

import io.flutter.plugin.platform.PlatformPlugin;

/**
 * @author guobihai
 * 创建日期：2020/12/29
 * desc：测试flutter
 */
public class FlutterFragmentPageActivity extends AppCompatActivity {

    private FlutterFragment flutterFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(0x40000000);
            window.getDecorView().setSystemUiVisibility(PlatformPlugin.DEFAULT_SYSTEM_UI);
        }
        super.onCreate(savedInstanceState);

        final ActionBar actionBar = getSupportActionBar();
        if(actionBar != null) {
            actionBar.hide();
        }

        setContentView(R.layout.flutter_fragment_page_activity);

        flutterFragment = new FlutterFragment.NewEngineFragmentBuilder().url("center").build();
        getSupportFragmentManager().beginTransaction().replace(R.id.flutter_content_layout, flutterFragment).commit();

    }
}