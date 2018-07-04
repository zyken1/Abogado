package com.example.nequiz_omen.abogado;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.nequiz_omen.abogado.utilidades.Utilidades;

/**
 * Created by Nequiz_OMEN on 03/07/2018.
 */

public class ConexionSQLiteHelper extends SQLiteOpenHelper {


    public ConexionSQLiteHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {/*AQUI SE CREAN LOS SCRIPTS*/
        db.execSQL(Utilidades.CREAR_TABLA_JUICIOS);   // DE ESTA MANERA SE  PUEDEN EJECUTAR TODAS LAS SENTENCIAS QUE TENGAMOS  y se extiende a la carpeta utilidades
        //db.execSQL(Utilidades.CREAR_TABLA_MASCOTA);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int viejaVersion, int nuevaVersion) {/*AQUI REFRESCAMOS LLS SCRIPTS*/
        db.execSQL("DROP TABLE IF  EXISTS juicios");
        //db.execSQL("DROP TABLE IF EXISTS "+Utilidades.TABLA_MASCOTA);
        onCreate(db);

    }
}
