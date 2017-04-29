package com.vobi.banco.modelo.control;

import com.vobi.banco.modelo.TiposUsuarios;
import com.vobi.banco.modelo.dto.TiposUsuariosDTO;

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
public interface ITiposUsuariosLogic {
    public List<TiposUsuarios> getTiposUsuarios() throws Exception;

    /**
         * Save an new TiposUsuarios entity
         */
    public void saveTiposUsuarios(TiposUsuarios entity)
        throws Exception;

    /**
         * Delete an existing TiposUsuarios entity
         *
         */
    public void deleteTiposUsuarios(TiposUsuarios entity)
        throws Exception;

    /**
        * Update an existing TiposUsuarios entity
        *
        */
    public void updateTiposUsuarios(TiposUsuarios entity)
        throws Exception;

    /**
         * Load an existing TiposUsuarios entity
         *
         */
    public TiposUsuarios getTiposUsuarios(Long tusuCodigo)
        throws Exception;

    public List<TiposUsuarios> findByCriteria(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception;

    public List<TiposUsuarios> findPageTiposUsuarios(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception;

    public Long findTotalNumberTiposUsuarios() throws Exception;

    public List<TiposUsuariosDTO> getDataTiposUsuarios()
        throws Exception;
}
