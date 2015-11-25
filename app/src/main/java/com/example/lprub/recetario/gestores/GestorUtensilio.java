package com.example.lprub.recetario.gestores;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.lprub.recetario.acd.Ayudante;
import com.example.lprub.recetario.acd.Contrato;
import com.example.lprub.recetario.pojo.Ingrediente;
import com.example.lprub.recetario.pojo.Utensilio;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lprub on 18/11/2015.
 */
public class GestorUtensilio {
    private Ayudante abd;
    private SQLiteDatabase bd;
    public GestorUtensilio(Context c){
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
    public long insert(Utensilio u){
        ContentValues valores=new ContentValues();
//        valores.put(Contrato.TablaUtensilio._ID, u.getId());
        valores.put(Contrato.TablaUtensilio.NOMBRE, u.getNombre());
        long id=bd.insert(Contrato.TablaUtensilio.TABLA,null,valores);
        return id;
    }
    public int delete(int id){
        String condicion = Contrato.TablaUtensilio._ID + " = ?";
        String[] argumentos = { id + "" };
        int cuenta = bd.delete(
                Contrato.TablaUtensilio.TABLA, condicion, argumentos);
        return cuenta;
    }
    public int delete(Ingrediente u) {
        return delete(u.getId());
    }
    public int update(Ingrediente u){
        ContentValues valores = new ContentValues();
        valores.put(Contrato.TablaUtensilio._ID, u.getId());
        valores.put(Contrato.TablaUtensilio.NOMBRE, u.getNombre());
        String condicion = Contrato.TablaUtensilio._ID + " = ?";
        String[] argumentos = { u.getId() + "" };
        int cuenta = bd.update(Contrato.TablaUtensilio.TABLA, valores,
                condicion, argumentos);
        return cuenta;
    }
    public Utensilio getRow(Cursor c) {
        Utensilio u = new Utensilio();
        u.setId(c.getInt(c.getColumnIndex(Contrato.TablaUtensilio._ID)));
        u.setNombre(c.getString(c.getColumnIndex(Contrato.TablaUtensilio.NOMBRE)));
        return u;
    }
    public Cursor getCursor(String condicion, String[] parametros) {
        Cursor cursor = bd.query(
                Contrato.TablaUtensilio.TABLA, null, condicion, parametros, null,
                null, Contrato.TablaUtensilio._ID + ", " + Contrato.TablaUtensilio.NOMBRE);
        return cursor;
    }
    public Cursor getCursor(){
        return getCursor(null, null);
    }
    public Utensilio getRow(long id) {
        Cursor c = getCursor("ID = ?", new String[]{id + ""});
        return getRow(c);
    }

    public List<Utensilio> select(String condicion, String[] parametros) {
        List<Utensilio> la = new ArrayList<>();
        Cursor cursor = getCursor(condicion, parametros);
        Utensilio u;
        while (cursor.moveToNext()) {
            u = getRow(cursor);
            la.add(u);
        }
        cursor.close();
        return la;
    }

    public List<Utensilio> select() {
        return select(null, null);
    }
}
