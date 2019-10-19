package com.b4rt.scannerapp.ui.fuzzing;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.b4rt.scannerapp.R;

public class FuzzinfFragment extends Fragment {

    private FuzzingViewModel fuzzingViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        fuzzingViewModel =
                ViewModelProviders.of(this).get(FuzzingViewModel.class);
        View root = inflater.inflate(R.layout.fragment_fuzzing, container, false);

        return root;
    }
}