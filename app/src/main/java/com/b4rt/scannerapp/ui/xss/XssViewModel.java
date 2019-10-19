package com.b4rt.scannerapp.ui.xss;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class XssViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public XssViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Xss Module");
    }

    public LiveData<String> getText() {
        return mText;
    }
}