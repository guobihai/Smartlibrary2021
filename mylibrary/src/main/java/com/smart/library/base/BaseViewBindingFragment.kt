package com.smart.library.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import androidx.viewbinding.ViewBinding
import com.smart.library.viewmodel.BaseViewModel
import com.smart.library.viewmodel.utils.ClassUtil

abstract class BaseViewBindingFragment<VB : ViewBinding, VM : BaseViewModel> : Fragment() {
    open lateinit var mViewModel: VM
    open lateinit var mViewBinding: VB



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mViewBinding = getBindView()
        return mViewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViewModel()
        initData()
        initView()
        observeNetWorkSate()
    }

    abstract fun initData()
    abstract fun initView()
    abstract fun getBindView(): VB

    /**
     * 绑定viewmodel
     */
    abstract fun getLifecycleOwner(): Any

    fun initViewModel() {
        val viewModelClass: Class<VM> = ClassUtil.getViewModel(this)
        var lifecycleOwner = getLifecycleOwner()
        if (lifecycleOwner is AppCompatActivity) {
            lifecycleOwner = requireActivity()
        } else {
            lifecycleOwner = this
        }
        this.mViewModel =
            ViewModelProvider(lifecycleOwner as ViewModelStoreOwner).get(viewModelClass)
    }

    fun observeNetWorkSate() {
        var lifecycleOwner = getLifecycleOwner()
        if (lifecycleOwner is AppCompatActivity) {
            lifecycleOwner = requireActivity()
        } else {
            lifecycleOwner = viewLifecycleOwner
        }
        mViewModel.netWorkState.observe(lifecycleOwner as LifecycleOwner, object : Observer<Int> {
            override fun onChanged(t: Int?) {

            }

        })

    }
}