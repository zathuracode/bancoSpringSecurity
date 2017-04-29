package com.vobi.banco.modelo.control;

import com.vobi.banco.dataaccess.dao.*;
import com.vobi.banco.exceptions.*;
import com.vobi.banco.modelo.*;
import com.vobi.banco.modelo.dto.ConsignacionesDTO;
import com.vobi.banco.utilities.Utilities;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.context.annotation.Scope;

import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;


/**
* @author Zathura Code Generator http://zathuracode.org/
* www.zathuracode.org
*
*/
@Scope("singleton")
@Service("ConsignacionesLogic")
public class ConsignacionesLogic implements IConsignacionesLogic {
    private static final Logger log = LoggerFactory.getLogger(ConsignacionesLogic.class);

    /**
     * DAO injected by Spring that manages Consignaciones entities
     *
     */
    @Autowired
    private IConsignacionesDAO consignacionesDAO;

    /**
    * Logic injected by Spring that manages Cuentas entities
    *
    */
    @Autowired
    ICuentasLogic logicCuentas1;

    /**
    * Logic injected by Spring that manages Usuarios entities
    *
    */
    @Autowired
    IUsuariosLogic logicUsuarios2;

    @Transactional(readOnly = true)
    public List<Consignaciones> getConsignaciones() throws Exception {
        log.debug("finding all Consignaciones instances");

        List<Consignaciones> list = new ArrayList<Consignaciones>();

        try {
            list = consignacionesDAO.findAll();
        } catch (Exception e) {
            log.error("finding all Consignaciones failed", e);
            throw new ZMessManager().new GettingException(ZMessManager.ALL +
                "Consignaciones");
        } finally {
        }

        return list;
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void saveConsignaciones(Consignaciones entity)
        throws Exception {
        log.debug("saving Consignaciones instance");

        try {
            if (entity.getCuentas() == null) {
                throw new ZMessManager().new ForeignException("cuentas");
            }

            if (entity.getUsuarios() == null) {
                throw new ZMessManager().new ForeignException("usuarios");
            }

            if (entity.getId().getConCodigo() == null) {
                throw new ZMessManager().new EmptyFieldException("conCodigo");
            }

            if ((entity.getId().getConCodigo() != null) &&
                    (Utilities.checkNumberAndCheckWithPrecisionAndScale("" +
                        entity.getId().getConCodigo(), 10, 0) == false)) {
                throw new ZMessManager().new NotValidFormatException(
                    "conCodigo");
            }

            if (entity.getId().getCueNumero() == null) {
                throw new ZMessManager().new EmptyFieldException("cueNumero");
            }

            if ((entity.getId().getCueNumero() != null) &&
                    (Utilities.checkWordAndCheckWithlength(
                        entity.getId().getCueNumero(), 30) == false)) {
                throw new ZMessManager().new NotValidFormatException(
                    "cueNumero");
            }

            if (entity.getConDescripcion() == null) {
                throw new ZMessManager().new EmptyFieldException(
                    "conDescripcion");
            }

            if ((entity.getConDescripcion() != null) &&
                    (Utilities.checkWordAndCheckWithlength(
                        entity.getConDescripcion(), 50) == false)) {
                throw new ZMessManager().new NotValidFormatException(
                    "conDescripcion");
            }

            if (entity.getConFecha() == null) {
                throw new ZMessManager().new EmptyFieldException("conFecha");
            }

            if (entity.getConValor() == null) {
                throw new ZMessManager().new EmptyFieldException("conValor");
            }

            if ((entity.getConValor() != null) &&
                    (Utilities.checkNumberAndCheckWithPrecisionAndScale("" +
                        entity.getConValor(), 10, 0) == false)) {
                throw new ZMessManager().new NotValidFormatException("conValor");
            }

            if (entity.getCuentas().getCueNumero() == null) {
                throw new ZMessManager().new EmptyFieldException(
                    "cueNumero_Cuentas");
            }

            if ((entity.getCuentas().getCueNumero() != null) &&
                    (Utilities.checkWordAndCheckWithlength(
                        entity.getCuentas().getCueNumero(), 30) == false)) {
                throw new ZMessManager().new NotValidFormatException(
                    "cueNumero_Cuentas");
            }

            if (entity.getUsuarios().getUsuCedula() == null) {
                throw new ZMessManager().new EmptyFieldException(
                    "usuCedula_Usuarios");
            }

            if ((entity.getUsuarios().getUsuCedula() != null) &&
                    (Utilities.checkNumberAndCheckWithPrecisionAndScale("" +
                        entity.getUsuarios().getUsuCedula(), 10, 0) == false)) {
                throw new ZMessManager().new NotValidFormatException(
                    "usuCedula_Usuarios");
            }

            if (getConsignaciones(entity.getId()) != null) {
                throw new ZMessManager(ZMessManager.ENTITY_WITHSAMEKEY);
            }

            consignacionesDAO.save(entity);

            log.debug("save Consignaciones successful");
        } catch (Exception e) {
            log.error("save Consignaciones failed", e);
            throw e;
        } finally {
        }
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void deleteConsignaciones(Consignaciones entity)
        throws Exception {
        log.debug("deleting Consignaciones instance");

        if (entity == null) {
            throw new ZMessManager().new NullEntityExcepcion("Consignaciones");
        }

        if (entity.getId().getConCodigo() == null) {
            throw new ZMessManager().new EmptyFieldException("conCodigo");
        }

        if (entity.getId().getCueNumero() == null) {
            throw new ZMessManager().new EmptyFieldException("cueNumero");
        }

        try {
            consignacionesDAO.delete(entity);

            log.debug("delete Consignaciones successful");
        } catch (Exception e) {
            log.error("delete Consignaciones failed", e);
            throw e;
        } finally {
        }
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void updateConsignaciones(Consignaciones entity)
        throws Exception {
        log.debug("updating Consignaciones instance");

        try {
            if (entity == null) {
                throw new ZMessManager().new NullEntityExcepcion(
                    "Consignaciones");
            }

            if (entity.getCuentas() == null) {
                throw new ZMessManager().new ForeignException("cuentas");
            }

            if (entity.getUsuarios() == null) {
                throw new ZMessManager().new ForeignException("usuarios");
            }

            if (entity.getId().getConCodigo() == null) {
                throw new ZMessManager().new EmptyFieldException("conCodigo");
            }

            if ((entity.getId().getConCodigo() != null) &&
                    (Utilities.checkNumberAndCheckWithPrecisionAndScale("" +
                        entity.getId().getConCodigo(), 10, 0) == false)) {
                throw new ZMessManager().new NotValidFormatException(
                    "conCodigo");
            }

            if (entity.getId().getCueNumero() == null) {
                throw new ZMessManager().new EmptyFieldException("cueNumero");
            }

            if ((entity.getId().getCueNumero() != null) &&
                    (Utilities.checkWordAndCheckWithlength(
                        entity.getId().getCueNumero(), 30) == false)) {
                throw new ZMessManager().new NotValidFormatException(
                    "cueNumero");
            }

            if (entity.getConDescripcion() == null) {
                throw new ZMessManager().new EmptyFieldException(
                    "conDescripcion");
            }

            if ((entity.getConDescripcion() != null) &&
                    (Utilities.checkWordAndCheckWithlength(
                        entity.getConDescripcion(), 50) == false)) {
                throw new ZMessManager().new NotValidFormatException(
                    "conDescripcion");
            }

            if (entity.getConFecha() == null) {
                throw new ZMessManager().new EmptyFieldException("conFecha");
            }

            if (entity.getConValor() == null) {
                throw new ZMessManager().new EmptyFieldException("conValor");
            }

            if ((entity.getConValor() != null) &&
                    (Utilities.checkNumberAndCheckWithPrecisionAndScale("" +
                        entity.getConValor(), 10, 0) == false)) {
                throw new ZMessManager().new NotValidFormatException("conValor");
            }

            if (entity.getCuentas().getCueNumero() == null) {
                throw new ZMessManager().new EmptyFieldException(
                    "cueNumero_Cuentas");
            }

            if ((entity.getCuentas().getCueNumero() != null) &&
                    (Utilities.checkWordAndCheckWithlength(
                        entity.getCuentas().getCueNumero(), 30) == false)) {
                throw new ZMessManager().new NotValidFormatException(
                    "cueNumero_Cuentas");
            }

            if (entity.getUsuarios().getUsuCedula() == null) {
                throw new ZMessManager().new EmptyFieldException(
                    "usuCedula_Usuarios");
            }

            if ((entity.getUsuarios().getUsuCedula() != null) &&
                    (Utilities.checkNumberAndCheckWithPrecisionAndScale("" +
                        entity.getUsuarios().getUsuCedula(), 10, 0) == false)) {
                throw new ZMessManager().new NotValidFormatException(
                    "usuCedula_Usuarios");
            }

            consignacionesDAO.update(entity);

            log.debug("update Consignaciones successful");
        } catch (Exception e) {
            log.error("update Consignaciones failed", e);
            throw e;
        } finally {
        }
    }

    @Transactional(readOnly = true)
    public List<ConsignacionesDTO> getDataConsignaciones()
        throws Exception {
        try {
            List<Consignaciones> consignaciones = consignacionesDAO.findAll();

            List<ConsignacionesDTO> consignacionesDTO = new ArrayList<ConsignacionesDTO>();

            for (Consignaciones consignacionesTmp : consignaciones) {
                ConsignacionesDTO consignacionesDTO2 = new ConsignacionesDTO();

                consignacionesDTO2.setConCodigo(consignacionesTmp.getId()
                                                                 .getConCodigo());
                consignacionesDTO2.setCueNumero(consignacionesTmp.getId()
                                                                 .getCueNumero());
                consignacionesDTO2.setConDescripcion((consignacionesTmp.getConDescripcion() != null)
                    ? consignacionesTmp.getConDescripcion() : null);
                consignacionesDTO2.setConFecha(consignacionesTmp.getConFecha());
                consignacionesDTO2.setConValor((consignacionesTmp.getConValor() != null)
                    ? consignacionesTmp.getConValor() : null);
                consignacionesDTO2.setCueNumero_Cuentas((consignacionesTmp.getCuentas()
                                                                          .getCueNumero() != null)
                    ? consignacionesTmp.getCuentas().getCueNumero() : null);
                consignacionesDTO2.setUsuCedula_Usuarios((consignacionesTmp.getUsuarios()
                                                                           .getUsuCedula() != null)
                    ? consignacionesTmp.getUsuarios().getUsuCedula() : null);
                consignacionesDTO.add(consignacionesDTO2);
            }

            return consignacionesDTO;
        } catch (Exception e) {
            throw e;
        }
    }

    @Transactional(readOnly = true)
    public Consignaciones getConsignaciones(ConsignacionesId id)
        throws Exception {
        log.debug("getting Consignaciones instance");

        Consignaciones entity = null;

        try {
            entity = consignacionesDAO.findById(id);
        } catch (Exception e) {
            log.error("get Consignaciones failed", e);
            throw new ZMessManager().new FindingException("Consignaciones");
        } finally {
        }

        return entity;
    }

    @Transactional(readOnly = true)
    public List<Consignaciones> findPageConsignaciones(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception {
        List<Consignaciones> entity = null;

        try {
            entity = consignacionesDAO.findPage(sortColumnName, sortAscending,
                    startRow, maxResults);
        } catch (Exception e) {
            throw new ZMessManager().new FindingException(
                "Consignaciones Count");
        } finally {
        }

        return entity;
    }

    @Transactional(readOnly = true)
    public Long findTotalNumberConsignaciones() throws Exception {
        Long entity = null;

        try {
            entity = consignacionesDAO.count();
        } catch (Exception e) {
            throw new ZMessManager().new FindingException(
                "Consignaciones Count");
        } finally {
        }

        return entity;
    }

    /**
    *
    * @param varibles
    *            este arreglo debera tener:
    *
    * [0] = String variable = (String) varibles[i]; representa como se llama la
    * variable en el pojo
    *
    * [1] = Boolean booVariable = (Boolean) varibles[i + 1]; representa si el
    * valor necesita o no ''(comillas simples)usado para campos de tipo string
    *
    * [2] = Object value = varibles[i + 2]; representa el valor que se va a
    * buscar en la BD
    *
    * [3] = String comparator = (String) varibles[i + 3]; representa que tipo
    * de busqueda voy a hacer.., ejemplo: where nombre=william o where nombre<>william,
        * en este campo iria el tipo de comparador que quiero si es = o <>
            *
            * Se itera de 4 en 4..., entonces 4 registros del arreglo representan 1
            * busqueda en un campo, si se ponen mas pues el continuara buscando en lo
            * que se le ingresen en los otros 4
            *
            *
            * @param variablesBetween
            *
            * la diferencia son estas dos posiciones
            *
            * [0] = String variable = (String) varibles[j]; la variable ne la BD que va
            * a ser buscada en un rango
            *
            * [1] = Object value = varibles[j + 1]; valor 1 para buscar en un rango
            *
            * [2] = Object value2 = varibles[j + 2]; valor 2 para buscar en un rango
            * ejempolo: a > 1 and a < 5 --> 1 seria value y 5 seria value2
                *
                * [3] = String comparator1 = (String) varibles[j + 3]; comparador 1
                * ejemplo: a comparator1 1 and a < 5
                    *
                    * [4] = String comparator2 = (String) varibles[j + 4]; comparador 2
                    * ejemplo: a comparador1>1  and a comparador2<5  (el original: a > 1 and a <
                            * 5) *
                            * @param variablesBetweenDates(en
                            *            este caso solo para mysql)
                            *  [0] = String variable = (String) varibles[k]; el nombre de la variable que hace referencia a
                            *            una fecha
                            *
                            * [1] = Object object1 = varibles[k + 2]; fecha 1 a comparar(deben ser
                            * dates)
                            *
                            * [2] = Object object2 = varibles[k + 3]; fecha 2 a comparar(deben ser
                            * dates)
                            *
                            * esto hace un between entre las dos fechas.
                            *
                            * @return lista con los objetos que se necesiten
                            * @throws Exception
                            */
    @Transactional(readOnly = true)
    public List<Consignaciones> findByCriteria(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception {
        List<Consignaciones> list = new ArrayList<Consignaciones>();
        String where = new String();
        String tempWhere = new String();

        if (variables != null) {
            for (int i = 0; i < variables.length; i++) {
                if ((variables[i] != null) && (variables[i + 1] != null) &&
                        (variables[i + 2] != null) &&
                        (variables[i + 3] != null)) {
                    String variable = (String) variables[i];
                    Boolean booVariable = (Boolean) variables[i + 1];
                    Object value = variables[i + 2];
                    String comparator = (String) variables[i + 3];

                    if (booVariable.booleanValue()) {
                        tempWhere = (tempWhere.length() == 0)
                            ? ("(model." + variable + " " + comparator + " \'" +
                            value + "\' )")
                            : (tempWhere + " AND (model." + variable + " " +
                            comparator + " \'" + value + "\' )");
                    } else {
                        tempWhere = (tempWhere.length() == 0)
                            ? ("(model." + variable + " " + comparator + " " +
                            value + " )")
                            : (tempWhere + " AND (model." + variable + " " +
                            comparator + " " + value + " )");
                    }
                }

                i = i + 3;
            }
        }

        if (variablesBetween != null) {
            for (int j = 0; j < variablesBetween.length; j++) {
                if ((variablesBetween[j] != null) &&
                        (variablesBetween[j + 1] != null) &&
                        (variablesBetween[j + 2] != null) &&
                        (variablesBetween[j + 3] != null) &&
                        (variablesBetween[j + 4] != null)) {
                    String variable = (String) variablesBetween[j];
                    Object value = variablesBetween[j + 1];
                    Object value2 = variablesBetween[j + 2];
                    String comparator1 = (String) variablesBetween[j + 3];
                    String comparator2 = (String) variablesBetween[j + 4];
                    tempWhere = (tempWhere.length() == 0)
                        ? ("(" + value + " " + comparator1 + " " + variable +
                        " and " + variable + " " + comparator2 + " " + value2 +
                        " )")
                        : (tempWhere + " AND (" + value + " " + comparator1 +
                        " " + variable + " and " + variable + " " +
                        comparator2 + " " + value2 + " )");
                }

                j = j + 4;
            }
        }

        if (variablesBetweenDates != null) {
            for (int k = 0; k < variablesBetweenDates.length; k++) {
                if ((variablesBetweenDates[k] != null) &&
                        (variablesBetweenDates[k + 1] != null) &&
                        (variablesBetweenDates[k + 2] != null)) {
                    String variable = (String) variablesBetweenDates[k];
                    Object object1 = variablesBetweenDates[k + 1];
                    Object object2 = variablesBetweenDates[k + 2];
                    String value = null;
                    String value2 = null;

                    try {
                        Date date1 = (Date) object1;
                        Date date2 = (Date) object2;
                        value = Utilities.formatDateWithoutTimeInAStringForBetweenWhere(date1);
                        value2 = Utilities.formatDateWithoutTimeInAStringForBetweenWhere(date2);
                    } catch (Exception e) {
                        list = null;
                        throw e;
                    }

                    tempWhere = (tempWhere.length() == 0)
                        ? ("(model." + variable + " between \'" + value +
                        "\' and \'" + value2 + "\')")
                        : (tempWhere + " AND (model." + variable +
                        " between \'" + value + "\' and \'" + value2 + "\')");
                }

                k = k + 2;
            }
        }

        if (tempWhere.length() == 0) {
            where = null;
        } else {
            where = "(" + tempWhere + ")";
        }

        try {
            list = consignacionesDAO.findByCriteria(where);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        } finally {
        }

        return list;
    }
}
