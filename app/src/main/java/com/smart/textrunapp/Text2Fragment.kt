package com.smart.textrunapp

import android.os.Bundle
import android.view.View
import com.smart.library.base.BaseViewBindingFragment
import com.smart.textrunapp.databinding.Text2FragmentBinding

class Text2Fragment : BaseViewBindingFragment<Text2FragmentBinding, Text2ViewModel>() {

    companion object {
        fun newInstance() = Text2Fragment()
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mViewBinding.tvHello.text = "tv hello"
    }

    override fun initData() {
    }

    override fun initView() {

    }

    override fun getBindView(): Text2FragmentBinding {
        return Text2FragmentBinding.inflate(layoutInflater)
    }

    override fun getLifecycleOwner(): Any {
        return requireActivity()
    }

}