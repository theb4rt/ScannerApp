package com.b4rt.scannerapp.ui.fuzzing;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class FuzzingViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public FuzzingViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Fuzzing Module");
    }

    public LiveData<String> getText() {
        return mText;
    }
}