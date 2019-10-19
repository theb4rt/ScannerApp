package com.b4rt.scannerapp.ui.webscanner;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class WebScannerViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public WebScannerViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Scanner Web Module");
    }

    public LiveData<String> getText() {
        return mText;
    }
}