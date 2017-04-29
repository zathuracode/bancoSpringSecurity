package com.vobi.banco.modelo.control;

import com.vobi.banco.modelo.Cuentas;
import com.vobi.banco.modelo.dto.CuentasDTO;

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
public interface ICuentasLogic {
    public List<Cuentas> getCuentas() throws Exception;

    /**
         * Save an new Cuentas entity
         */
    public void saveCuentas(Cuentas entity) throws Exception;

    /**
         * Delete an existing Cuentas entity
         *
         */
    public void deleteCuentas(Cuentas entity) throws Exception;

    /**
        * Update an existing Cuentas entity
        *
        */
    public void updateCuentas(Cuentas entity) throws Exception;

    /**
         * Load an existing Cuentas entity
         *
         */
    public Cuentas getCuentas(String cueNumero) throws Exception;

    public List<Cuentas> findByCriteria(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception;

    public List<Cuentas> findPageCuentas(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception;

    public Long findTotalNumberCuentas() throws Exception;

    public List<CuentasDTO> getDataCuentas() throws Exception;
}
