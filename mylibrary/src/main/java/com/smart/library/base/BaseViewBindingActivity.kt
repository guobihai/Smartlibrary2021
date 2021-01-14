package com.smart.library.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.viewbinding.ViewBinding
import com.smart.library.base.viewbinding.inflateBindingWithGeneric
import com.smart.library.utils.MyDisplayMetrics
import com.smart.library.viewmodel.utils.ClassUtil
import com.smart.library.viewmodel.BaseViewModel

abstract class BaseViewBindingActivity<VB : ViewBinding, VM : BaseViewModel> : AppCompatActivity() {
    open lateinit var mViewModel: VM
    open lateinit var mViewBinding: VB
    override fun onCreate(savedInstanceState: Bundle?) {
        MyDisplayMetrics.setCustomDensity(this, application)
        super.onCreate(savedInstanceState)
        mViewBinding= inflateBindingWithGeneric(layoutInflater)
//        mViewBinding = getBindView()
        setContentView(mViewBinding.root)
        initViewModel()
        initData()
        initView()
        observeNetWorkSate()
    }

    abstract fun initData()
    abstract fun initView()
    abstract fun getBindView(): VB


    fun initViewModel() {
        val viewModelClass: Class<VM> = ClassUtil.getViewModel(this)
        this.mViewModel = ViewModelProvider(this).get(viewModelClass)

    }

    /**
     * 绑定UI的view
     */
    open fun bindNetWorkStateView(layoutId: Int) {

    }

    fun observeNetWorkSate() {
        mViewModel.netWorkState.observe(this, object : Observer<Int> {
            override fun onChanged(t: Int?) {

            }

        })

    }
}