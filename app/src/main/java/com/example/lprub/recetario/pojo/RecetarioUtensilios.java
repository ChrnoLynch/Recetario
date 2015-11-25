package com.example.lprub.recetario.pojo;

/**
 * Created by lprub on 18/11/2015.
 */
public class RecetarioUtensilios {
    private int id;
    private  int idRecetario;
    private int idUtensilio;

    public RecetarioUtensilios() {
    }

    public RecetarioUtensilios(int idRecetario, int idUtensilio) {
        this.idRecetario = idRecetario;
        this.idUtensilio = idUtensilio;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdRecetario() {
        return idRecetario;
    }

    public void setIdRecetario(int idRecetario) {
        this.idRecetario = idRecetario;
    }

    public int getIdUtensilio() {
        return idUtensilio;
    }

    public void setIdUtensilio(int idUtensilio) {
        this.idUtensilio = idUtensilio;
    }

    @Override
    public String toString() {
        return "RecetarioUtensilios{" +
                "id=" + id +
                ", idRecetario=" + idRecetario +
                ", idUtensilio=" + idUtensilio +
                '}';
    }
}
