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
    public static final String CAMPO_TIPO = "tipo_persona";    // si es 1 = fisica    2 = Moral
    public static final String CAMPO_EMAIL = "e_mail";
    public static final String CAMPO_GENERO = "genero";       // si es 1 = Hombre    2 = Mujer
    public static final String CAMPO_NACIMIENTO = "fechaNacimiento";
    public static final String CAMPO_DIRECCION = "direccion";
    public static final String CAMPO_TELMOVIL = "tel_movil";
    public static final String CAMPO_TELCASA= "tel_casa";
    public static final String CAMPO_TELOFICINA = "tel_oficina";
    public static final String CAMPO_DEPENDIENTES = "dependientes";
    public static final String CAMPO_NOTAS = "notas";



    /*public static String CREAR_TABLA_USUARIOS ="CREATE TABLE    usuarios     (id INTEGER,             nombre TEXT,           telefono TEXT)" ;*/
    public static String CREAR_TABLA_USUARIOS ="CREATE TABLE "+TABLA_USUARIO+" ("+CAMPO_ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+CAMPO_NOMBRE+" TEXT, "+CAMPO_TIPO+" TEXT, "+CAMPO_EMAIL+" TEXT, "+CAMPO_GENERO+" TEXT, "+CAMPO_NACIMIENTO+" TEXT, "+CAMPO_DIRECCION+" TEXT, "+CAMPO_TELMOVIL+" TEXT, "+CAMPO_TELCASA+" TEXT, "+CAMPO_TELOFICINA+" TEXT, "+CAMPO_DEPENDIENTES+" TEXT, "+CAMPO_NOTAS+" TEXT)" ;





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

