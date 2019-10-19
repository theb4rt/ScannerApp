package com.b4rt.scannerapp.ui.wordpress;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class WordpressViewModel extends ViewModel {
    private MutableLiveData<String> mText;

    public WordpressViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Wordpress Module");
    }

    public LiveData<String> getText() {
        return mText;
    }
}
