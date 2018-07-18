package com.example.nequiz_omen.abogado.entidades;
import java.io.Serializable;
import java.security.Identity;

/**
 * Created by Nequiz_OMEN on 03/07/2018.
 */


public class JuiciosE implements Serializable {

    private Integer idDuenio;
    private Integer idJuicios;
    private String nombreExpediente;
    private String clientes;

    public JuiciosE(){

    }

    public JuiciosE(Integer idDuenio, Integer idJuicios, String nombreExpediente, String clientes) {
        this.idDuenio = idDuenio;
        this.idJuicios = idJuicios;
        this.nombreExpediente = nombreExpediente;
        this.clientes = clientes;
    }


     /* ===================       SE GENERAN METODOS GET Y SET          =================*/

    public Integer getIdDuenio() {
        return idDuenio;
    }

    public void setIdDuenio(Integer idDuenio) {
        this.idDuenio = idDuenio;
    }

    public Integer getIdJuicios() {
        return idJuicios;
    }

    public void setIdJuicios(Integer idJuicios) {
        this.idJuicios = idJuicios;
    }

    public String getNombreExpediente() {
        return nombreExpediente;
    }

    public void setNombreExpediente(String nombreExpediente) {
        this.nombreExpediente = nombreExpediente;
    }

    public String getClientes() {
        return clientes;
    }

    public void setClientes(String clientes) {
        this.clientes = clientes;
    }
}
