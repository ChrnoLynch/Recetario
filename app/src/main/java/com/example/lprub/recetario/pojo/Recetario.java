package com.example.lprub.recetario.pojo;

import android.database.Cursor;

import com.example.lprub.recetario.acd.Contrato;

/**
 * Created by lprub on 18/11/2015.
 */
public class Recetario {
    private int id;
    private int idCategoria;
    private String nombre;
    private String foto;
    private String instrucciones;

    public Recetario() {
    }

    public Recetario(int id, String nombre, String foto, String instrucciones, int idCategoria) {
        this.id = id;
        this.nombre = nombre;
        this.foto = foto;
        this.instrucciones = instrucciones;
        this.idCategoria = idCategoria;

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

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getInstrucciones() {
        return instrucciones;
    }

    public void setInstrucciones(String instrucciones) {
        this.instrucciones = instrucciones;
    }

    public int getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(int idCategoria) {
        this.idCategoria = idCategoria;
    }

    public void setCursor(Cursor c){
        setId(c.getInt(c.getColumnIndex(Contrato.TablaRecetario._ID)));
        setNombre(c.getString(c.getColumnIndex(Contrato.TablaRecetario.NOMBRE)));
        setFoto(c.getString(c.getColumnIndex(Contrato.TablaRecetario.FOTO)));
        setInstrucciones(c.getString(c.getColumnIndex(Contrato.TablaRecetario.INSTRUCCIONES)));
        setIdCategoria(c.getInt(c.getColumnIndex(Contrato.TablaRecetario.IDCATEGORIA)));
    }

    @Override
    public String toString() {
        return "Recetario{" +
                "id=" + id +
                ", idCategoria=" + idCategoria +
                ", nombre='" + nombre + '\'' +
                ", foto='" + foto + '\'' +
                ", instrucciones='" + instrucciones + '\'' +
                '}';
    }
}
