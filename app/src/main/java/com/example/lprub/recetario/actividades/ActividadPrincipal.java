package com.example.lprub.recetario.actividades;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.lprub.recetario.R;
import com.example.lprub.recetario.acd.Ayudante;
import com.example.lprub.recetario.gestores.GestorRecetario;

import java.sql.SQLData;

public class ActividadPrincipal extends AppCompatActivity {

    private Ayudante ayudante;
    public SQLiteDatabase bd;
    private GestorRecetario gestorRecetario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actividad_principal);
        init();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_actividad_principal, menu);
        return true;
    }


    public void init(){
        bc();
    }

    public void bc(){
        ayudante=new Ayudante(this);
        gestorRecetario=new GestorRecetario(this);
        }

    public void alta(View v){
        Intent i=new Intent(this,ActividadAlta.class);
        startActivity(i);
    }

    public void categorias(View v){
        Intent i=new Intent(this,ActividadCategorias.class);
        startActivity(i);
    }
}
