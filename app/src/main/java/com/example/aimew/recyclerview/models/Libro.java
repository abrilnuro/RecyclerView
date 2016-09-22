package com.example.aimew.recyclerview.models;

/**
 * Created by aimew on 21/09/2016.
 */

public class Libro {

    String nombre;
    String autor;
    int imagen;

    //constructor
    public Libro(String nombre, String autor, int imagen) {
        this.nombre = nombre;
        this.autor = autor;
        this.imagen = imagen;
    }

    //getters y setters
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public int getImagen() {
        return imagen;
    }

    public void setImagen(int imagen) {
        this.imagen = imagen;
    }



}
