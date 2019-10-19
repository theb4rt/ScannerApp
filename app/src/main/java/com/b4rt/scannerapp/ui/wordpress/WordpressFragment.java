package com.b4rt.scannerapp.ui.wordpress;

import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.b4rt.scannerapp.R;

public class WordpressFragment extends Fragment {

    private WordpressViewModel mViewModel;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        mViewModel =
                ViewModelProviders.of(this).get(WordpressViewModel.class);
        View root = inflater.inflate(R.layout.fragment_wordpress, container, false);

        return root;
    }



}
