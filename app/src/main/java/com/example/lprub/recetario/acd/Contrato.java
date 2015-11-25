package com.example.lprub.recetario.acd;

import android.provider.BaseColumns;

/**
 * Created by lprub on 18/11/2015.
 */
public class Contrato {
    private Contrato (){
    }

    public static abstract class TablaRecetario implements BaseColumns {
        public static final String TABLA = "Recetario";
        public static final String NOMBRE = "nombre";
        public static final String FOTO = "foto";
        public static final String INSTRUCCIONES= "instrucciones";
        public static final String IDCATEGORIA= "idcategoria";
    }

    public static abstract class TablaRecetaIngredientes implements BaseColumns {
        public static final String TABLA = "RecetaIngredientes";
        public static final String IDRECETAS = "idRecetas";
        public static final String IDINGREDIENTE = "idIngrediente";
        public static final String CANTIDAD= "cantidad";
    }

    public static abstract class TablaRecetarioUtensilios implements BaseColumns {
        public static final String TABLA = "RecetarioUtensilios";
        public static final String IDRECETARIO = "idRecetario";
        public static final String IDUTENSILIO = "idUtensilio";
    }
    public static abstract class TablaUtensilio implements BaseColumns {
        public static final String TABLA = "Utensilio";
        public static final String NOMBRE = "nombre";
    }
    public static abstract class TablaCategoria implements BaseColumns {
        public static final String TABLA = "Categoria";
        public static final String NOMBRE = "nombre";
    }
    public static abstract class TablaIngrediente implements BaseColumns {
        public static final String TABLA = "Ingrediente";
        public static final String NOMBRE = "nombre";
    }

}
