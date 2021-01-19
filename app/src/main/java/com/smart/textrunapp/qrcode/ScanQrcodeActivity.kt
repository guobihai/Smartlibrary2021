package com.smart.textrunapp.qrcode

import android.Manifest
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.huawei.hms.hmsscankit.ScanUtil
import com.huawei.hms.ml.scan.HmsScan
import com.huawei.hms.ml.scan.HmsScanAnalyzerOptions
import com.smart.textrunapp.databinding.ActivityScanQrcodeBinding
import permissions.dispatcher.NeedsPermission
import permissions.dispatcher.OnPermissionDenied
import permissions.dispatcher.RuntimePermissions


/**
 * @author guobihai
 * 创建日期：2021/1/19
 * desc：华为扫码
 * url :https://developer.huawei.com/consumer/cn/codelab/ScanKit-DefaultView/index.html#3
 */
@RuntimePermissions
class ScanQrcodeActivity : AppCompatActivity() {
    val  REQUEST_CODE_SCAN :Int = 1001
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_scan_qrcode)
        requireSdCardWithPermissionCheck()

        var viewModel = ActivityScanQrcodeBinding.inflate(layoutInflater)
        setContentView(viewModel.root)
        viewModel.btnHuaWei.setOnClickListener {
            //调用DefaultView扫码界面
            ScanUtil.startScan(
                this@ScanQrcodeActivity,
                REQUEST_CODE_SCAN,
                HmsScanAnalyzerOptions.Creator().setHmsScanTypes(
                    HmsScan.ALL_SCAN_TYPE
                ).create()
            )
        }

        viewModel.btnGoogle.setOnClickListener {

        }
    }

    public override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        //当扫码页面结束后，处理扫码结果
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode != RESULT_OK || data == null) {
            return
        }
        //从onActivityResult返回data中，用 ScanUtil.RESULT作为key值取到HmsScan返回值
        if (requestCode == REQUEST_CODE_SCAN) {
            var obj: Any? = data?.getParcelableExtra(ScanUtil.RESULT)
            if (obj is HmsScan?) {
                if (!TextUtils.isEmpty(obj?.getOriginalValue())) {
                    Toast.makeText(this, obj?.getOriginalValue(), Toast.LENGTH_SHORT).show()
                }
                return
            }
        }
    }
    @NeedsPermission(
        Manifest.permission.READ_EXTERNAL_STORAGE,
        Manifest.permission.WRITE_EXTERNAL_STORAGE,
        Manifest.permission.CAMERA
    )
    fun requireSdCard() {
        System.out.println("==============")
    }

    @OnPermissionDenied(
        Manifest.permission.READ_EXTERNAL_STORAGE,
        Manifest.permission.WRITE_EXTERNAL_STORAGE,
        Manifest.permission.CAMERA
    )
    fun reject() {

    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        onRequestPermissionsResult(requestCode, grantResults)
    }

}