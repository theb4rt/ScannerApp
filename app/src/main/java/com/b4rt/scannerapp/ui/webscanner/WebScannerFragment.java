package com.b4rt.scannerapp.ui.webscanner;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.b4rt.scannerapp.R;

public class WebScannerFragment extends Fragment {

    private WebScannerViewModel webScannerViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        webScannerViewModel =
                ViewModelProviders.of(this).get(WebScannerViewModel.class);
        View root = inflater.inflate(R.layout.fragment_website, container, false);

        return root;
    }
}