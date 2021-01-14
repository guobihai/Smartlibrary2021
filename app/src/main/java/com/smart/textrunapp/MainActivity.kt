package com.smart.textrunapp

import android.Manifest
import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.Observer
import com.smart.library.base.BaseViewBindingActivity
import com.smart.textrunapp.adabpter.ImageNetAdapter
import com.smart.textrunapp.bean.DataBean
import com.smart.textrunapp.databinding.ActivityMainBinding
import com.smart.textrunapp.viewmodle.NetCtrlModel
import com.youth.banner.Banner
import com.youth.banner.indicator.CircleIndicator
import io.flutter.app.FlutterActivity
import io.flutter.app.FlutterFragmentActivity
import io.flutter.embedding.android.FlutterFragmentActivity.createDefaultIntent
import permissions.dispatcher.NeedsPermission
import permissions.dispatcher.OnPermissionDenied
import permissions.dispatcher.RuntimePermissions

@RuntimePermissions
class MainActivity : BaseViewBindingActivity<ActivityMainBinding, NetCtrlModel>() {

    var i: Int = 3


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        i = 6
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
        Manifest.permission.WRITE_EXTERNAL_STORAGE
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
            startActivity(Intent(this, FlutterTextActivity::class.java))
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


}