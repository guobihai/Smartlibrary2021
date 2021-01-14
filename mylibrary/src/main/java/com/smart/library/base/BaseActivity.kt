package com.smart.library.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.smart.library.iloadview.ILoadVew
import com.smart.library.utils.MyDisplayMetrics
import com.smart.library.viewmodel.utils.ClassUtil
import com.smart.library.viewmodel.BaseViewModel

abstract class BaseActivity<VM : BaseViewModel> : AppCompatActivity() {
    open lateinit var mViewModel: VM
    override fun onCreate(savedInstanceState: Bundle?) {
        MyDisplayMetrics.setCustomDensity(this, application)
        super.onCreate(savedInstanceState)
        setContentView(getLayoutResId())
        initViewModel()
        initData()
        initView()
        observeNetWorkSate()
    }

    abstract fun initData()
    abstract fun initView()
    abstract fun getLayoutResId(): Int
    fun initViewModel() {
        val viewModelClass: Class<VM> = ClassUtil.getViewModel(this)
        this.mViewModel = ViewModelProvider(this).get(viewModelClass)
    }

    /**
     * 绑定UI的view
     */
    open fun bindNetWorkStateView(layoutId: Int) {

    }

    lateinit var bindView: ILoadVew
    fun bindNetStateView(bind: ILoadVew) {
        bindView = bind
    }

    fun observeNetWorkSate() {
        mViewModel.netWorkState.observe(this, object : Observer<Int> {
            override fun onChanged(t: Int?) {
                when (t) {
                    1->bindView.showEmpty(null)
                }
            }

        })

    }
}