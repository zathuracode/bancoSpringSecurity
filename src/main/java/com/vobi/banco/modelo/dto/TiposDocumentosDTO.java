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
public class TiposDocumentosDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final Logger log = LoggerFactory.getLogger(TiposDocumentosDTO.class);
    private Long tdocCodigo;
    private String tdocNombre;

    public Long getTdocCodigo() {
        return tdocCodigo;
    }

    public void setTdocCodigo(Long tdocCodigo) {
        this.tdocCodigo = tdocCodigo;
    }

    public String getTdocNombre() {
        return tdocNombre;
    }

    public void setTdocNombre(String tdocNombre) {
        this.tdocNombre = tdocNombre;
    }
}
