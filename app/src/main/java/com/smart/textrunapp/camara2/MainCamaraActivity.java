package com.smart.textrunapp.camara2;

import android.content.Intent;
import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RadioGroup;

import androidx.appcompat.app.AppCompatActivity;


import com.smart.textrunapp.R;
import com.smart.textrunapp.camara2.util.FileUtil;
import com.smart.textrunapp.camara2.util.OnRecordListener;
import com.smart.textrunapp.camara2.widget.GlRenderView;
import com.smart.textrunapp.camara2.widget.RecordButton;
import com.smart.textrunapp.camara2.widget.SoulActivity;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MainCamaraActivity extends AppCompatActivity implements OnRecordListener {

    private GlRenderView mRenderView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camara_main);

        mRenderView = findViewById(R.id.render_view);
        mRenderView.setOnRecordListener(this);
        RecordButton recordButton = findViewById(R.id.btn_record);
        recordButton.setOnRecordListener(new RecordButton.OnRecordListener() {
            /**
             * 开始录制
             */
            @Override
            public void onRecordStart() {

                try {
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
                    File file = FileUtil.createFile(MainCamaraActivity.this, false, "opengl", sdf.format(new Date(System.currentTimeMillis())) + ".mp4", 1074000000);
                    mRenderView.setSavePath(file.getAbsolutePath());
                    mRenderView.startRecord();
                } catch (FileUtil.NoExternalStoragePermissionException e) {
                    e.printStackTrace();
                } catch (FileUtil.NoExternalStorageMountedException e) {
                    e.printStackTrace();
                } catch (FileUtil.DirHasNoFreeSpaceException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }


            }

            /**
             * 停止录制
             */
            @Override
            public void onRecordStop() {
                mRenderView.stopRecord();
            }
        });
        RadioGroup radioGroup = findViewById(R.id.rg_speed);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            /**
             * 选择录制模式
             * @param group
             * @param checkedId
             */
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.rb_extra_slow: //极慢
                        mRenderView.setSpeed(GlRenderView.Speed.MODE_EXTRA_SLOW);
                        break;
                    case R.id.rb_slow:
                        mRenderView.setSpeed(GlRenderView.Speed.MODE_SLOW);
                        break;
                    case R.id.rb_normal:
                        mRenderView.setSpeed(GlRenderView.Speed.MODE_NORMAL);
                        break;
                    case R.id.rb_fast:
                        mRenderView.setSpeed(GlRenderView.Speed.MODE_FAST);
                        break;
                    case R.id.rb_extra_fast: //极快
                        mRenderView.setSpeed(GlRenderView.Speed.MODE_EXTRA_FAST);
                        break;
                }
            }
        });


        ((CheckBox)findViewById(R.id.beauty)).setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                mRenderView.enableBeauty(isChecked);
            }
        });

        ((CheckBox)findViewById(R.id.bigEye)).setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                mRenderView.enableBigEye(isChecked);
            }
        });
        ((CheckBox)findViewById(R.id.stick)).setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                mRenderView.enableStick(isChecked);
            }
        });


    }


    @Override
    public void recordFinish(String mPath) {
        Intent intent = new Intent(this, SoulActivity.class);
        intent.putExtra("path", mPath);
        startActivity(intent);
    }
}
