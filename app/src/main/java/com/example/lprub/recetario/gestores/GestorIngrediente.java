package com.example.lprub.recetario.gestores;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.lprub.recetario.acd.Ayudante;
import com.example.lprub.recetario.acd.Contrato;
import com.example.lprub.recetario.pojo.Ingrediente;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lprub on 18/11/2015.
 */

public class GestorIngrediente {
    private Ayudante abd;
    private SQLiteDatabase bd;
    public GestorIngrediente(Context c){
        abd=new Ayudante(c);
    }
    public void open(){
        bd=abd.getWritableDatabase();
    }
    public void openRead(){
        bd=abd.getReadableDatabase();
    }
    public void close(){
        abd.close();
    }
    public long insert(Ingrediente i){
        ContentValues valores=new ContentValues();
        valores.put(Contrato.TablaIngrediente.NOMBRE,i.getNombre());
        long id=bd.insert(Contrato.TablaIngrediente.TABLA,null,valores);
        return id;
    }
    public int delete(int id){
        String condicion = Contrato.TablaIngrediente._ID + " = ?";
        String[] argumentos = { id + "" };
        int cuenta = bd.delete(
                Contrato.TablaIngrediente.TABLA, condicion, argumentos);
        return cuenta;
    }
    public int delete(Ingrediente i) {
        return delete(i.getId());
    }
    public int update(Ingrediente i){
        ContentValues valores = new ContentValues();
        valores.put(Contrato.TablaIngrediente._ID, i.getId());
        valores.put(Contrato.TablaIngrediente.NOMBRE, i.getNombre());
        String condicion = Contrato.TablaIngrediente._ID + " = ?";
        String[] argumentos = { i.getId() + "" };
        int cuenta = bd.update(Contrato.TablaIngrediente.TABLA, valores,
                condicion, argumentos);
        return cuenta;
    }
    public Ingrediente getRow(Cursor c) {
        Ingrediente i = new Ingrediente();
        i.setId(c.getInt(c.getColumnIndex(Contrato.TablaIngrediente._ID)));
        i.setNombre(c.getString(c.getColumnIndex(Contrato.TablaIngrediente.NOMBRE)));
        return i;
    }
    public Cursor getCursor(String condicion, String[] parametros) {
        Cursor cursor = bd.query(
                Contrato.TablaIngrediente.TABLA, null, condicion, parametros, null,
                null, Contrato.TablaIngrediente._ID + ", " + Contrato.TablaIngrediente.NOMBRE);
        return cursor;
    }
    public Cursor getCursor(){
        return getCursor(null, null);
    }
    public Ingrediente getRow(long id) {
        Cursor c = getCursor("ID = ?", new String[]{id + ""});
        return getRow(c);
    }

    public List<Ingrediente> select(String condicion, String[] parametros) {
        List<Ingrediente> la = new ArrayList<>();
        Cursor cursor = getCursor(condicion, parametros);
        Ingrediente i;
        while (cursor.moveToNext()) {
            i = getRow(cursor);
            la.add(i);
        }
        cursor.close();
        return la;
    }

    public List<Ingrediente> select() {
        return select(null, null);
    }
}


