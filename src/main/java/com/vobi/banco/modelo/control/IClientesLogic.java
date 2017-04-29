package com.vobi.banco.modelo.control;

import com.vobi.banco.modelo.Clientes;
import com.vobi.banco.modelo.dto.ClientesDTO;

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
public interface IClientesLogic {
    public List<Clientes> getClientes() throws Exception;

    /**
         * Save an new Clientes entity
         */
    public void saveClientes(Clientes entity) throws Exception;

    /**
         * Delete an existing Clientes entity
         *
         */
    public void deleteClientes(Clientes entity) throws Exception;

    /**
        * Update an existing Clientes entity
        *
        */
    public void updateClientes(Clientes entity) throws Exception;

    /**
         * Load an existing Clientes entity
         *
         */
    public Clientes getClientes(Long cliId) throws Exception;

    public List<Clientes> findByCriteria(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception;

    public List<Clientes> findPageClientes(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception;

    public Long findTotalNumberClientes() throws Exception;

    public List<ClientesDTO> getDataClientes() throws Exception;
}
