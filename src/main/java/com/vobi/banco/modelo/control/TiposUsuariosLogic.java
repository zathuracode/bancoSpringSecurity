package com.vobi.banco.modelo.control;

import com.vobi.banco.dataaccess.dao.*;
import com.vobi.banco.exceptions.*;
import com.vobi.banco.modelo.*;
import com.vobi.banco.modelo.dto.TiposUsuariosDTO;
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
@Service("TiposUsuariosLogic")
public class TiposUsuariosLogic implements ITiposUsuariosLogic {
    private static final Logger log = LoggerFactory.getLogger(TiposUsuariosLogic.class);

    /**
     * DAO injected by Spring that manages TiposUsuarios entities
     *
     */
    @Autowired
    private ITiposUsuariosDAO tiposUsuariosDAO;

    /**
    * DAO injected by Spring that manages Usuarios entities
    *
    */
    @Autowired
    private IUsuariosDAO usuariosDAO;

    @Transactional(readOnly = true)
    public List<TiposUsuarios> getTiposUsuarios() throws Exception {
        log.debug("finding all TiposUsuarios instances");

        List<TiposUsuarios> list = new ArrayList<TiposUsuarios>();

        try {
            list = tiposUsuariosDAO.findAll();
        } catch (Exception e) {
            log.error("finding all TiposUsuarios failed", e);
            throw new ZMessManager().new GettingException(ZMessManager.ALL +
                "TiposUsuarios");
        } finally {
        }

        return list;
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void saveTiposUsuarios(TiposUsuarios entity)
        throws Exception {
        log.debug("saving TiposUsuarios instance");

        try {
            if (entity.getTusuCodigo() == null) {
                throw new ZMessManager().new EmptyFieldException("tusuCodigo");
            }

            if ((entity.getTusuCodigo() != null) &&
                    (Utilities.checkNumberAndCheckWithPrecisionAndScale("" +
                        entity.getTusuCodigo(), 10, 0) == false)) {
                throw new ZMessManager().new NotValidFormatException(
                    "tusuCodigo");
            }

            if (entity.getTusuNombre() == null) {
                throw new ZMessManager().new EmptyFieldException("tusuNombre");
            }

            if ((entity.getTusuNombre() != null) &&
                    (Utilities.checkWordAndCheckWithlength(
                        entity.getTusuNombre(), 50) == false)) {
                throw new ZMessManager().new NotValidFormatException(
                    "tusuNombre");
            }

            if (getTiposUsuarios(entity.getTusuCodigo()) != null) {
                throw new ZMessManager(ZMessManager.ENTITY_WITHSAMEKEY);
            }

            tiposUsuariosDAO.save(entity);

            log.debug("save TiposUsuarios successful");
        } catch (Exception e) {
            log.error("save TiposUsuarios failed", e);
            throw e;
        } finally {
        }
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void deleteTiposUsuarios(TiposUsuarios entity)
        throws Exception {
        log.debug("deleting TiposUsuarios instance");

        if (entity == null) {
            throw new ZMessManager().new NullEntityExcepcion("TiposUsuarios");
        }

        if (entity.getTusuCodigo() == null) {
            throw new ZMessManager().new EmptyFieldException("tusuCodigo");
        }

        List<Usuarios> usuarioses = null;

        try {
            usuarioses = usuariosDAO.findByProperty("tiposUsuarios.tusuCodigo",
                    entity.getTusuCodigo());

            if (Utilities.validationsList(usuarioses) == true) {
                throw new ZMessManager().new DeletingException("usuarioses");
            }

            tiposUsuariosDAO.delete(entity);

            log.debug("delete TiposUsuarios successful");
        } catch (Exception e) {
            log.error("delete TiposUsuarios failed", e);
            throw e;
        } finally {
        }
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void updateTiposUsuarios(TiposUsuarios entity)
        throws Exception {
        log.debug("updating TiposUsuarios instance");

        try {
            if (entity == null) {
                throw new ZMessManager().new NullEntityExcepcion(
                    "TiposUsuarios");
            }

            if (entity.getTusuCodigo() == null) {
                throw new ZMessManager().new EmptyFieldException("tusuCodigo");
            }

            if ((entity.getTusuCodigo() != null) &&
                    (Utilities.checkNumberAndCheckWithPrecisionAndScale("" +
                        entity.getTusuCodigo(), 10, 0) == false)) {
                throw new ZMessManager().new NotValidFormatException(
                    "tusuCodigo");
            }

            if (entity.getTusuNombre() == null) {
                throw new ZMessManager().new EmptyFieldException("tusuNombre");
            }

            if ((entity.getTusuNombre() != null) &&
                    (Utilities.checkWordAndCheckWithlength(
                        entity.getTusuNombre(), 50) == false)) {
                throw new ZMessManager().new NotValidFormatException(
                    "tusuNombre");
            }

            tiposUsuariosDAO.update(entity);

            log.debug("update TiposUsuarios successful");
        } catch (Exception e) {
            log.error("update TiposUsuarios failed", e);
            throw e;
        } finally {
        }
    }

    @Transactional(readOnly = true)
    public List<TiposUsuariosDTO> getDataTiposUsuarios()
        throws Exception {
        try {
            List<TiposUsuarios> tiposUsuarios = tiposUsuariosDAO.findAll();

            List<TiposUsuariosDTO> tiposUsuariosDTO = new ArrayList<TiposUsuariosDTO>();

            for (TiposUsuarios tiposUsuariosTmp : tiposUsuarios) {
                TiposUsuariosDTO tiposUsuariosDTO2 = new TiposUsuariosDTO();

                tiposUsuariosDTO2.setTusuCodigo(tiposUsuariosTmp.getTusuCodigo());
                tiposUsuariosDTO2.setTusuNombre((tiposUsuariosTmp.getTusuNombre() != null)
                    ? tiposUsuariosTmp.getTusuNombre() : null);
                tiposUsuariosDTO.add(tiposUsuariosDTO2);
            }

            return tiposUsuariosDTO;
        } catch (Exception e) {
            throw e;
        }
    }

    @Transactional(readOnly = true)
    public TiposUsuarios getTiposUsuarios(Long tusuCodigo)
        throws Exception {
        log.debug("getting TiposUsuarios instance");

        TiposUsuarios entity = null;

        try {
            entity = tiposUsuariosDAO.findById(tusuCodigo);
        } catch (Exception e) {
            log.error("get TiposUsuarios failed", e);
            throw new ZMessManager().new FindingException("TiposUsuarios");
        } finally {
        }

        return entity;
    }

    @Transactional(readOnly = true)
    public List<TiposUsuarios> findPageTiposUsuarios(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception {
        List<TiposUsuarios> entity = null;

        try {
            entity = tiposUsuariosDAO.findPage(sortColumnName, sortAscending,
                    startRow, maxResults);
        } catch (Exception e) {
            throw new ZMessManager().new FindingException("TiposUsuarios Count");
        } finally {
        }

        return entity;
    }

    @Transactional(readOnly = true)
    public Long findTotalNumberTiposUsuarios() throws Exception {
        Long entity = null;

        try {
            entity = tiposUsuariosDAO.count();
        } catch (Exception e) {
            throw new ZMessManager().new FindingException("TiposUsuarios Count");
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
    public List<TiposUsuarios> findByCriteria(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception {
        List<TiposUsuarios> list = new ArrayList<TiposUsuarios>();
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
            list = tiposUsuariosDAO.findByCriteria(where);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        } finally {
        }

        return list;
    }
}
