package com.example.lprub.recetario.actividades;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import com.example.lprub.recetario.R;
import com.example.lprub.recetario.acd.Contrato;
import com.example.lprub.recetario.adaptadores.AdaptadorRecetas;
import com.example.lprub.recetario.gestores.GestorRecetarioUtensilios;
import com.example.lprub.recetario.pojo.RecetaIngredientes;
import com.example.lprub.recetario.gestores.GestorRecetaIngredientes;
import com.example.lprub.recetario.gestores.GestorRecetario;

import java.util.List;

public class ActividadListaRecetas extends AppCompatActivity {

    private GestorRecetario gestorRecetario;
    private GestorRecetaIngredientes gestorRecetaIngredientes;
    private GestorRecetarioUtensilios gestorRecetarioUtensilios;
    private AdaptadorRecetas adaptador;
    private GridView gridView;
    private Cursor cursor;
    private int categoria;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actividad_lista_recetas);
        Intent i = getIntent();
        Bundle b = i.getExtras();
        categoria = b.getInt("Categoria");
        gestorRecetario = new GestorRecetario(this);
        gestorRecetaIngredientes=new GestorRecetaIngredientes(this);
        gestorRecetarioUtensilios=new GestorRecetarioUtensilios(this);
        gridView = (GridView) findViewById(R.id.grView);
    }

    @Override
    protected void onResume() {
        gestorRecetario.open();
        gestorRecetaIngredientes.open();
        gestorRecetarioUtensilios.open();
        generarAdaptador();
        super.onResume();
    }


    @Override
    protected void onPause() {
        gestorRecetario.close();
        gestorRecetaIngredientes.close();
        gestorRecetarioUtensilios.close();
        super.onPause();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_actividad_lista_recetas, menu);
        return true;
    }



    @Override
    public void onCreateContextMenu(ContextMenu menu, View v,
                                    ContextMenu.ContextMenuInfo menuInfo) {//creamos nuestro menu contextual
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_actividad_lista_recetas, menu);
    }

    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo vistainfo =
                (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        int posicion = vistainfo.position;//cogemos la posicion del elemento pulsado en la vista
        switch (item.getItemId()) {//acciones que hara nuestro menu contextual
            case R.id.action_borrar:
                borrar(posicion);
                actualiza();
                return true;
            case R.id.action_editar:
                cursor.moveToPosition(posicion);
                editar(posicion);
                actualiza();
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }

    public void generarAdaptador() {
        cursor = gestorRecetario.getCursor(Contrato.TablaRecetario.IDCATEGORIA + " = " + categoria);
        adaptador = new AdaptadorRecetas(this, cursor);
        gridView.setAdapter(adaptador);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                cursor.moveToPosition(position);
                int idReceta = cursor.getInt(cursor.getColumnIndex(Contrato.TablaRecetario._ID));
                Intent i = new Intent(ActividadListaRecetas.this, ActividadVisualizar.class);
                Bundle b = new Bundle();
                b.putInt("ID", idReceta);
                i.putExtras(b);
                startActivity(i);
            }
        });
        registerForContextMenu(gridView);
    }

    public void actualiza() {
        generarAdaptador();
    }

    public void borrar(int posicion){
        cursor.moveToPosition(posicion);
        int id = cursor.getInt(cursor.getColumnIndex(Contrato.TablaRecetario._ID));
        gestorRecetaIngredientes.deleteIngredientesReceta(id);
        gestorRecetarioUtensilios.deleteRecetaUtensilios(id);
        gestorRecetario.delete(id);
    }

    public void editar(int posicion){
        cursor.moveToPosition(posicion);
        int id=cursor.getInt(cursor.getColumnIndex(Contrato.TablaRecetario._ID));
        Intent i= new Intent(this, ActividadEditar.class);
        Bundle b=new Bundle();
        b.putInt("ID",id);
        i.putExtras(b);
        startActivity(i);
    }
}
