package com.example.lprub.recetario.adaptadores;

/**
 * Created by lprub on 21/11/2015.
 */
import android.content.Context;
        import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.CursorAdapter;
import android.widget.ImageView;
import android.widget.TextView;
        import com.example.lprub.recetario.R;
        import com.example.lprub.recetario.pojo.Recetario;

import java.io.File;


public class AdaptadorRecetas extends CursorAdapter {

    public AdaptadorRecetas (Context co, Cursor cu) {
        super(co, cu, true);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        LayoutInflater i = LayoutInflater.from(parent.getContext());
        View v = i.inflate(R.layout.itemreceta, parent, false);
        return v;
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        TextView tvNombre = (TextView)view.findViewById(R.id.tvNombre);
        ImageView ivFoto = (ImageView)view.findViewById(R.id.ivFoto);
        Recetario receta = new Recetario();
        receta.setCursor(cursor);
        tvNombre.setText(receta.getNombre());
        File imgFile = new  File(receta.getFoto());
        if(imgFile.exists()){
            Bitmap myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
            ivFoto.setImageBitmap(myBitmap);
        }
    }
}