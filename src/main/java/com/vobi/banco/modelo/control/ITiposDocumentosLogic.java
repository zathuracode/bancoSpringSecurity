package com.vobi.banco.modelo.control;

import com.vobi.banco.modelo.TiposDocumentos;
import com.vobi.banco.modelo.dto.TiposDocumentosDTO;

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
public interface ITiposDocumentosLogic {
    public List<TiposDocumentos> getTiposDocumentos() throws Exception;

    /**
         * Save an new TiposDocumentos entity
         */
    public void saveTiposDocumentos(TiposDocumentos entity)
        throws Exception;

    /**
         * Delete an existing TiposDocumentos entity
         *
         */
    public void deleteTiposDocumentos(TiposDocumentos entity)
        throws Exception;

    /**
        * Update an existing TiposDocumentos entity
        *
        */
    public void updateTiposDocumentos(TiposDocumentos entity)
        throws Exception;

    /**
         * Load an existing TiposDocumentos entity
         *
         */
    public TiposDocumentos getTiposDocumentos(Long tdocCodigo)
        throws Exception;

    public List<TiposDocumentos> findByCriteria(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception;

    public List<TiposDocumentos> findPageTiposDocumentos(
        String sortColumnName, boolean sortAscending, int startRow,
        int maxResults) throws Exception;

    public Long findTotalNumberTiposDocumentos() throws Exception;

    public List<TiposDocumentosDTO> getDataTiposDocumentos()
        throws Exception;
}
