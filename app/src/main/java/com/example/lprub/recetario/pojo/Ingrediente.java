package com.example.lprub.recetario.pojo;

/**
 * Created by lprub on 18/11/2015.
 */
public class Ingrediente {
    private int id;
    private String nombre;

    public Ingrediente() {
    }

    public Ingrediente(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return "Ingrediente{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                '}';
    }
}
