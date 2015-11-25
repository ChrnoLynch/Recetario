package com.example.lprub.recetario.pojo;

/**
 * Created by lprub on 18/11/2015.
 */
public class RecetaIngredientes {
    private int id;
    private int idRecetas;
    private int idIngrediente;
    private int cantidad;

    public RecetaIngredientes() {
    }

    public RecetaIngredientes( int idRecetas, int idIngrediente, int cantidad) {
        this.idRecetas = idRecetas;
        this.idIngrediente = idIngrediente;
        this.cantidad = cantidad;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdRecetas() {
        return idRecetas;
    }

    public void setIdRecetas(int idRecetas) {
        this.idRecetas = idRecetas;
    }

    public int getIdIngrediente() {
        return idIngrediente;
    }

    public void setIdIngrediente(int idIngrediente) {
        this.idIngrediente = idIngrediente;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    @Override
    public String toString() {
        return "RecetaIngredientes{" +
                "id=" + id +
                ", idRecetas=" + idRecetas +
                ", idIngrediente=" + idIngrediente +
                ", cantidad=" + cantidad +
                '}';
    }
}
