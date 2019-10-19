package com.b4rt.scannerapp.ui.nmap;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.b4rt.scannerapp.R;

public class NmapFragment extends Fragment {

    private NmapViewModel nmapViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        nmapViewModel =
                ViewModelProviders.of(this).get(NmapViewModel.class);
        View root = inflater.inflate(R.layout.fragment_nmap, container, false);

        return root;
    }
}