package com.example.lprub.recetario.gestores;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.example.lprub.recetario.acd.Ayudante;
import com.example.lprub.recetario.acd.Contrato;
import com.example.lprub.recetario.pojo.Ingrediente;
import com.example.lprub.recetario.pojo.RecetaIngredientes;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lprub on 18/11/2015.
 */
public class GestorRecetaIngredientes {


    private Ayudante abd;
    private SQLiteDatabase bd;
    private String query="SELECT * FROM RECETAINGREDIENTES INNER JOIN INGREDIENTE ON RECETAINGREDIENTEs.IDINGREDIENTE = INGREDIENTE._ID WHERE " + Contrato.TablaRecetaIngredientes.IDRECETAS +" = ";
    public GestorRecetaIngredientes(Context c){
        abd = new Ayudante(c);
    }
    public void open() {
        bd = abd.getWritableDatabase();
    }
    public void openRead() {
        bd = abd.getReadableDatabase();
    }
    public void close() {
        abd.close();
    }

    public long insert(RecetaIngredientes ri) {
        ContentValues valores = new ContentValues();
//        valores.put(Contrato.TablaRecetaIngredientes._ID, ri.getId());
        valores.put(Contrato.TablaRecetaIngredientes.IDRECETAS, ri.getIdRecetas());
        valores.put(Contrato.TablaRecetaIngredientes.IDINGREDIENTE, ri.getIdIngrediente());
        valores.put(Contrato.TablaRecetaIngredientes.CANTIDAD, ri.getCantidad());
        long id = bd.insert(Contrato.TablaRecetaIngredientes.TABLA, null, valores);
        return id;
    }

    public int delete(RecetaIngredientes ri) {
        return delete(ri.getId());
    }

    public int delete(int id){
        String condicion = Contrato.TablaRecetaIngredientes._ID + " = ?";
        String[] argumentos = { id + "" };
        int cuenta = bd.delete(
                Contrato.TablaRecetaIngredientes.TABLA, condicion, argumentos);
        return cuenta;
    }

    public int deleteIngredientesReceta(int id){
        String condicion = Contrato.TablaRecetaIngredientes.IDRECETAS + " = ?";
        String[] argumentos = { id + "" };
        int cuenta = bd.delete(
                Contrato.TablaRecetaIngredientes.TABLA, condicion, argumentos);
        return cuenta;
    }

    public int update(RecetaIngredientes ri){
        ContentValues valores = new ContentValues();
        valores.put(Contrato.TablaRecetaIngredientes._ID, ri.getId());
        valores.put(Contrato.TablaRecetaIngredientes.IDRECETAS, ri.getIdRecetas());
        valores.put(Contrato.TablaRecetaIngredientes.IDINGREDIENTE, ri.getIdIngrediente());
        valores.put(Contrato.TablaRecetaIngredientes.CANTIDAD, ri.getCantidad());
        String condicion = Contrato.TablaRecetaIngredientes._ID + " = ?";
        String[] argumentos = {ri.getId() + "" };
        int cuenta = bd.update(Contrato.TablaRecetaIngredientes.TABLA, valores,
                condicion, argumentos);
        return cuenta;
    }

    public RecetaIngredientes getRow(Cursor c) {
        RecetaIngredientes ri = new RecetaIngredientes();
        ri.setId(c.getInt(c.getColumnIndex(Contrato.TablaRecetaIngredientes._ID)));
        ri.setIdRecetas(c.getInt(c.getColumnIndex(Contrato.TablaRecetaIngredientes.IDRECETAS)));
        ri.setIdIngrediente(c.getInt(c.getColumnIndex(Contrato.TablaRecetaIngredientes.IDINGREDIENTE)));
        ri.setCantidad(c.getInt(c.getColumnIndex(Contrato.TablaRecetaIngredientes.CANTIDAD)));
        return ri;
    }

    public RecetaIngredientes getRow(long id) {
        Cursor c = getCursor("id = ?", new String[]{id + ""});
        return getRow(c);
    }

    public Cursor getCursor(){
        return getCursor(null, null);
    }

    //REVISAR BIEN PARA SABER COMO COÑO FUNCIONA
    public Cursor getCursor(String condicion, String[] parametros) {
        Cursor cursor = bd.query(
                Contrato.TablaRecetaIngredientes.TABLA, null, condicion, parametros, null,
                null, Contrato.TablaRecetaIngredientes._ID + ", " + Contrato.TablaRecetaIngredientes.IDINGREDIENTE);
        return cursor;
    }

    public List<RecetaIngredientes> select(String condicion, String[] parametros) {
        List<RecetaIngredientes> la = new ArrayList<>();
        Cursor cursor = getCursor(condicion, parametros);
        RecetaIngredientes ri;
        while (cursor.moveToNext()) {
            ri = getRow(cursor);
            la.add(ri);
        }
        cursor.close();
        return la;
    }

    public List<RecetaIngredientes> select() {
        return select(null,null);
    }

    //Consulta con InnerJoin para unir la tabla de recetaingredientes y la tabla ingredientes
    public Cursor getCursorInner(String[] parametros) {
        return bd.rawQuery(query + parametros[0] + ";", null);
    }

    //Consulta para sacar todos los ingredientes y cantidades de una receta en un String
    public String selectIngredientes(String[] parametros){
        String ingredientes="";
        Cursor cursor=getCursorInner(parametros);
        while (cursor.moveToNext()) {
            ingredientes=ingredientes+""+cursor.getString(cursor.getColumnIndex(Contrato.TablaRecetaIngredientes.CANTIDAD))+" "
                    + cursor.getString(cursor.getColumnIndex(Contrato.TablaIngrediente.NOMBRE))+"\n";
        }
        return ingredientes;
    }
}

