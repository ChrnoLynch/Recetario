package com.example.lprub.recetario.actividades;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lprub.recetario.R;
import com.example.lprub.recetario.acd.Ayudante;
import com.example.lprub.recetario.pojo.Recetario;
import com.example.lprub.recetario.gestores.GestorIngrediente;
import com.example.lprub.recetario.gestores.GestorRecetaIngredientes;
import com.example.lprub.recetario.gestores.GestorRecetario;
import com.example.lprub.recetario.gestores.GestorRecetarioUtensilios;
import com.example.lprub.recetario.gestores.GestorUtensilio;

import java.io.File;

public class ActividadVisualizar extends AppCompatActivity {

    private Recetario recetario;
    private String ingredientes, utensilios;
    private Ayudante ayudante;
    private GestorIngrediente gestorIngrediente;
    private GestorRecetario gestorRecetario;
    private GestorRecetaIngredientes gestorRecetaIngredientes;
    private GestorUtensilio gestorUtensilio;
    private GestorRecetarioUtensilios gestorRecetarioUtensilios;
    private int id;
    private TextView tvNombre, tvIngredientes, tvInstruciones, tvUtensilios;
    private ImageView ivFoto;
    private File imgFile;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actividad_visualizar);
        Intent i = getIntent();
        Bundle b = i.getExtras();
        id=b.getInt("ID");
        ayudante=new Ayudante(this);
        gestorIngrediente=new GestorIngrediente(this);
        gestorRecetario=new GestorRecetario(this);
        gestorRecetaIngredientes=new GestorRecetaIngredientes(this);
        gestorRecetarioUtensilios=new GestorRecetarioUtensilios(this);
        gestorUtensilio=new GestorUtensilio(this);
    }

    @Override
    protected void onResume() {
        gestorRecetario.openRead();
        gestorIngrediente.openRead();
        gestorRecetaIngredientes.openRead();
        gestorRecetarioUtensilios.openRead();
        gestorUtensilio.openRead();
        recetario=gestorRecetario.select(" _ID="+id,null).get(0);
        ingredientes=gestorRecetaIngredientes.selectIngredientes(new String[]{"" + id});
        utensilios=gestorRecetarioUtensilios.selectUtensilios(new String[]{"" + id});
        init();
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
            gestorIngrediente.close();
            gestorRecetaIngredientes.close();
            gestorRecetario.close();
            gestorUtensilio.close();
            gestorRecetarioUtensilios.close();
        }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_actividad_visualizar, menu);
        return true;
    }


    public void init(){
        tvNombre= (TextView) findViewById(R.id.tvNombre);
        tvInstruciones=(TextView) findViewById(R.id.tvInstrucciones);
        tvIngredientes= (TextView) findViewById(R.id.tvIngredientes);
        tvUtensilios= (TextView) findViewById(R.id.tvUtensilios);
        ivFoto= (ImageView) findViewById(R.id.ivFoto);
        tvNombre.setText(recetario.getNombre());
        tvInstruciones.setText(recetario.getInstrucciones());
        tvIngredientes.setText(ingredientes);
        tvUtensilios.setText(utensilios);
        imgFile = new  File(recetario.getFoto());
            if(imgFile.exists()){
            Bitmap myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
            ivFoto.setImageBitmap(myBitmap);
        }
    }
}
