package com.example.lprub.recetario.gestores;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.lprub.recetario.acd.Ayudante;
import com.example.lprub.recetario.acd.Contrato;
import com.example.lprub.recetario.pojo.Recetario;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lprub on 18/11/2015.
 */

public class GestorRecetario {
    private Ayudante abd;
    private SQLiteDatabase bd;
    public GestorRecetario(Context c){
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
    public long insert(Recetario i){
        ContentValues valores=new ContentValues();
//        valores.put(Contrato.TablaRecetario._ID,i.getId());
        valores.put(Contrato.TablaRecetario.NOMBRE,i.getNombre());
        valores.put(Contrato.TablaRecetario.INSTRUCCIONES,i.getInstrucciones());
        valores.put(Contrato.TablaRecetario.FOTO,i.getFoto());
        valores.put(Contrato.TablaRecetario.IDCATEGORIA,i.getIdCategoria());
        long id=bd.insert(Contrato.TablaRecetario.TABLA,null,valores);
        return id;
    }
    public int delete(int id){
        String condicion = Contrato.TablaRecetario._ID + " = ?";
        String[] argumentos = { id + "" };
        int cuenta = bd.delete(
                Contrato.TablaRecetario.TABLA, condicion, argumentos);
        return cuenta;
    }
    public int delete(Recetario i) {
        return delete(i.getId());
    }
    public int update(Recetario i){
        ContentValues valores = new ContentValues();
        valores.put(Contrato.TablaRecetario.NOMBRE, i.getNombre());
        valores.put(Contrato.TablaRecetario.INSTRUCCIONES,i.getInstrucciones());
        valores.put(Contrato.TablaRecetario.FOTO,i.getFoto());
        valores.put(Contrato.TablaRecetario.IDCATEGORIA,i.getIdCategoria());
        String condicion = Contrato.TablaRecetario._ID + " = ?";
        String[] argumentos = { i.getId() + "" };
        int cuenta = bd.update(Contrato.TablaRecetario.TABLA, valores,
                condicion, argumentos);
        return cuenta;
    }
    public Recetario getRow(Cursor c) {
        Recetario i = new Recetario();
        i.setId(c.getInt(c.getColumnIndex(Contrato.TablaRecetario._ID)));
        i.setNombre(c.getString(c.getColumnIndex(Contrato.TablaRecetario.NOMBRE)));
        i.setInstrucciones(c.getString(c.getColumnIndex(Contrato.TablaRecetario.INSTRUCCIONES)));
        i.setFoto(c.getString(c.getColumnIndex(Contrato.TablaRecetario.FOTO)));
        i.setIdCategoria(c.getInt(c.getColumnIndex(Contrato.TablaRecetario.IDCATEGORIA)));
        return i;
    }
    public Cursor getCursor(String condicion, String[] parametros) {
        Cursor cursor = bd.query(
                Contrato.TablaRecetario.TABLA, null, condicion, parametros, null,
                null, Contrato.TablaRecetario._ID + ", " + Contrato.TablaRecetario.NOMBRE);
        return cursor;
    }
    public Cursor getCursor(String condicion) {
        Cursor cursor = bd.query(
                Contrato.TablaRecetario.TABLA, null, condicion, null , null,
                null, Contrato.TablaRecetario.NOMBRE);
        return cursor;
    }
    public Cursor getCursor(){
        return getCursor(null, null);
    }
    public Recetario getRow(long id) {
        Cursor c = getCursor("ID = ?", new String[]{id + ""});
        return getRow(c);
    }

    public List<Recetario> select(String condicion, String[] parametros) {
        List<Recetario> la = new ArrayList<>();
        Cursor cursor = getCursor(condicion, parametros);
        Recetario i;
        while (cursor.moveToNext()) {
            i = getRow(cursor);
            la.add(i);
        }
        cursor.close();
        return la;
    }

    public List<Recetario> select() {
        return select(null, null);
    }
}

