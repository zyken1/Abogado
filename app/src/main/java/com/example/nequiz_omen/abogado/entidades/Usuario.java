package com.example.nequiz_omen.abogado.entidades;

import java.io.Serializable;

/**
 * Created by Nequiz_OMEN on 03/07/2018.
 */

public class Usuario implements Serializable {  /*Serializable permite que sea enviado como objeto */
    private Integer id;
    private String nombre;
    private String tipo_persona;        // si es 1 = fisica    2 = Moral
    private String e_mail;
    private String genero;              // si es 3 = Hombre    4 = Mujer
    private String fechaNacimiento;
    private String direccion;
    private String tel_movil;
    private String tel_casa;
    private String tel_oficina;
    private String dependientes;
    private String notas;


    public Usuario(Integer id, String nombre, String tipo_persona, String e_mail, String genero, String fechaNacimiento, String direccion, String tel_movil, String tel_casa, String tel_oficina, String dependientes, String notas ) {         /*CONSTRUCTOR CON LOS PARAMETROS*/
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

    public String getTipo_persona() {
        return tipo_persona;
    }

    public void setTipo_persona(String tipo_persona) {
        this.tipo_persona = tipo_persona;
    }

    public String getE_mail() {
        return e_mail;
    }

    public void setE_mail(String e_mail) {
        this.e_mail = e_mail;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
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

    public String getTel_movil() {
        return tel_movil;
    }

    public void setTel_movil(String tel_movil) {
        this.tel_movil = tel_movil;
    }

    public String getTel_casa() {
        return tel_casa;
    }

    public void setTel_casa(String tel_casa) {
        this.tel_casa = tel_casa;
    }

    public String getTel_oficina() {
        return tel_oficina;
    }

    public void setTel_oficina(String tel_oficina) {
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


