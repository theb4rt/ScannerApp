package com.b4rt.scannerapp.ui.xss;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.b4rt.scannerapp.R;

public class XssFragment extends Fragment {

    private XssViewModel xssViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        xssViewModel =
                ViewModelProviders.of(this).get(XssViewModel.class);
        View root = inflater.inflate(R.layout.fragment_xss, container, false);

        return root;
    }
}