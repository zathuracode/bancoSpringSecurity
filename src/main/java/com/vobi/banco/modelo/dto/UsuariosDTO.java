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
public class UsuariosDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final Logger log = LoggerFactory.getLogger(UsuariosDTO.class);
    private Long usuCedula;
    private String usuClave;
    private String usuLogin;
    private String usuNombre;
    private Long tusuCodigo_TiposUsuarios;

    public Long getUsuCedula() {
        return usuCedula;
    }

    public void setUsuCedula(Long usuCedula) {
        this.usuCedula = usuCedula;
    }

    public String getUsuClave() {
        return usuClave;
    }

    public void setUsuClave(String usuClave) {
        this.usuClave = usuClave;
    }

    public String getUsuLogin() {
        return usuLogin;
    }

    public void setUsuLogin(String usuLogin) {
        this.usuLogin = usuLogin;
    }

    public String getUsuNombre() {
        return usuNombre;
    }

    public void setUsuNombre(String usuNombre) {
        this.usuNombre = usuNombre;
    }

    public Long getTusuCodigo_TiposUsuarios() {
        return tusuCodigo_TiposUsuarios;
    }

    public void setTusuCodigo_TiposUsuarios(Long tusuCodigo_TiposUsuarios) {
        this.tusuCodigo_TiposUsuarios = tusuCodigo_TiposUsuarios;
    }
}
