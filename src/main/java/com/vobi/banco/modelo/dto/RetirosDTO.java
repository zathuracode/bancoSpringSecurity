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
public class RetirosDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final Logger log = LoggerFactory.getLogger(RetirosDTO.class);
    private Long retCodigo;
    private String cueNumero;
    private String retDescripcion;
    private Date retFecha;
    private Double retValor;
    private String cueNumero_Cuentas;
    private Long usuCedula_Usuarios;

    public Long getRetCodigo() {
        return retCodigo;
    }

    public void setRetCodigo(Long retCodigo) {
        this.retCodigo = retCodigo;
    }

    public String getCueNumero() {
        return cueNumero;
    }

    public void setCueNumero(String cueNumero) {
        this.cueNumero = cueNumero;
    }

    public String getRetDescripcion() {
        return retDescripcion;
    }

    public void setRetDescripcion(String retDescripcion) {
        this.retDescripcion = retDescripcion;
    }

    public Date getRetFecha() {
        return retFecha;
    }

    public void setRetFecha(Date retFecha) {
        this.retFecha = retFecha;
    }

    public Double getRetValor() {
        return retValor;
    }

    public void setRetValor(Double retValor) {
        this.retValor = retValor;
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
