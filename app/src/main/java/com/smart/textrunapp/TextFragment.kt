package com.smart.textrunapp

import android.os.Bundle

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import com.smart.library.base.BaseFragment
import com.smart.textrunapp.databinding.Text2FragmentBinding
import com.smart.textrunapp.viewmodle.NetCtrlModel
import kotlinx.android.synthetic.main.fragment_text.*

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class TextFragment : BaseFragment<NetCtrlModel>() {

    val viewbind: Text2FragmentBinding by lazy {
        Text2FragmentBinding.inflate(layoutInflater)
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            TextFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    override fun initData() {

    }

    override fun initView() {
        tvBtnTextLiveData.setOnClickListener {
            mViewModel.data.value = "update value"
        }


        mViewModel.data.observe(requireActivity(), object : Observer<String> {
            override fun onChanged(t: String?) {
                System.out.println("==========TextFragment==========$t")
            }

        })
    }


    override fun getLayoutResId(): Int {
        return R.layout.fragment_text
    }

    override fun getLifecycleOwner(): LifecycleOwner {
        return requireActivity()
    }
}