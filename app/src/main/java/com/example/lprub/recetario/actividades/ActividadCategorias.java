package com.example.lprub.recetario.actividades;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;


import com.example.lprub.recetario.R;

public class ActividadCategorias extends AppCompatActivity {

    private final int POSTRES=1;
    private final int ARROCES=2;
    private final int CARNES=3;
    private final int PESCADOS=4;
    private final int SALSAS=5;
    private final int VERDURAS=6;
    private final int GUARNICIONES=7;
    private final int SOPAS=8;
    private final int POTAJES=9;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actividad_categorias);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_actividad_categorias, menu);
        return true;
    }

    //Metodo para entrar a la lista de recetas de las distintas categorias
    public void onClick(View iv_pulsado) {
        Intent intent = new Intent(this,ActividadListaRecetas.class);
        Bundle bundle=new Bundle();
        switch(iv_pulsado.getId()) {
            case R.id.ivArroz:
                bundle.putInt("Categoria",ARROCES);
                break;
            case R.id.ivCarne:
                bundle.putInt("Categoria",CARNES);
                break;
            case R.id.ivGuarniciones:
                bundle.putInt("Categoria",GUARNICIONES);
                break;
            case R.id.ivPescado:
                bundle.putInt("Categoria",PESCADOS);
                break;
            case R.id.ivPostres:
                bundle.putInt("Categoria",POSTRES);
                break;
            case R.id.ivPotajes:
                bundle.putInt("Categoria",POTAJES);
                break;
            case R.id.ivSalsas:
                bundle.putInt("Categoria",SALSAS);
                break;
            case R.id.ivSopas:
                bundle.putInt("Categoria",SOPAS);
                break;
            case R.id.ivVerduras:
                bundle.putInt("Categoria", VERDURAS);
                break;
        }
        intent.putExtras(bundle);
        startActivity(intent);
    }
}
