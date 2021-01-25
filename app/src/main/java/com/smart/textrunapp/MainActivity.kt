package com.smart.textrunapp

import android.Manifest
import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.Observer
import com.smart.library.base.BaseViewBindingActivity
import com.smart.library.network.utils.RxUtils
import com.smart.textrunapp.adabpter.ImageNetAdapter
import com.smart.textrunapp.bean.DataBean
import com.smart.textrunapp.databinding.ActivityMainBinding
import com.smart.textrunapp.qrcode.ImageActivity
import com.smart.textrunapp.qrcode.ScanQrcodeActivity
import com.smart.textrunapp.viewmodle.NetCtrlModel
import com.youth.banner.Banner
import com.youth.banner.indicator.CircleIndicator
import io.reactivex.Observable
import io.reactivex.Scheduler
import permissions.dispatcher.NeedsPermission
import permissions.dispatcher.OnPermissionDenied
import permissions.dispatcher.RuntimePermissions
import java.util.*

@RuntimePermissions
class MainActivity : BaseViewBindingActivity<ActivityMainBinding, NetCtrlModel>() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    //播放
    private fun playVideo(url: String) {
        mViewBinding.smartVideoPlayer.addLifecycleObserver(this)
            .playVideo(url)
            .setFullScreen(true)
    }

    override fun onResume() {
        super.onResume()
        val textviewWidth = mViewBinding.textSdcard.measuredWidth
        System.out.println("======textviewWidth========" + textviewWidth)
        mViewBinding.textSdcard.postDelayed(Runnable {
            val textviewWidth = mViewBinding.textSdcard.measuredWidth
            System.out.println("======textviewWidth=====1===" + textviewWidth)
        }, 10)
    }


    @NeedsPermission(
        Manifest.permission.READ_EXTERNAL_STORAGE,
        Manifest.permission.WRITE_EXTERNAL_STORAGE
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

    override fun initData() {

    }

    override fun initView() {
        mViewBinding.textSdcard.setOnClickListener {
//            mViewModel.data.value = "jelemt"
            startActivity(Intent(this, SetGrayActivity::class.java))
        }

        mViewBinding.textQrcode.setOnClickListener {
            startActivity(Intent(this, ImageActivity::class.java))
        }

        mViewBinding.textScanQrcode.setOnClickListener {
            startActivity(Intent(this, ScanQrcodeActivity::class.java))
        }

        mViewModel.data.observe(this, object : Observer<String> {
            override fun onChanged(t: String?) {
                System.out.println("====================$t")
            }

        })

        supportFragmentManager.beginTransaction()
            .add(R.id.contentLayout, Text2Fragment.newInstance()).commit()

        playVideo("http://supergroup.oss-cn-shenzhen.aliyuncs.com/meeting/exhibition/a3765aa0e04fa81bbbefaf6abb4fe532.mp4")
        val adapers = ImageNetAdapter(DataBean.getTestData3())
        val tBaner = mViewBinding.banner as Banner<DataBean, ImageNetAdapter>
        tBaner.setAdapter(adapers)
            .addBannerLifecycleObserver(this)
            .setIndicator(CircleIndicator(this))
            .setOnBannerListener { data, position -> }
    }

    override fun getBindView(): ActivityMainBinding {
        TODO("Not yet implemented")
    }

    fun loadData(){


    }

}