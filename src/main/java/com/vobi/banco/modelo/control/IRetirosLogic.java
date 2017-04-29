package com.vobi.banco.modelo.control;

import com.vobi.banco.modelo.Retiros;
import com.vobi.banco.modelo.RetirosId;
import com.vobi.banco.modelo.dto.RetirosDTO;

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
public interface IRetirosLogic {
    public List<Retiros> getRetiros() throws Exception;

    /**
         * Save an new Retiros entity
         */
    public void saveRetiros(Retiros entity) throws Exception;

    /**
         * Delete an existing Retiros entity
         *
         */
    public void deleteRetiros(Retiros entity) throws Exception;

    /**
        * Update an existing Retiros entity
        *
        */
    public void updateRetiros(Retiros entity) throws Exception;

    /**
         * Load an existing Retiros entity
         *
         */
    public Retiros getRetiros(RetirosId id) throws Exception;

    public List<Retiros> findByCriteria(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception;

    public List<Retiros> findPageRetiros(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception;

    public Long findTotalNumberRetiros() throws Exception;

    public List<RetirosDTO> getDataRetiros() throws Exception;
}
