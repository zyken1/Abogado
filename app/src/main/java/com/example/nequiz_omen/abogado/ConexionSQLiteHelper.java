package com.example.nequiz_omen.abogado;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.nequiz_omen.abogado.utilidades.Utilidades;

/**
 * Created by Nequiz_OMEN on 03/07/2018.
 */

public class ConexionSQLiteHelper  extends SQLiteOpenHelper {

    //SCRIPT  SE MUEVE ALA CARPETA DE UTILIDADES
    //final String CREAR_TABLA_USUARIOS ="CREATE TABLE  usuarios (id INTEGER, nombre TEXT, telefono TEXT)" ;

    public ConexionSQLiteHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {    /*SE GENERA EL CONSTRUCTOR DEL METODO */
        super(context, name, factory, version);
    }



    @Override
    public void onCreate(SQLiteDatabase db) {           /*AQUI SE CREAN LOS SCRIPTS*/
        db.execSQL(Utilidades.CREAR_TABLA_USUARIOS);   // DE ESTA MANERA SE  PUEDEN EJECUTAR TODAS LAS SENTENCIAS QUE TENGAMOS  y se extiende a la carpeta utilidades
        db.execSQL(Utilidades.CREAR_TABLA_JUICIOS);
    }



    @Override
    public void onUpgrade(SQLiteDatabase db, int versionAntigua, int nuevaVersion) {    /*AQUI REFRESCAMOS LLS SCRIPTS*/
        db.execSQL("DROP TABLE IF  EXISTS usuarios");
        db.execSQL("DROP TABLE IF EXISTS "+Utilidades.TABLA_JUICIOS);
        onCreate(db);
    }



}//END CLASS
