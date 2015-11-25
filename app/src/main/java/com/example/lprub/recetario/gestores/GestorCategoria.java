package com.example.lprub.recetario.gestores;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.lprub.recetario.acd.Ayudante;
import com.example.lprub.recetario.acd.Contrato;
import com.example.lprub.recetario.pojo.Categoria;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lprub on 18/11/2015.
 */
public class GestorCategoria {
    private Ayudante abd;
    private SQLiteDatabase bd;
    public GestorCategoria(Context c){
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
    public long insert(Categoria u){
        ContentValues valores=new ContentValues();
//        valores.put(Contrato.TablaCategoria._ID,u.getId());
        valores.put(Contrato.TablaCategoria.NOMBRE,u.getNombre());
        long id=bd.insert(Contrato.TablaCategoria.TABLA,null,valores);
        return id;
    }
    public int delete(int id){
        String condicion = Contrato.TablaCategoria._ID + " = ?";
        String[] argumentos = { id + "" };
        int cuenta = bd.delete(
                Contrato.TablaCategoria.TABLA, condicion, argumentos);
        return cuenta;
    }
    public int delete(Categoria u) {
        return delete(u.getId());
    }
    public int update(Categoria u){
        ContentValues valores = new ContentValues();
        valores.put(Contrato.TablaCategoria._ID, u.getId());
        valores.put(Contrato.TablaCategoria.NOMBRE, u.getNombre());
        String condicion = Contrato.TablaCategoria._ID + " = ?";
        String[] argumentos = { u.getId() + "" };
        int cuenta = bd.update(Contrato.TablaCategoria.TABLA, valores,
                condicion, argumentos);
        return cuenta;
    }
    public Categoria getRow(Cursor c) {
        Categoria u = new Categoria();
        u.setId(c.getInt(c.getColumnIndex(Contrato.TablaCategoria._ID)));
        u.setNombre(c.getString(c.getColumnIndex(Contrato.TablaCategoria.NOMBRE)));
        return u;
    }
    public Cursor getCursor(String condicion, String[] parametros) {
        Cursor cursor = bd.query(
                Contrato.TablaCategoria.TABLA, null, condicion, parametros, null,
                null, Contrato.TablaCategoria._ID + ", " + Contrato.TablaCategoria.NOMBRE);
        return cursor;
    }
    public Cursor getCursor(){
        return getCursor(null, null);
    }
    public Categoria getRow(long id) {
        Cursor c = getCursor("ID = ?", new String[]{id + ""});
        return getRow(c);
    }

    public List<Categoria> select(String condicion, String[] parametros) {
        List<Categoria> la = new ArrayList<>();
        Cursor cursor = getCursor(condicion, parametros);
        Categoria u;
        while (cursor.moveToNext()) {
            u = getRow(cursor);
            la.add(u);
        }
        cursor.close();
        return la;
    }

    public List<Categoria> select() {
        return select(null, null);
    }
}

