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
    public static final String TABLA_JUICIOS="juicios";
    public static final String CAMPO_ID_JUICIO="id_juicio";
    public static final String CAMPO_NOMBRE_EXPEDIENTE="nombreExpediente";
    public static final String CAMPO_CLIENTE_EXTRA="cliente_extra";
    public static final String CAMPO_CONTRARIO="contrario";
    public static final String CAMPO_CONTRARIO_EXTRA="contrario_extra";
    public static final String CAMPO_JUICIO="juicio";
    public static final String CAMPO_ASUNTO="asunto";
    public static final String CAMPO_INSTANCIA = "instancia";
    public static final String CAMPO_ETAPA = "etapa";
    public static final String CAMPO_TRAMITE = "tramite";
    public static final String CAMPO_FECHA_TRAMITE = "fecha_tramite";
    public static final String CAMPO_TRAMITE_EXTRA = "tramite_extra";
    public static final String CAMPO_FECHATRAMITE_EXTRA = "fechaTramite_extra";
    public static final String CAMPO_COSTO_JUICIO = "costo_juicio";
    public static final String CAMPO_RESTA_PAGO = "resta_pago";
    public static final String CAMPO_ABONO = "abono";
    public static final String CAMPO_FECHA_PAGO = "fecha_pago";
    public static final String CAMPO_ABONO_EXTRA = "abono_extra";
    public static final String CAMPO_FECHAABONO_EXTRA = "fechaAbono_extra";
    public static final String CAMPO_ID_DUENIO = "id_duenio";




    public static final String CREAR_TABLA_JUICIOS="CREATE TABLE " +
            ""+TABLA_JUICIOS+" ("+CAMPO_ID_JUICIO+" INTEGER PRIMARY KEY AUTOINCREMENT, "+CAMPO_NOMBRE_EXPEDIENTE+" TEXT, "+CAMPO_CLIENTE_EXTRA+" TEXT, "+CAMPO_CONTRARIO+" TEXT, "+CAMPO_CONTRARIO_EXTRA+" TEXT, "+CAMPO_JUICIO+" TEXT, "+CAMPO_ASUNTO+" TEXT, "+CAMPO_INSTANCIA+" TEXT, "+CAMPO_ETAPA+" TEXT, "+CAMPO_TRAMITE+" TEXT, "+CAMPO_FECHA_TRAMITE+" TEXT, "+CAMPO_TRAMITE_EXTRA+" TEXT, "+CAMPO_FECHATRAMITE_EXTRA+" TEXT, "+CAMPO_COSTO_JUICIO+" TEXT, "+CAMPO_RESTA_PAGO+" TEXT, "+CAMPO_ABONO+" TEXT, "+CAMPO_FECHA_PAGO+" TEXT, "+CAMPO_ABONO_EXTRA+" TEXT, "+CAMPO_FECHAABONO_EXTRA+" TEXT, "+CAMPO_ID_DUENIO+" INTEGER)";


    /*orden de los campos en el formulario  de  JUICIOS_FORMULARIO
    *
    private Integer idJuicios;
    private String nombreExpediente;
    private String cliente_extra;
    private String contrario;
    private String contrario_extra;
    private String juicio;
    private String asunto;
    private String instancia;
    private String etapa;
    private String tramite;
    private String fecha_tramite;
    private String tramite_extra;
    private String fechaTramite_extra;
    private String costo_juicio;
    private String resta_pago;
    private String abono;
    private String fecha_pago;
    private String abono_extra;
    private String fechaAbono_extra;

    private Integer idDuenio;
     */
}

