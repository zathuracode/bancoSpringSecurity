package com.vobi.banco.modelo.control;

import com.vobi.banco.modelo.Consignaciones;
import com.vobi.banco.modelo.ConsignacionesId;
import com.vobi.banco.modelo.dto.ConsignacionesDTO;

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
public interface IConsignacionesLogic {
    public List<Consignaciones> getConsignaciones() throws Exception;

    /**
         * Save an new Consignaciones entity
         */
    public void saveConsignaciones(Consignaciones entity)
        throws Exception;

    /**
         * Delete an existing Consignaciones entity
         *
         */
    public void deleteConsignaciones(Consignaciones entity)
        throws Exception;

    /**
        * Update an existing Consignaciones entity
        *
        */
    public void updateConsignaciones(Consignaciones entity)
        throws Exception;

    /**
         * Load an existing Consignaciones entity
         *
         */
    public Consignaciones getConsignaciones(ConsignacionesId id)
        throws Exception;

    public List<Consignaciones> findByCriteria(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception;

    public List<Consignaciones> findPageConsignaciones(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception;

    public Long findTotalNumberConsignaciones() throws Exception;

    public List<ConsignacionesDTO> getDataConsignaciones()
        throws Exception;
}
