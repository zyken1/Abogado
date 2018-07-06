package com.example.nequiz_omen.abogado.entidades;
import java.io.Serializable;
import java.security.Identity;

/**
 * Created by Nequiz_OMEN on 03/07/2018.
 */


public class Mascota implements Serializable {

    private Integer idDuenio;
    private Integer idMascota;
    private String nombreMascota;
    private String raza;
    private int cliente;

    public Mascota(){

    }

    public Mascota(Integer idDuenio, Integer idMascota, String nombreMascota, String raza) {
        this.idDuenio = idDuenio;
        this.idMascota = idMascota;
        this.nombreMascota = nombreMascota;
        this.raza = raza;
    }


     /* ===================       SE GENERAN METODOS GET Y SET          =================*/

    public Integer getIdDuenio() {
        return idDuenio;
    }

    public void setIdDuenio(Integer idDuenio) {
        this.idDuenio = idDuenio;
    }

    public Integer getIdMascota() {
        return idMascota;
    }

    public void setIdMascota(Integer idMascota) {
        this.idMascota = idMascota;
    }

    public String getNombreMascota() {
        return nombreMascota;
    }

    public void setNombreMascota(String nombreMascota) {
        this.nombreMascota = nombreMascota;
    }

    public String getRaza() {
        return raza;
    }

    public void setRaza(String raza) {
        this.raza = raza;
    }
}
