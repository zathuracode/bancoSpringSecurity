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
public class ClientesDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final Logger log = LoggerFactory.getLogger(ClientesDTO.class);
    private String cliDireccion;
    private Long cliId;
    private String cliMail;
    private String cliNombre;
    private String cliTelefono;
    private Long tdocCodigo_TiposDocumentos;

    public String getCliDireccion() {
        return cliDireccion;
    }

    public void setCliDireccion(String cliDireccion) {
        this.cliDireccion = cliDireccion;
    }

    public Long getCliId() {
        return cliId;
    }

    public void setCliId(Long cliId) {
        this.cliId = cliId;
    }

    public String getCliMail() {
        return cliMail;
    }

    public void setCliMail(String cliMail) {
        this.cliMail = cliMail;
    }

    public String getCliNombre() {
        return cliNombre;
    }

    public void setCliNombre(String cliNombre) {
        this.cliNombre = cliNombre;
    }

    public String getCliTelefono() {
        return cliTelefono;
    }

    public void setCliTelefono(String cliTelefono) {
        this.cliTelefono = cliTelefono;
    }

    public Long getTdocCodigo_TiposDocumentos() {
        return tdocCodigo_TiposDocumentos;
    }

    public void setTdocCodigo_TiposDocumentos(Long tdocCodigo_TiposDocumentos) {
        this.tdocCodigo_TiposDocumentos = tdocCodigo_TiposDocumentos;
    }
}
