package com.b4rt.scannerapp.ui.profile;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.b4rt.scannerapp.Activities.MainActivity;
import com.b4rt.scannerapp.Activities.menuActivity;
import com.b4rt.scannerapp.R;
import com.squareup.picasso.Picasso;

public class ProfileFragment extends Fragment {

    private profileViewModel profileViewModel;
    private menuActivity mContext;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


        //  Intent intent = mContext.getIntent();

        final String imagen_nombre = getActivity().getIntent().getExtras().getString("imagen_nombre");
        final String nombre = getActivity().getIntent().getExtras().getString("nombre");
        final String apellido = getActivity().getIntent().getExtras().getString("apellido");
        final Integer user_id = getActivity().getIntent().getExtras().getInt("user_id");
        final String correo = getActivity().getIntent().getExtras().getString("correo");
        final String musica = getActivity().getIntent().getExtras().getString("musica");
        final String imagen_path = getActivity().getIntent().getExtras().getString("imagen_path");


        profileViewModel =
                ViewModelProviders.of(this).get(profileViewModel.class);
        View root = inflater.inflate(R.layout.fragment_profile, container, false);
        final TextView textView = root.findViewById(R.id.text_profile);


        EditText txt_profile_email = (EditText) root.findViewById(R.id.txt_profile_email);
        EditText txt_profile_music = (EditText) root.findViewById(R.id.txt_profile_music);
        EditText txt_profile_nombre = (EditText) root.findViewById(R.id.txt_profile_nombre);
        TextView txt_name = (TextView) root.findViewById(R.id.txt_name);
        ImageView imageView_profile = (ImageView) root.findViewById(R.id.imageview_profile);

        Picasso.get().load(imagen_path).into(imageView_profile);
        txt_profile_email.setText(correo);
        txt_profile_music.setText(musica);
        txt_profile_nombre.setText(nombre + " " + apellido);
        txt_name.setText(nombre);


        profileViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


    }
}