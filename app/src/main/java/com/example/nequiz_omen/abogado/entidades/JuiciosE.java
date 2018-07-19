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

    public JuiciosE(){

    }


    public JuiciosE(Integer idDuenio,Integer idJuicios,String nombreExpediente,String cliente_extra,String contrario,String contrario_extra,String juicio,String asunto,String instancia,String etapa,String tramite,String fecha_tramite,String tramite_extra,String fechaTramite_extra,String costo_juicio,String resta_pago,String abono,String fecha_pago,String abono_extra,String fechaAbono_extra) {
        this.idDuenio = idDuenio;
        this.idJuicios = idJuicios;
        this.nombreExpediente = nombreExpediente;
        this.cliente_extra = cliente_extra;
        this.contrario	= contrario;
        this.contrario_extra = contrario_extra;
        this.juicio = juicio;
        this.asunto = asunto;
        this.instancia = instancia;
        this.etapa = etapa;
        this.tramite = tramite;
        this.fecha_tramite = fecha_tramite;
        this.tramite_extra = tramite_extra;
        this.fechaTramite_extra = fechaTramite_extra;
        this.costo_juicio = costo_juicio;
        this.resta_pago = resta_pago;
        this.abono = abono;
        this.fecha_pago = fecha_pago;
        this.abono_extra = abono_extra;
        this.fechaAbono_extra = fechaAbono_extra;
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

    public String getCliente_extra() {
        return cliente_extra;
    }

    public void setCliente_extra(String cliente_extra) {
        this.cliente_extra = cliente_extra;
    }

    public String getContrario() {
        return contrario;
    }

    public void setContrario(String contrario) {
        this.contrario = contrario;
    }

    public String getContrario_extra() {
        return contrario_extra;
    }

    public void setContrario_extra(String contrario_extra) {
        this.contrario_extra = contrario_extra;
    }

    public String getJuicio() {
        return juicio;
    }

    public void setJuicio(String juicio) {
        this.juicio = juicio;
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

    public String getEtapa() {
        return etapa;
    }

    public void setEtapa(String etapa) {
        this.etapa = etapa;
    }

    public String getTramite() {
        return tramite;
    }

    public void setTramite(String tramite) {
        this.tramite = tramite;
    }

    public String getFecha_tramite() {
        return fecha_tramite;
    }

    public void setFecha_tramite(String fecha_tramite) {
        this.fecha_tramite = fecha_tramite;
    }

    public String getTramite_extra() {
        return tramite_extra;
    }

    public void setTramite_extra(String tramite_extra) {
        this.tramite_extra = tramite_extra;
    }

    public String getFechaTramite_extra() {
        return fechaTramite_extra;
    }

    public void setFechaTramite_extra(String fechaTramite_extra) {
        this.fechaTramite_extra = fechaTramite_extra;
    }

    public String getCosto_juicio() {
        return costo_juicio;
    }

    public void setCosto_juicio(String costo_juicio) {
        this.costo_juicio = costo_juicio;
    }

    public String getResta_pago() {
        return resta_pago;
    }

    public void setResta_pago(String resta_pago) {
        this.resta_pago = resta_pago;
    }

    public String getAbono() {
        return abono;
    }

    public void setAbono(String abono) {
        this.abono = abono;
    }

    public String getFecha_pago() {
        return fecha_pago;
    }

    public void setFecha_pago(String fecha_pago) {
        this.fecha_pago = fecha_pago;
    }

    public String getAbono_extra() {
        return abono_extra;
    }

    public void setAbono_extra(String abono_extra) {
        this.abono_extra = abono_extra;
    }

    public String getFechaAbono_extra() {
        return fechaAbono_extra;
    }

    public void setFechaAbono_extra(String fechaAbono_extra) {
        this.fechaAbono_extra = fechaAbono_extra;
    }
}
