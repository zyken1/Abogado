package com.example.nequiz_omen.abogado.entidades;

import java.io.Serializable;

/**
 * Created by Nequiz_OMEN on 03/07/2018.
 */

public class Usuario implements Serializable {  /*Serializable permite que sea enviado como objeto */

    private Integer id;
    private String nombre;
    private int tipo_persona;        // si es 1 = fisica    2 = Moral
    private String e_mail;
    private int genero;              // si es 1 = Hombre    2 = Mujer
    private String fechaNacimiento;
    private String direccion;
    private int tel_movil;
    private int tel_casa;
    private int tel_oficina;
    private String dependientes;
    private String notas;


    public Usuario(Integer id, String nombre, int tipo_persona, String e_mail, int genero, String fechaNacimiento, String direccion, int tel_movil, int tel_casa, int tel_oficina, String dependientes, String notas ) {         /*CONSTRUCTOR CON LOS PARAMETROS*/
        this.id = id;
        this.nombre = nombre;
        this.tipo_persona = tipo_persona;
        this.e_mail = e_mail;
        this.genero = genero;
        this.fechaNacimiento = fechaNacimiento;
        this.direccion = direccion;
        this.tel_movil = tel_movil;
        this.tel_casa = tel_casa;
        this.tel_oficina = tel_oficina;
        this.dependientes = dependientes;
        this.notas = notas;
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


    public int getTipo_persona() {
        return tipo_persona;
    }

    public void setTipo_persona(int tipo_persona) {
        this.tipo_persona = tipo_persona;
    }

    public String getE_mail() {
        return e_mail;
    }

    public void setE_mail(String e_mail) {
        this.e_mail = e_mail;
    }

    public int getGenero() {
        return genero;
    }

    public void setGenero(int genero) {
        this.genero = genero;
    }

    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public int getTel_movil() {
        return tel_movil;
    }

    public void setTel_movil(int tel_movil) {
        this.tel_movil = tel_movil;
    }

    public int getTel_casa() {
        return tel_casa;
    }

    public void setTel_casa(int tel_casa) {
        this.tel_casa = tel_casa;
    }

    public int getTel_oficina() {
        return tel_oficina;
    }

    public void setTel_oficina(int tel_oficina) {
        this.tel_oficina = tel_oficina;
    }

    public String getDependientes() {
        return dependientes;
    }

    public void setDependientes(String dependientes) {
        this.dependientes = dependientes;
    }

    public String getNotas() {
        return notas;
    }

    public void setNotas(String notas) {
        this.notas = notas;
    }
}


