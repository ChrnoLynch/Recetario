package com.example.lprub.recetario.gestores;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.example.lprub.recetario.acd.Ayudante;
import com.example.lprub.recetario.acd.Contrato;
import com.example.lprub.recetario.pojo.RecetarioUtensilios;
import com.example.lprub.recetario.pojo.Utensilio;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lprub on 18/11/2015.
 */
public class GestorRecetarioUtensilios {
    private Ayudante abd;
    private SQLiteDatabase bd;
    private String query="SELECT * FROM RECETARIOUTENSILIOS INNER JOIN UTENSILIO ON RECETARIOUTENSILIOS.IDUTENSILIO = UTENSILIO._ID WHERE " + Contrato.TablaRecetarioUtensilios.IDRECETARIO +" = ";
    public GestorRecetarioUtensilios(Context c){
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
    public long insert(RecetarioUtensilios i){
        ContentValues valores=new ContentValues();
        valores.put(Contrato.TablaRecetarioUtensilios.IDRECETARIO,i.getIdRecetario());
        valores.put(Contrato.TablaRecetarioUtensilios.IDUTENSILIO,i.getIdUtensilio());
        long id=bd.insert(Contrato.TablaRecetarioUtensilios.TABLA,null,valores);
        return id;
    }
    public int delete(int id){
        String condicion = Contrato.TablaRecetarioUtensilios._ID + " = ?";
        String[] argumentos = { id + "" };
        int cuenta = bd.delete(
                Contrato.TablaRecetarioUtensilios.TABLA, condicion, argumentos);
        return cuenta;
    }

    public int deleteRecetaUtensilios(int id){
        String condicion = Contrato.TablaRecetarioUtensilios.IDRECETARIO + " = ?";
        String[] argumentos = { id + "" };
        int cuenta = bd.delete(
                Contrato.TablaRecetarioUtensilios.TABLA, condicion, argumentos);
        return cuenta;
    }

    public int delete(RecetarioUtensilios i) {
        return delete(i.getId());
    }
    public int update(RecetarioUtensilios i){
        ContentValues valores = new ContentValues();
//        valores.put(Contrato.TablaRecetarioUtensilios._ID, i.getId());
        valores.put(Contrato.TablaRecetarioUtensilios.IDRECETARIO,i.getIdRecetario());
        valores.put(Contrato.TablaRecetarioUtensilios.IDUTENSILIO,i.getIdUtensilio());
        String condicion = Contrato.TablaRecetarioUtensilios._ID + " = ?";
        String[] argumentos = { i.getId() + "" };
        int cuenta = bd.update(Contrato.TablaRecetarioUtensilios.TABLA, valores,
                condicion, argumentos);
        return cuenta;
    }
    public RecetarioUtensilios getRow(Cursor c) {
        RecetarioUtensilios i = new RecetarioUtensilios();
        i.setId(c.getInt(c.getColumnIndex(Contrato.TablaRecetarioUtensilios._ID)));
        i.setIdRecetario(c.getInt(c.getColumnIndex(Contrato.TablaRecetarioUtensilios.IDRECETARIO)));
        i.setIdUtensilio(c.getInt(c.getColumnIndex(Contrato.TablaRecetarioUtensilios.IDUTENSILIO)));
        return i;
    }
    public Cursor getCursor(String condicion, String[] parametros) {
        Cursor cursor = bd.query(
                Contrato.TablaRecetarioUtensilios.TABLA, null, condicion, parametros, null,
                null, Contrato.TablaRecetarioUtensilios._ID + ", " + Contrato.TablaRecetarioUtensilios._ID);
        return cursor;
    }
    public Cursor getCursor(){
        return getCursor(null, null);
    }
    public RecetarioUtensilios getRow(long id) {
        Cursor c = getCursor("ID = ?", new String[]{id + ""});
        return getRow(c);
    }

    public List<RecetarioUtensilios> select(String condicion, String[] parametros) {
        List<RecetarioUtensilios> la = new ArrayList<>();
        Cursor cursor = getCursor(condicion, parametros);
        RecetarioUtensilios i;
        while (cursor.moveToNext()) {
            i = getRow(cursor);
            la.add(i);
        }
        cursor.close();
        return la;
    }

    public List<RecetarioUtensilios> select() {
        return select(null, null);
    }

    public Cursor getCursorInner(String[] parametros) {
        return bd.rawQuery(query + parametros[0] + ";", null);
    }

    public String selectUtensilios(String[] parametros){
        String utensilios="";
        Cursor cursor=getCursorInner(parametros);
        while (cursor.moveToNext()) {
            utensilios=utensilios+""+cursor.getString(cursor.getColumnIndex(Contrato.TablaIngrediente.NOMBRE))+"\n";
        }
        return utensilios;
    }
}


