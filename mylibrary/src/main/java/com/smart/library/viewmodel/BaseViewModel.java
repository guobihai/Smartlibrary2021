package com.smart.library.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class BaseViewModel extends ViewModel {
    private MutableLiveData<Integer> netWorkState = new MutableLiveData<>();

    public MutableLiveData<Integer> getNetWorkState() {
        return netWorkState;
    }
}
