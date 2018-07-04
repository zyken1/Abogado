package com.example.nequiz_omen.abogado.entidades;
import java.io.Serializable;

/**
 * Created by Nequiz_OMEN on 03/07/2018.
 */

public class E_juicio implements Serializable{
    private Integer id;
    private String expediente;
    private String cliente;
    private String contrario;
    private String tipo_juicio;
    private String asunto;
    private String instancia;
    private String etapa_procesal;
    private String tramite;
    private Integer costo_juicio;
    private Integer resta_pago;
    private Integer abono;
    private String fecha;

   /*CONSTRUCTOR CON LOS PARAMETROS*/
    public E_juicio(Integer id,String expediente, String cliente, String contrario,String tipo_juicio,String asunto,String instancia,String etapa_procesal,String tramite,Integer costo_juicio,Integer resta_pago,Integer abono,String fecha) {
        this.id = id;
        this.expediente = expediente;
        this.cliente = cliente;
        this.contrario = contrario;
        this.tipo_juicio = tipo_juicio;
        this.asunto = asunto;
        this.instancia = instancia;
        this.etapa_procesal = etapa_procesal;
        this.tramite = tramite;
        this.costo_juicio = costo_juicio;
        this.resta_pago = resta_pago;
        this.abono = abono;
        this.fecha = fecha;

    }


        public E_juicio() {   //se genera constructor en automatico para su funcionamiento

        }







     /* ===================       SE GENERAN METODOS GET Y SET          =================*/


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getExpediente() {
        return expediente;
    }

    public void setExpediente(String expediente) {
        this.expediente = expediente;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public String getContrario() {
        return contrario;
    }

    public void setContrario(String contrario) {
        this.contrario = contrario;
    }

    public String getTipo_juicio() {
        return tipo_juicio;
    }

    public void setTipo_juicio(String tipo_juicio) {
        this.tipo_juicio = tipo_juicio;
    }

    public String getAsunto() {
        return asunto;
    }

    public void setAsunto(String asunto) {
        this.asunto = asunto;
    }

    public String getInstancia() {
        return instancia;
    }

    public void setInstancia(String instancia) {
        this.instancia = instancia;
    }

    public String getEtapa_procesal() {
        return etapa_procesal;
    }

    public void setEtapa_procesal(String etapa_procesal) {
        this.etapa_procesal = etapa_procesal;
    }

    public String getTramite() {
        return tramite;
    }

    public void setTramite(String tramite) {
        this.tramite = tramite;
    }

    public Integer getCosto_juicio() {
        return costo_juicio;
    }

    public void setCosto_juicio(Integer costo_juicio) {
        this.costo_juicio = costo_juicio;
    }

    public Integer getResta_pago() {
        return resta_pago;
    }

    public void setResta_pago(Integer resta_pago) {
        this.resta_pago = resta_pago;
    }

    public Integer getAbono() {
        return abono;
    }

    public void setAbono(Integer abono) {
        this.abono = abono;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
}
