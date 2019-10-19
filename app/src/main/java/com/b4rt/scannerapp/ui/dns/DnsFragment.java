package com.b4rt.scannerapp.ui.dns;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.b4rt.scannerapp.R;

public class DnsFragment extends Fragment {

    private DnsViewModel dnsViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        dnsViewModel =
                ViewModelProviders.of(this).get(DnsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_dns, container, false);

        return root;
    }
}