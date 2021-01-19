package com.smart.textrunapp.qrcode;


import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.huawei.hms.hmsscankit.ScanUtil;
import com.huawei.hms.hmsscankit.WriterException;
import com.huawei.hms.ml.scan.HmsBuildBitmapOption;
import com.huawei.hms.ml.scan.HmsScan;
import com.smart.library.zxing.CodeUtils;
import com.smart.textrunapp.R;

/**
 * @author guobihai
 * 创建日期：2021/1/19
 * desc：二维码生成显示类
 */
public class ImageActivity extends AppCompatActivity {


    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);

        ImageView ivBarcode = findViewById(R.id.iv_barcode);
        ImageView ivQrcode = findViewById(R.id.iv_qrcode);

//        ivBarcode.setImageBitmap(CodeUtils.createBarcode("This is a barcode"));
//        ivQrcode.setImageBitmap(CodeUtils.createQrcode("This is a qrcode"));

//        ivQrcode.setImageBitmap(ScanUtil.buildBitmap("This is a qrcode",1,100,100, HmsBuildBitmapOption.Creator));


        buildBarQrcode(ivBarcode, 152345678234L);
        buildQrcode(ivQrcode, "This is a qrcode");
    }

    private void buildQrcode(ImageView ivQrcode, String code) {
        try {
            //Generate the barcode.
            HmsBuildBitmapOption options = new HmsBuildBitmapOption.Creator().setBitmapMargin(1).create();
            Bitmap resultImage = ScanUtil.buildBitmap(code, HmsScan.QRCODE_SCAN_TYPE, 600, 600, options);
            ivQrcode.setImageBitmap(resultImage);
        } catch (WriterException e) {
            Toast.makeText(this, "Parameter Error!", Toast.LENGTH_SHORT).show();
        }
    }

    private void buildBarQrcode(ImageView ivQrcode, long code) {
        try {
            //Generate the barcode.
            HmsBuildBitmapOption options = new HmsBuildBitmapOption.Creator().setBitmapMargin(1).create();
            Bitmap resultImage = ScanUtil.buildBitmap(String.valueOf(code), HmsScan.CODABAR_SCAN_TYPE, 800, 300, options);
            ivQrcode.setImageBitmap(resultImage);
        } catch (WriterException e) {
            Toast.makeText(this, "Parameter Error!", Toast.LENGTH_SHORT).show();
        }
    }


}