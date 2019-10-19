package com.b4rt.scannerapp.ui.dns;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class DnsViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public DnsViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Dns Scanner");
    }

    public LiveData<String> getText() {
        return mText;
    }
}