package com.b4rt.scannerapp.Models;

public class Imagen {
    private String imagen_nombre;
    private String imagen_path;
    private Integer imagen_id;

    public String getImagen_nombre() {
        return imagen_nombre;
    }

    public void setImagen_nombre(String imagen_nombre) {
        this.imagen_nombre = imagen_nombre;
    }

    public String getImagen_path() {
        return imagen_path;
    }

    public void setImagen_path(String imagen_path) {
        this.imagen_path = imagen_path;
    }

    public Integer getImagen_id() {
        return imagen_id;
    }

    public void setImagen_id(int imagen_id) {
        this.imagen_id = imagen_id;
    }
}
