package com.b4rt.scannerapp;

import android.app.Activity;
import android.app.AlertDialog;

public class Metodos {
    private Activity context;

    public Metodos(Activity context) {
        this.context = context;
    }

    public AlertDialog alerta(String titulo, String descripcion) {
        AlertDialog alertDialog = new AlertDialog.Builder(this.context).create();
        alertDialog.setTitle(titulo);
        alertDialog.setMessage(descripcion);
         return alertDialog;
    }
}
