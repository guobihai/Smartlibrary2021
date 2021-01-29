package com.smart.textrunapp

import android.Manifest
import android.content.Intent
import android.util.Log
import androidx.lifecycle.Observer
import com.google.gson.Gson
import com.smart.library.base.BaseViewBindingActivity
import com.smart.library.network.RetrofitManager
import com.smart.library.network.converter.GsonConverterFactory
import com.smart.textrunapp.adabpter.ImageNetAdapter
import com.smart.textrunapp.bean.DataBean
import com.smart.textrunapp.databinding.ActivityMainBinding
import com.smart.textrunapp.launch.ApiService
import com.smart.textrunapp.launch.bean.BaseRusult
import com.smart.textrunapp.launch.bean.SmartBean
import com.smart.textrunapp.qrcode.ImageActivity
import com.smart.textrunapp.qrcode.ScanQrcodeActivity
import com.smart.textrunapp.viewmodle.NetCtrlModel
import com.youth.banner.Banner
import com.youth.banner.indicator.CircleIndicator
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import permissions.dispatcher.NeedsPermission
import permissions.dispatcher.OnPermissionDenied
import permissions.dispatcher.RuntimePermissions
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit

@RuntimePermissions
class MainActivity : BaseViewBindingActivity<ActivityMainBinding, NetCtrlModel>() {


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
            startActivity(Intent(this, FlutterTextActivity::class.java))
        }

        mViewBinding.textQrcode.setOnClickListener {
            startActivity(Intent(this, ImageActivity::class.java))

        }

        mViewBinding.textLaunch.setOnClickListener {
            loadData()
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

//        playVideo("http://supergroup.oss-cn-shenzhen.aliyuncs.com/meeting/exhibition/a3765aa0e04fa81bbbefaf6abb4fe532.mp4")
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


    //携程处理
    private fun loadData() {
        GlobalScope.launch(Dispatchers.Main) {
            Log.d("GlobalScope", "=========main==========${Thread.currentThread().name}")
            try {
                val apiService = RetrofitManager.create(ApiService::class.java)
                val result = apiService.queryData1()
                val gson = Gson()
                Log.d("GlobalScope", "=========result==========${gson.toJson(result)}")
            } catch (e: Exception) {
                e.printStackTrace()
            }

            initLoadData()
        }


    }

    //普通异步处理
    fun initLoadData() {
        val apiService = RetrofitManager.create(ApiService::class.java)
        apiService.queryData().enqueue(object : Callback<BaseRusult<SmartBean>> {
            override fun onResponse(
                call: Call<BaseRusult<SmartBean>>,
                response: Response<BaseRusult<SmartBean>>
            ) {
                var gson = Gson()
                Log.d("GlobalScope", "=========result==========${gson.toJson(response.body())}")
            }

            override fun onFailure(call: Call<BaseRusult<SmartBean>>, t: Throwable) {
                Log.d("GlobalScope", "=========error==========${t.toString()}")
            }

        })
    }


}