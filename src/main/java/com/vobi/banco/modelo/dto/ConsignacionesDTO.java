package com.vobi.banco.modelo.dto;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;

import java.sql.*;

import java.util.Date;


/**
*
* @author Zathura Code Generator http://zathuracode.org/
* www.zathuracode.org
*
*/
public class ConsignacionesDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final Logger log = LoggerFactory.getLogger(ConsignacionesDTO.class);
    private String conDescripcion;
    private Date conFecha;
    private Double conValor;
    private Long conCodigo;
    private String cueNumero;
    private String cueNumero_Cuentas;
    private Long usuCedula_Usuarios;

    public String getConDescripcion() {
        return conDescripcion;
    }

    public void setConDescripcion(String conDescripcion) {
        this.conDescripcion = conDescripcion;
    }

    public Date getConFecha() {
        return conFecha;
    }

    public void setConFecha(Date conFecha) {
        this.conFecha = conFecha;
    }

    public Double getConValor() {
        return conValor;
    }

    public void setConValor(Double conValor) {
        this.conValor = conValor;
    }

    public Long getConCodigo() {
        return conCodigo;
    }

    public void setConCodigo(Long conCodigo) {
        this.conCodigo = conCodigo;
    }

    public String getCueNumero() {
        return cueNumero;
    }

    public void setCueNumero(String cueNumero) {
        this.cueNumero = cueNumero;
    }

    public String getCueNumero_Cuentas() {
        return cueNumero_Cuentas;
    }

    public void setCueNumero_Cuentas(String cueNumero_Cuentas) {
        this.cueNumero_Cuentas = cueNumero_Cuentas;
    }

    public Long getUsuCedula_Usuarios() {
        return usuCedula_Usuarios;
    }

    public void setUsuCedula_Usuarios(Long usuCedula_Usuarios) {
        this.usuCedula_Usuarios = usuCedula_Usuarios;
    }
}
