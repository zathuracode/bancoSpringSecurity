package com.vobi.banco.modelo.control;

import com.vobi.banco.modelo.Usuarios;
import com.vobi.banco.modelo.dto.UsuariosDTO;

import java.math.BigDecimal;

import java.util.*;
import java.util.Date;
import java.util.List;
import java.util.Set;


/**
* @author Zathura Code Generator http://zathuracode.org/
* www.zathuracode.org
*
*/
public interface IUsuariosLogic {
    public List<Usuarios> getUsuarios() throws Exception;

    /**
         * Save an new Usuarios entity
         */
    public void saveUsuarios(Usuarios entity) throws Exception;

    /**
         * Delete an existing Usuarios entity
         *
         */
    public void deleteUsuarios(Usuarios entity) throws Exception;

    /**
        * Update an existing Usuarios entity
        *
        */
    public void updateUsuarios(Usuarios entity) throws Exception;

    /**
         * Load an existing Usuarios entity
         *
         */
    public Usuarios getUsuarios(Long usuCedula) throws Exception;

    public List<Usuarios> findByCriteria(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception;

    public List<Usuarios> findPageUsuarios(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception;

    public Long findTotalNumberUsuarios() throws Exception;

    public List<UsuariosDTO> getDataUsuarios() throws Exception;
    
    public Usuarios validarUsuario(String usuLogin, String usuClave)throws Exception;
}
