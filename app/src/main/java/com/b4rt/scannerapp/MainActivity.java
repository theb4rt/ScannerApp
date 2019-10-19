package com.b4rt.scannerapp;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.multi.DialogOnAnyDeniedMultiplePermissionsListener;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;
import com.karumi.dexter.listener.single.DialogOnDeniedPermissionListener;
import com.karumi.dexter.listener.single.PermissionListener;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    Button btn_login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Dexter.withActivity(this)
                .withPermissions(
                        Manifest.permission.CAMERA,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.ACCESS_FINE_LOCATION,
                        Manifest.permission.READ_EXTERNAL_STORAGE
                ).withListener(new MultiplePermissionsListener() {

            @Override
            public void onPermissionsChecked(MultiplePermissionsReport report) {

                Dexter.withActivity(MainActivity.this)
                        .withPermissions(
                                Manifest.permission.CAMERA,
                                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                                Manifest.permission.READ_EXTERNAL_STORAGE
                        ).withListener(new MultiplePermissionsListener() {


                    @Override
                    public void onPermissionsChecked(MultiplePermissionsReport report) {


                        //LanzandoHome
                        if (isConnect(MainActivity.this)) {
                            open_home();
                        } else {
                            no_red();
                        }

                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(List<PermissionRequest> permissions, PermissionToken token) {

                        noPermissions();


                    }
                }).check();


            }

            @Override
            public void onPermissionRationaleShouldBeShown(List<com.karumi.dexter.listener.PermissionRequest> permissions, PermissionToken token) {

                token.continuePermissionRequest();


            }
        }).check();


    }


    protected void open_home() {
        btn_login = (Button) findViewById(R.id.btn_login);
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), menuActivity.class);
                startActivity(intent);
            }
        });
    }

    protected void noPermissions() {

        Metodos metodos = new Metodos(this);
        AlertDialog dialog = metodos.alerta("Mensaje", "Por Favor, Acepte los permisos");
        dialog.setButton(AlertDialog.BUTTON_NEUTRAL, "Aceptar",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        Intent intent = new Intent(MainActivity.this, MainActivity.class);

                        MainActivity main = new MainActivity();
                        main.startActivity(intent);
                        main.finishAffinity();

                        finish();
                    }
                });
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();
    }

    protected void no_red() {
        Metodos metodos = new Metodos(this);
        AlertDialog dialog = metodos.alerta("Mensaje", "Por favor, conéctese a una red.");
        dialog.setButton(AlertDialog.BUTTON_NEUTRAL, "Aceptar",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        finish();
                    }
                });
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();
    }

    protected static boolean isConnect(Activity activity) {
        boolean flag = false;

        ConnectivityManager cwjManager = (ConnectivityManager) activity.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (cwjManager.getActiveNetworkInfo() != null)
            flag = cwjManager.getActiveNetworkInfo().isConnected();

        return flag;
    }

    @Override
    protected void onStart() {
        super.onStart();
    }


    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }
}