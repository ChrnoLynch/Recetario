package com.example.lprub.recetario.actividades;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.SimpleCursorAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.lprub.recetario.R;
import com.example.lprub.recetario.acd.Ayudante;
import com.example.lprub.recetario.acd.Contrato;
import com.example.lprub.recetario.pojo.Ingrediente;
import com.example.lprub.recetario.pojo.RecetaIngredientes;
import com.example.lprub.recetario.pojo.Recetario;
import com.example.lprub.recetario.pojo.RecetarioUtensilios;
import com.example.lprub.recetario.pojo.Utensilio;
import com.example.lprub.recetario.gestores.GestorIngrediente;
import com.example.lprub.recetario.gestores.GestorRecetaIngredientes;
import com.example.lprub.recetario.gestores.GestorRecetario;
import com.example.lprub.recetario.gestores.GestorRecetarioUtensilios;
import com.example.lprub.recetario.gestores.GestorUtensilio;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ActividadAlta extends AppCompatActivity {

    private Ayudante ayudante;
    public SQLiteDatabase bd;
    private ImageView foto;
    private List<EditText> arrayIngredientes=new ArrayList<>();
    private List<EditText> arrayCantidad=new ArrayList<>();
    private List<EditText> arrayEditUtensilios=new ArrayList<>();
    private List<Utensilio> arrayUtensilios=new ArrayList<>();
    private List<Ingrediente> arrayIngrediente;
    private GridLayout layIngredientes;
    private GridLayout layUtensilios;
    private GestorRecetaIngredientes gestorRecetaIngredientes;
    private GestorRecetario gestorRecetario;
    private GestorIngrediente gestorIngrediente;
    private GestorUtensilio gestorUtensilio;
    private GestorRecetarioUtensilios gestorRecetarioUtensilios;
    private RecetarioUtensilios recetaUtensilio;
    private String rutaFoto="error";
    private Utensilio utensilio;
    private String nombreUtensilio;
    private int idReceta;
    private int idUtensilio;
    private boolean finalizar=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actividad_alta);
        ayudante=new Ayudante(this);
        gestorRecetaIngredientes = new GestorRecetaIngredientes(this);
        gestorRecetario=new GestorRecetario(this);
        gestorIngrediente=new GestorIngrediente(this);
        gestorRecetarioUtensilios=new GestorRecetarioUtensilios(this);
        gestorUtensilio=new GestorUtensilio(this);
        foto = (ImageView) findViewById(R.id.ivFoto);
    }

    @Override
    protected void onResume() {
        bd=ayudante.getWritableDatabase();
        gestorRecetario.open();
        gestorIngrediente.open();
        gestorRecetaIngredientes.open();
        gestorRecetarioUtensilios.open();
        gestorUtensilio.open();
        boxSpinner();
        super.onResume();
    }

    @Override
    protected void onPause() {
        bd.close();
        gestorIngrediente.close();
        gestorRecetario.close();
        gestorRecetaIngredientes.close();
        gestorUtensilio.close();
        gestorRecetarioUtensilios.close();
        super.onPause();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_actividad_alta, menu);
        return true;
    }

    public void foto(View v){
        Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
        photoPickerIntent.setType("image/*");
        startActivityForResult(photoPickerIntent, 1);
    }

    protected void onActivityResult(int requestCode, int resultCode,
                                    Intent imageReturnedIntent) {
        super.onActivityResult(requestCode, resultCode, imageReturnedIntent);

        switch(requestCode) {
            case 1:
                if(resultCode == RESULT_OK){
                    Uri selectedImage = imageReturnedIntent.getData();
                    String[] filePathColumn = {MediaStore.Images.Media.DATA};
                    Cursor cursor = getContentResolver().query(
                            selectedImage, filePathColumn, null, null, null);
                    cursor.moveToFirst();
                    int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                    String filePath = cursor.getString(columnIndex);
                    cursor.close();
                    rutaFoto=filePath;
                    File imgFile = new  File(filePath);
                    if(imgFile.exists()){
                        Bitmap myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
                        foto.setImageBitmap(myBitmap);
                    }
                }
        }
    }

    public void boxSpinner(){
        Spinner spinner= (Spinner) findViewById(R.id.spinner);
        String[] queryCols=new String[]{"_id", "nombre"};
        String[] adapterCols=new String[]{"nombre"};
        int[] adapterRowViews=new int[]{android.R.id.text1};
        Cursor cursorcat=bd.query(true,"Categoria", queryCols,null,null,null,null, Contrato.TablaCategoria._ID,null);
        SimpleCursorAdapter sca=new SimpleCursorAdapter(this, android.R.layout.simple_spinner_item, cursorcat, adapterCols, adapterRowViews,0);
        sca.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(sca);
    }



    /********************************************************************************/

    public void añadirIngrediente(View v){
        EditText ingrediente=new EditText(this);
        EditText cantidad=new EditText(this);
        layIngredientes= (GridLayout) findViewById(R.id.layIngredientes);
        ingrediente.setHint(R.string.ingredienteHint);
        cantidad.setHint(R.string.cantidadHint);
        cantidad.setInputType(2);
        arrayIngredientes.add(ingrediente);
        arrayCantidad.add(cantidad);
        ViewGroup.LayoutParams parametros
                =new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        layIngredientes.addView(ingrediente, parametros);
        layIngredientes.addView(cantidad, parametros);
    }

    public void deleteIngrediente(View v){
        if(arrayIngredientes.size()>0) {
            EditText ingrediente = arrayIngredientes.get(arrayIngredientes.size() - 1);
            EditText cantidad = arrayCantidad.get(arrayCantidad.size() - 1);
            arrayIngredientes.remove(arrayIngredientes.size() - 1);
            arrayCantidad.remove(arrayCantidad.size() - 1);
            layIngredientes.removeView(ingrediente);
            layIngredientes.removeView(cantidad);
        }else{
            tostada(getString(R.string.toastIngrediente));
        }
    }

    public void añadirUtensilio(View v){
        EditText editUtensilio=new EditText(this);
        layUtensilios= (GridLayout) findViewById(R.id.layUtensilios);
        editUtensilio.setHint(R.string.utensilioHint);
        arrayEditUtensilios.add(editUtensilio);
        ViewGroup.LayoutParams parametros
                =new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layUtensilios.addView(editUtensilio, parametros);
    }

    public void deleteUtensilio(View v){
        if(arrayEditUtensilios.size()>0) {
            EditText utensilio = arrayEditUtensilios.get(arrayEditUtensilios.size() - 1);
            arrayEditUtensilios.remove(arrayEditUtensilios.size() - 1);
            layUtensilios.removeView(utensilio);
        }else{
            tostada(getString(R.string.toastUtensilio));
        }
    }

    public void tostada(String s){
        Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
    }

    public void añadir(View v){
        EditText etNombre, etInstrucciones;
        Spinner spinner= (Spinner) findViewById(R.id.spinner);
        etNombre = (EditText)findViewById(R.id.etNombre);
        etInstrucciones =(EditText)findViewById(R.id.etInstrucciones);
        String nombre=etNombre.getText().toString(),
                intrucciones=etInstrucciones.getText().toString();
        if(nombre.isEmpty() || intrucciones.isEmpty()){
            tostada("No dejes el nombre o las instrucciones en blanco");
        }else if (rutaFoto.equals("error")) {
            tostada("Debes seleccionar una imagen para la receta");
        }
        else{
            Recetario receta = new Recetario();
            receta.setNombre(etNombre.getText().toString());
            receta.setInstrucciones(etInstrucciones.getText().toString());
            receta.setFoto(rutaFoto);
            receta.setIdCategoria((int)spinner.getSelectedItemId());
            RecetaIngredientes recetaIngredientes;
            Ingrediente aux;
            idReceta = (int) gestorRecetario.insert(receta);
            finalizar=true;
            int idIngrediente;
            int cantidad;
            String nombreIng;
            for (int i = 0; i < arrayIngredientes.size(); i++) {
                nombreIng=arrayIngredientes.get(i).getText().toString();
                String condicion = " NOMBRE = '" + nombreIng + "'";
                arrayIngrediente = gestorIngrediente.select(condicion, null);
                if (arrayIngrediente.size() > 0) {
                    idIngrediente = arrayIngrediente.get(0).getId();
                    String cant=arrayCantidad.get(i).getText().toString();
                    cantidad = Integer.parseInt(cant);
                    recetaIngredientes = new RecetaIngredientes(idReceta, idIngrediente, cantidad);//PONER CANTIDAD
                    gestorRecetaIngredientes.insert(recetaIngredientes);
                    setResult(Activity.RESULT_OK);

                } else if (arrayIngrediente.size() == 0) {
                    aux = new Ingrediente();
                    nombreIng=arrayIngredientes.get(i).getText().toString();
                    aux.setNombre(nombreIng);
                    idIngrediente = (int) gestorIngrediente.insert(aux);
                    String cadCant=arrayCantidad.get(i).getText().toString();
                    cantidad = Integer.parseInt(cadCant);
                    recetaIngredientes = new RecetaIngredientes(idReceta, idIngrediente, cantidad);//PONER CANTIDAD
                    gestorRecetaIngredientes.insert(recetaIngredientes);
                    setResult(Activity.RESULT_OK);
                            }
                        }
            for (int i = 0; i < arrayEditUtensilios.size(); i++) {
                nombreUtensilio = arrayEditUtensilios.get(i).getText().toString();
                String condicion = " NOMBRE = '" + nombreUtensilio + "'";
                arrayUtensilios = gestorUtensilio.select(condicion, null);
                if (arrayUtensilios.size() > 0) {
                    idUtensilio = arrayUtensilios.get(0).getId();
                    recetaUtensilio = new RecetarioUtensilios(idReceta, idUtensilio);
                    gestorRecetarioUtensilios.insert(recetaUtensilio);
                    setResult(Activity.RESULT_OK);

                } else if (arrayIngrediente.size() == 0) {
                    utensilio = new Utensilio();
                    nombreUtensilio = arrayEditUtensilios.get(i).getText().toString();
                    utensilio.setNombre(nombreUtensilio);
                    idUtensilio = (int) gestorUtensilio.insert(utensilio);
                    recetaUtensilio = new RecetarioUtensilios(idReceta, idUtensilio);
                    gestorRecetarioUtensilios.insert(recetaUtensilio);
                    setResult(Activity.RESULT_OK);
                }
            }
        }
        if(finalizar)
        finish();
      }
    }




