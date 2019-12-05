package com.b4rt.scannerapp.Activities;

import android.content.Context;
import android.content.Intent;

import com.b4rt.scannerapp.Adapters.CircleTransform;
import com.b4rt.scannerapp.R;
import com.b4rt.scannerapp.ui.profile.ProfileFragment;
import com.b4rt.scannerapp.ui.profile.profileViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.navigation.NavigationView;
import com.squareup.picasso.Picasso;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.Menu;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class menuActivity extends AppCompatActivity  {
    TextView txt_correo;
    ImageView imagen_perfil;


    private AppBarConfiguration mAppBarConfiguration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SharedPreferences mPrefs = getSharedPreferences("AuthScanner", MODE_PRIVATE);
        SharedPreferences.Editor prefsEditor = mPrefs.edit();

        String data = mPrefs.getString("token", null);
        Integer data2 = mPrefs.getInt("user_id", 0);

        Log.i("data", " retrieve --> " + data+data2);

        setContentView(R.layout.activity_menu);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = findViewById(R.id.fab);

        Intent intent = getIntent();
        final String imagen_nombre = intent.getStringExtra("imagen_nombre");
        final String nombre = intent.getStringExtra("nombre");
        final String apellido = intent.getStringExtra("apellido");
        final String user_id = intent.getStringExtra("user_id");
        final String correo = intent.getStringExtra("correo");
        final String musica = intent.getStringExtra("musica");
        final String imagen_path = intent.getStringExtra("imagen_path");
        final String token = intent.getStringExtra("token");




        Log.w("datosIntent", correo + " " + musica);

//        Picasso.get().load(imagen_path).into(this.imagen_perfil);


        //String url = ApiSe  intent.getStringExtra("token");


        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Enviar Mensaje", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);


        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_profile, R.id.nav_dns, R.id.nav_nmap,
                R.id.nav_web_scanner, R.id.nav_fuzzing, R.id.nav_xss, R.id.nav_wordpress
        )
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);


        //Obteniendo valores en el Nav_HEAVER_MENU y Profile
        View headerView = navigationView.getHeaderView(0);

        TextView txt_correo = (TextView) headerView.findViewById(R.id.txtview_correo_menu);
        ImageView imageView_menu = (ImageView) headerView.findViewById(R.id.imageView_profile_menu);

        txt_correo.setText(correo);
        Picasso.get().load(imagen_path).transform(new CircleTransform()).into(imageView_menu);




    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }




}
