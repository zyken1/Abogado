package com.example.nequiz_omen.abogado.entidades;

import java.io.Serializable;

/**
 * Created by Nequiz_OMEN on 03/07/2018.
 */

public class Usuario implements Serializable {  /*Serializable permite que sea enviado como objeto */

    private Integer id;
    private String nombre;
    private String telefono;


    public Usuario(Integer id, String nombre, String telefono) {         /*CONSTRUCTOR CON LOS PARAMETROS*/
        this.id = id;
        this.nombre = nombre;
        this.telefono = telefono;
    }

    public Usuario() {  //se genera constructor en automatico para su funcionamiento

    }
/*===========METODOS GET Y SET  ============================*/


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
}


