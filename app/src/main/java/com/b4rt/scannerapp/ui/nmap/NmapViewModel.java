package com.b4rt.scannerapp.ui.nmap;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class NmapViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public NmapViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Nmap Module");
    }

    public LiveData<String> getText() {
        return mText;
    }
}