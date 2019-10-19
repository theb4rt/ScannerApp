package com.b4rt.scannerapp.ui.wardriving;

import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.b4rt.scannerapp.R;

public class WardrivingFragment extends Fragment {

    private WardrivingViewModel mViewModel;

    public static WardrivingFragment newInstance() {
        return new WardrivingFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_wardriving, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(WardrivingViewModel.class);
        // TODO: Use the ViewModel
    }

}
