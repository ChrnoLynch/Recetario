package com.example.lprub.recetario.acd;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by lprub on 18/11/2015.
 */
public class Ayudante extends SQLiteOpenHelper{
    public static final String DATABASE_NAME = "recetario.sqlite";
    public static final int DATABASE_VERSION = 1;

    public Ayudante(Context context) {
        super(context, DATABASE_NAME, null,
                DATABASE_VERSION);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion,
                          int newVersion) {
        String sql="drop table if exists "
                + Contrato.TablaRecetario.TABLA;
        db.execSQL(sql);

        sql="drop table if exists "
                + Contrato.TablaRecetaIngredientes.TABLA;
        db.execSQL(sql);

        sql="drop table if exists "
                + Contrato.TablaRecetarioUtensilios.TABLA;
        db.execSQL(sql);

        sql="drop table if exists "
                + Contrato.TablaCategoria.TABLA;
        db.execSQL(sql);

        sql="drop table if exists "
                + Contrato.TablaIngrediente.TABLA;
        db.execSQL(sql);

        sql="drop table if exists "
                + Contrato.TablaUtensilio.TABLA;
        db.execSQL(sql);
        onCreate(db);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {//Cuando se baja la aplicación y se crea por primera vez(no hay versión previa de la aplicación)
        String sql;
        sql="create table "+Contrato.TablaRecetario.TABLA+
                " ("+
                Contrato.TablaRecetario._ID + " integer primary key autoincrement, "+
                Contrato.TablaRecetario.NOMBRE+" text, "+
                Contrato.TablaRecetario.FOTO+" text, "+
                Contrato.TablaRecetario.INSTRUCCIONES +" text," +
                Contrato.TablaRecetario.IDCATEGORIA + " integer" +
                ")";
        db.execSQL(sql);

        /*****************************************************/

        sql="create table "+Contrato.TablaRecetaIngredientes.TABLA+
                " ("+
                Contrato.TablaRecetaIngredientes._ID + " integer primary key autoincrement, "+
                Contrato.TablaRecetaIngredientes.IDRECETAS+" integer, "+
                Contrato.TablaRecetaIngredientes.IDINGREDIENTE+" integer, "+
                Contrato.TablaRecetaIngredientes.CANTIDAD +" integer" +
                ")";
        db.execSQL(sql);

        /*****************************************************/

        sql="create table "+Contrato.TablaRecetarioUtensilios.TABLA+
                " ("+
                Contrato.TablaRecetarioUtensilios._ID + " integer primary key autoincrement, "+
                Contrato.TablaRecetarioUtensilios.IDRECETARIO+" integer, "+
                Contrato.TablaRecetarioUtensilios.IDUTENSILIO+" integer "+
                ")";
        db.execSQL(sql);

        /*****************************************************/

        sql="create table "+Contrato.TablaCategoria.TABLA+
                " ("+
                Contrato.TablaCategoria._ID + " integer primary key autoincrement, "+
                Contrato.TablaCategoria.NOMBRE+" text "+
                ")";

        db.execSQL(sql);

        /*****************************************************/

        sql="create table "+Contrato.TablaIngrediente.TABLA+
                " ("+
                Contrato.TablaIngrediente._ID + " integer primary key autoincrement, "+
                Contrato.TablaIngrediente.NOMBRE+" text "+
                ")";

        db.execSQL(sql);

        /*****************************************************/

        sql="create table "+Contrato.TablaUtensilio.TABLA+
                " ("+
                Contrato.TablaUtensilio._ID + " integer primary key autoincrement, "+
                Contrato.TablaUtensilio.NOMBRE+" text "+
                ")";

        db.execSQL(sql);

        /*******************************************************/
        //Introduciendo datos basicos de la Base de datos
        sql="INSERT INTO Categoria VALUES (1,'Postre'),(2,'Arroces y Pastas'),(3,'Carnes y Aves'), " +
                "(4,'Pescados y Mariscos'),(5,'Salsas'), (6,'Verduras y Hortalizas'), (7,'Guarniciones')," +
                " (8,'Sopas y Cremas'), (9,'Potajes y Platos de Cuchara')";
        db.execSQL(sql);
        /*******************************************************/



    }
}
