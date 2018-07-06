package com.example.nequiz_omen.abogado.utilidades;

import android.provider.Settings;

/**
 * Created by Nequiz_OMEN on 03/07/2018.
 */

public class Utilidades {
    //Constantes campos de la tabla usuario
    public static final String TABLA_USUARIO = "usuario";   //se pone final para que sea constante  &  Se pone statico para que pueda ser accedido desde otra parte del codigo
    public static final String CAMPO_ID = "id";
    public static final String CAMPO_NOMBRE = "nombre";
    public static final String CAMPO_TELEFONO = "telefono";


    /*public static String CREAR_TABLA_USUARIOS ="CREATE TABLE    usuarios     (id INTEGER,             nombre TEXT,           telefono TEXT)" ;*/
    public static String CREAR_TABLA_USUARIOS ="CREATE TABLE "+TABLA_USUARIO+" ("+CAMPO_ID+" INTEGER, "+CAMPO_NOMBRE+" TEXT, "+CAMPO_TELEFONO+" TEXT)" ;





    //=========================  Constantes campos tabla mascota
    public static final String TABLA_MASCOTA="mascota";
    public static final String CAMPO_ID_MASCOTA="id_mascota";
    public static final String CAMPO_NOMBRE_MASCOTA="nombre_mascota";
    public static final String CAMPO_RAZA_MASCOTA="raza_mascota";
    public static final String CAMPO_ID_DUENIO="id_duenio";

    public static final String CREAR_TABLA_MASCOTA="CREATE TABLE " +
            ""+TABLA_MASCOTA+" ("+CAMPO_ID_MASCOTA+" INTEGER PRIMARY KEY AUTOINCREMENT, "
            +CAMPO_NOMBRE_MASCOTA+" TEXT, "+CAMPO_RAZA_MASCOTA+" TEXT,"+CAMPO_ID_DUENIO+" INTEGER)";

}

