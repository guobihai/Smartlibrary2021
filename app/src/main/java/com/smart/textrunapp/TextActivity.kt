package com.smart.textrunapp

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.smart.library.entity.BaseResponse
import com.smart.library.network.RetrofitManager
import com.smart.library.network.utils.RxUtils
import com.smart.library.observer.MyMaybeObserver
import com.smart.library.observer.ResponseObserver
import com.smart.textrunapp.api.CenterApi
import com.smart.textrunapp.databinding.TextActivityLayoutBinding
import com.smart.textrunapp.java.thread.ThCallable
import dagger.hilt.android.AndroidEntryPoint
import io.reactivex.Maybe
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.util.*
import java.util.concurrent.FutureTask
import javax.inject.Inject

class TextActivity : AppCompatActivity() {
    var textBinding: TextActivityLayoutBinding? = null
    var futureTask: FutureTask<String>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        textBinding = TextActivityLayoutBinding.inflate(
            layoutInflater
        )
        setContentView(textBinding!!.root)
        textBinding!!.textView.text = "viewbinding"
        textBinding!!.textView.setOnClickListener { v: View? ->
            println("=====开始加载=======")
        }
        textBinding!!.tvCancle.setOnClickListener { v: View? ->
            println("=====取消加载=======")
            if (null != futureTask) {
                futureTask!!.cancel(true)
            }
        }
        GlobalScope.launch {
            val param = mapOf<String, String>("userId" to "13455")
            val res = RetrofitManager.create(CenterApi::class.java).logOut1(param)
        }
    }

    /**
     * 测试案例
     */
    fun textLoad() {
        RetrofitManager.create(CenterApi::class.java).logOut(HashMap<Any?, Any?>())
            .compose(RxUtils.getWrapper())
            .subscribe(object : ResponseObserver<BaseResponse<Boolean?>?>() {
                override fun onSuccess(data: BaseResponse<Boolean?>?) {
                    TODO("Not yet implemented")
                }
            })
    }

    fun textCallable() {
        Maybe.fromCallable(ThCallable()).compose(RxUtils.getWrapper())
            .subscribe(object : MyMaybeObserver<String?>() {
                override fun onResultSuccess(t: String?) {
                    TODO("Not yet implemented")
                }
            })
    }
}