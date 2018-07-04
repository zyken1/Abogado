package com.example.nequiz_omen.abogado.utilidades;

import android.provider.Settings;

/**
 * Created by Nequiz_OMEN on 03/07/2018.
 */

public class Utilidades {
    //    Constantes campos de la tabla JUICIOS

    public static final String TABLA_JUICIOS = "juicios";    //se pone final para que sea constante  &  Se pone statico para que pueda ser accedido desde otra parte del codigo
    public static final String CAMPO_ID = "id";
    public static final String CAMPO_EXPEDIENTE = "expediente";
    public static final String CAMPO_CLIENTE = "cliente";
    public static final String CAMPO_CONTRARIO = "contrario";
    public static final String CAMPO_TIPO_JUICIO = "tipo_juicio";
    public static final String CAMPO_ASUNTO = "asunto";
    public static final String CAMPO_INSTANCIA = "instancia";
    public static final String CAMPO_ETAPA_PROCESAL = "etapa_procesal";
    public static final String CAMPO_TRAMITE = "tramite";
    public static final String CAMPO_COSTO_JUICIO = "costo_juicio";
    public static final String CAMPO_RESTA_PAGO = "resta_pago";
    public static final String CAMPO_ABONO = "abono";
    public static final String CAMPO_FECHA = "fecha";


    /*public static String CREAR_TABLA_JUICIOS ="CREATE TABLE    usuarios     (id INTEGER, nombre TEXT, telefono TEXT)" ;*/
    public static String CREAR_TABLA_JUICIOS = "CREATE TABLE " +TABLA_JUICIOS+ " (" +CAMPO_ID+ " INTEGER PRIMARY KEY AUTOINCREMENT, " +CAMPO_EXPEDIENTE+ " TEXT, " +CAMPO_CONTRARIO+ " TEXT)";



            /*+CAMPO_CLIENTE + " TEXT,"
            +CAMPO_CONTRARIO+ " TEXT,"
            +CAMPO_TIPO_JUICIO+ " TEXT,"
            +CAMPO_ASUNTO+ " TEXT,"
            +CAMPO_INSTANCIA+ " TEXT,"
            +CAMPO_ETAPA_PROCESAL+ " TEXT,"
            +CAMPO_TRAMITE+ " TEXT,"
            +CAMPO_COSTO_JUICIO+ " INTEGER,"
            +CAMPO_RESTA_PAGO+ " INTEGER,"
            +CAMPO_ABONO+ " INTEGER,"
            +CAMPO_FECHA+ " TEXT   )"; */




    //     Constantes campos tabla USUARIOS

}
