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
public class TiposUsuariosDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final Logger log = LoggerFactory.getLogger(TiposUsuariosDTO.class);
    private Long tusuCodigo;
    private String tusuNombre;

    public Long getTusuCodigo() {
        return tusuCodigo;
    }

    public void setTusuCodigo(Long tusuCodigo) {
        this.tusuCodigo = tusuCodigo;
    }

    public String getTusuNombre() {
        return tusuNombre;
    }

    public void setTusuNombre(String tusuNombre) {
        this.tusuNombre = tusuNombre;
    }
}
