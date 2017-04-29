package com.vobi.banco.presentation.businessDelegate;

import com.vobi.banco.modelo.Clientes;
import com.vobi.banco.modelo.Consignaciones;
import com.vobi.banco.modelo.ConsignacionesId;
import com.vobi.banco.modelo.Cuentas;
import com.vobi.banco.modelo.Retiros;
import com.vobi.banco.modelo.RetirosId;
import com.vobi.banco.modelo.TiposDocumentos;
import com.vobi.banco.modelo.TiposUsuarios;
import com.vobi.banco.modelo.Usuarios;
import com.vobi.banco.modelo.control.ClientesLogic;
import com.vobi.banco.modelo.control.ConsignacionesLogic;
import com.vobi.banco.modelo.control.CuentasLogic;
import com.vobi.banco.modelo.control.IClientesLogic;
import com.vobi.banco.modelo.control.IConsignacionesLogic;
import com.vobi.banco.modelo.control.ICuentasLogic;
import com.vobi.banco.modelo.control.IRetirosLogic;
import com.vobi.banco.modelo.control.ITiposDocumentosLogic;
import com.vobi.banco.modelo.control.ITiposUsuariosLogic;
import com.vobi.banco.modelo.control.IUsuariosLogic;
import com.vobi.banco.modelo.control.RetirosLogic;
import com.vobi.banco.modelo.control.TiposDocumentosLogic;
import com.vobi.banco.modelo.control.TiposUsuariosLogic;
import com.vobi.banco.modelo.control.UsuariosLogic;
import com.vobi.banco.modelo.dto.ClientesDTO;
import com.vobi.banco.modelo.dto.ConsignacionesDTO;
import com.vobi.banco.modelo.dto.CuentasDTO;
import com.vobi.banco.modelo.dto.RetirosDTO;
import com.vobi.banco.modelo.dto.TiposDocumentosDTO;
import com.vobi.banco.modelo.dto.TiposUsuariosDTO;
import com.vobi.banco.modelo.dto.UsuariosDTO;
import com.vobi.banco.presentation.businessDelegate.IBusinessDelegatorView;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.context.annotation.Scope;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;

import java.sql.*;

import java.util.Date;
import java.util.List;
import java.util.Set;


/**
* Use a Business Delegate to reduce coupling between presentation-tier clients and business services.
* The Business Delegate hides the underlying implementation details of the business service, such as lookup and access details of the EJB architecture.
*
* The Business Delegate acts as a client-side business abstraction; it provides an abstraction for, and thus hides,
* the implementation of the business services. Using a Business Delegate reduces the coupling between presentation-tier clients and
* the system's business services. Depending on the implementation strategy, the Business Delegate may shield clients from possible
* volatility in the implementation of the business service API. Potentially, this reduces the number of changes that must be made to the
* presentation-tier client code when the business service API or its underlying implementation changes.
*
* However, interface methods in the Business Delegate may still require modification if the underlying business service API changes.
* Admittedly, though, it is more likely that changes will be made to the business service rather than to the Business Delegate.
*
* Often, developers are skeptical when a design goal such as abstracting the business layer causes additional upfront work in return
* for future gains. However, using this pattern or its strategies results in only a small amount of additional upfront work and provides
* considerable benefits. The main benefit is hiding the details of the underlying service. For example, the client can become transparent
* to naming and lookup services. The Business Delegate also handles the exceptions from the business services, such as java.rmi.Remote
* exceptions, Java Messages Service (JMS) exceptions and so on. The Business Delegate may intercept such service level exceptions and
* generate application level exceptions instead. Application level exceptions are easier to handle by the clients, and may be user friendly.
* The Business Delegate may also transparently perform any retry or recovery operations necessary in the event of a service failure without
* exposing the client to the problem until it is determined that the problem is not resolvable. These gains present a compelling reason to
* use the pattern.
*
* Another benefit is that the delegate may cache results and references to remote business services. Caching can significantly improve performance,
* because it limits unnecessary and potentially costly round trips over the network.
*
* A Business Delegate uses a component called the Lookup Service. The Lookup Service is responsible for hiding the underlying implementation
* details of the business service lookup code. The Lookup Service may be written as part of the Delegate, but we recommend that it be
* implemented as a separate component, as outlined in the Service Locator pattern (See "Service Locator" on page 368.)
*
* When the Business Delegate is used with a Session Facade, typically there is a one-to-one relationship between the two.
* This one-to-one relationship exists because logic that might have been encapsulated in a Business Delegate relating to its interaction
* with multiple business services (creating a one-to-many relationship) will often be factored back into a Session Facade.
*
* Finally, it should be noted that this pattern could be used to reduce coupling between other tiers, not simply the presentation and the
* business tiers.
*
* @author Zathura Code Generator http://zathuracode.org/
* www.zathuracode.org
*
*/
@Scope("singleton")
@Service("BusinessDelegatorView")
public class BusinessDelegatorView implements IBusinessDelegatorView {
    private static final Logger log = LoggerFactory.getLogger(BusinessDelegatorView.class);
    @Autowired
    private IClientesLogic clientesLogic;
    @Autowired
    private IConsignacionesLogic consignacionesLogic;
    @Autowired
    private ICuentasLogic cuentasLogic;
    @Autowired
    private IRetirosLogic retirosLogic;
    @Autowired
    private ITiposDocumentosLogic tiposDocumentosLogic;
    @Autowired
    private ITiposUsuariosLogic tiposUsuariosLogic;
    @Autowired
    private IUsuariosLogic usuariosLogic;

    public List<Clientes> getClientes() throws Exception {
        return clientesLogic.getClientes();
    }

    public void saveClientes(Clientes entity) throws Exception {
        clientesLogic.saveClientes(entity);
    }

    public void deleteClientes(Clientes entity) throws Exception {
        clientesLogic.deleteClientes(entity);
    }

    public void updateClientes(Clientes entity) throws Exception {
        clientesLogic.updateClientes(entity);
    }

    public Clientes getClientes(Long cliId) throws Exception {
        Clientes clientes = null;

        try {
            clientes = clientesLogic.getClientes(cliId);
        } catch (Exception e) {
            throw e;
        }

        return clientes;
    }

    public List<Clientes> findByCriteriaInClientes(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception {
        return clientesLogic.findByCriteria(variables, variablesBetween,
            variablesBetweenDates);
    }

    public List<Clientes> findPageClientes(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception {
        return clientesLogic.findPageClientes(sortColumnName, sortAscending,
            startRow, maxResults);
    }

    public Long findTotalNumberClientes() throws Exception {
        return clientesLogic.findTotalNumberClientes();
    }

    public List<ClientesDTO> getDataClientes() throws Exception {
        return clientesLogic.getDataClientes();
    }

    public List<Consignaciones> getConsignaciones() throws Exception {
        return consignacionesLogic.getConsignaciones();
    }

    public void saveConsignaciones(Consignaciones entity)
        throws Exception {
        consignacionesLogic.saveConsignaciones(entity);
    }

    public void deleteConsignaciones(Consignaciones entity)
        throws Exception {
        consignacionesLogic.deleteConsignaciones(entity);
    }

    public void updateConsignaciones(Consignaciones entity)
        throws Exception {
        consignacionesLogic.updateConsignaciones(entity);
    }

    public Consignaciones getConsignaciones(ConsignacionesId id)
        throws Exception {
        Consignaciones consignaciones = null;

        try {
            consignaciones = consignacionesLogic.getConsignaciones(id);
        } catch (Exception e) {
            throw e;
        }

        return consignaciones;
    }

    public List<Consignaciones> findByCriteriaInConsignaciones(
        Object[] variables, Object[] variablesBetween,
        Object[] variablesBetweenDates) throws Exception {
        return consignacionesLogic.findByCriteria(variables, variablesBetween,
            variablesBetweenDates);
    }

    public List<Consignaciones> findPageConsignaciones(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception {
        return consignacionesLogic.findPageConsignaciones(sortColumnName,
            sortAscending, startRow, maxResults);
    }

    public Long findTotalNumberConsignaciones() throws Exception {
        return consignacionesLogic.findTotalNumberConsignaciones();
    }

    public List<ConsignacionesDTO> getDataConsignaciones()
        throws Exception {
        return consignacionesLogic.getDataConsignaciones();
    }

    public List<Cuentas> getCuentas() throws Exception {
        return cuentasLogic.getCuentas();
    }

    public void saveCuentas(Cuentas entity) throws Exception {
        cuentasLogic.saveCuentas(entity);
    }

    public void deleteCuentas(Cuentas entity) throws Exception {
        cuentasLogic.deleteCuentas(entity);
    }

    public void updateCuentas(Cuentas entity) throws Exception {
        cuentasLogic.updateCuentas(entity);
    }

    public Cuentas getCuentas(String cueNumero) throws Exception {
        Cuentas cuentas = null;

        try {
            cuentas = cuentasLogic.getCuentas(cueNumero);
        } catch (Exception e) {
            throw e;
        }

        return cuentas;
    }

    public List<Cuentas> findByCriteriaInCuentas(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception {
        return cuentasLogic.findByCriteria(variables, variablesBetween,
            variablesBetweenDates);
    }

    public List<Cuentas> findPageCuentas(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception {
        return cuentasLogic.findPageCuentas(sortColumnName, sortAscending,
            startRow, maxResults);
    }

    public Long findTotalNumberCuentas() throws Exception {
        return cuentasLogic.findTotalNumberCuentas();
    }

    public List<CuentasDTO> getDataCuentas() throws Exception {
        return cuentasLogic.getDataCuentas();
    }

    public List<Retiros> getRetiros() throws Exception {
        return retirosLogic.getRetiros();
    }

    public void saveRetiros(Retiros entity) throws Exception {
        retirosLogic.saveRetiros(entity);
    }

    public void deleteRetiros(Retiros entity) throws Exception {
        retirosLogic.deleteRetiros(entity);
    }

    public void updateRetiros(Retiros entity) throws Exception {
        retirosLogic.updateRetiros(entity);
    }

    public Retiros getRetiros(RetirosId id) throws Exception {
        Retiros retiros = null;

        try {
            retiros = retirosLogic.getRetiros(id);
        } catch (Exception e) {
            throw e;
        }

        return retiros;
    }

    public List<Retiros> findByCriteriaInRetiros(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception {
        return retirosLogic.findByCriteria(variables, variablesBetween,
            variablesBetweenDates);
    }

    public List<Retiros> findPageRetiros(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception {
        return retirosLogic.findPageRetiros(sortColumnName, sortAscending,
            startRow, maxResults);
    }

    public Long findTotalNumberRetiros() throws Exception {
        return retirosLogic.findTotalNumberRetiros();
    }

    public List<RetirosDTO> getDataRetiros() throws Exception {
        return retirosLogic.getDataRetiros();
    }

    public List<TiposDocumentos> getTiposDocumentos() throws Exception {
        return tiposDocumentosLogic.getTiposDocumentos();
    }

    public void saveTiposDocumentos(TiposDocumentos entity)
        throws Exception {
        tiposDocumentosLogic.saveTiposDocumentos(entity);
    }

    public void deleteTiposDocumentos(TiposDocumentos entity)
        throws Exception {
        tiposDocumentosLogic.deleteTiposDocumentos(entity);
    }

    public void updateTiposDocumentos(TiposDocumentos entity)
        throws Exception {
        tiposDocumentosLogic.updateTiposDocumentos(entity);
    }

    public TiposDocumentos getTiposDocumentos(Long tdocCodigo)
        throws Exception {
        TiposDocumentos tiposDocumentos = null;

        try {
            tiposDocumentos = tiposDocumentosLogic.getTiposDocumentos(tdocCodigo);
        } catch (Exception e) {
            throw e;
        }

        return tiposDocumentos;
    }

    public List<TiposDocumentos> findByCriteriaInTiposDocumentos(
        Object[] variables, Object[] variablesBetween,
        Object[] variablesBetweenDates) throws Exception {
        return tiposDocumentosLogic.findByCriteria(variables, variablesBetween,
            variablesBetweenDates);
    }

    public List<TiposDocumentos> findPageTiposDocumentos(
        String sortColumnName, boolean sortAscending, int startRow,
        int maxResults) throws Exception {
        return tiposDocumentosLogic.findPageTiposDocumentos(sortColumnName,
            sortAscending, startRow, maxResults);
    }

    public Long findTotalNumberTiposDocumentos() throws Exception {
        return tiposDocumentosLogic.findTotalNumberTiposDocumentos();
    }

    public List<TiposDocumentosDTO> getDataTiposDocumentos()
        throws Exception {
        return tiposDocumentosLogic.getDataTiposDocumentos();
    }

    public List<TiposUsuarios> getTiposUsuarios() throws Exception {
        return tiposUsuariosLogic.getTiposUsuarios();
    }

    public void saveTiposUsuarios(TiposUsuarios entity)
        throws Exception {
        tiposUsuariosLogic.saveTiposUsuarios(entity);
    }

    public void deleteTiposUsuarios(TiposUsuarios entity)
        throws Exception {
        tiposUsuariosLogic.deleteTiposUsuarios(entity);
    }

    public void updateTiposUsuarios(TiposUsuarios entity)
        throws Exception {
        tiposUsuariosLogic.updateTiposUsuarios(entity);
    }

    public TiposUsuarios getTiposUsuarios(Long tusuCodigo)
        throws Exception {
        TiposUsuarios tiposUsuarios = null;

        try {
            tiposUsuarios = tiposUsuariosLogic.getTiposUsuarios(tusuCodigo);
        } catch (Exception e) {
            throw e;
        }

        return tiposUsuarios;
    }

    public List<TiposUsuarios> findByCriteriaInTiposUsuarios(
        Object[] variables, Object[] variablesBetween,
        Object[] variablesBetweenDates) throws Exception {
        return tiposUsuariosLogic.findByCriteria(variables, variablesBetween,
            variablesBetweenDates);
    }

    public List<TiposUsuarios> findPageTiposUsuarios(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception {
        return tiposUsuariosLogic.findPageTiposUsuarios(sortColumnName,
            sortAscending, startRow, maxResults);
    }

    public Long findTotalNumberTiposUsuarios() throws Exception {
        return tiposUsuariosLogic.findTotalNumberTiposUsuarios();
    }

    public List<TiposUsuariosDTO> getDataTiposUsuarios()
        throws Exception {
        return tiposUsuariosLogic.getDataTiposUsuarios();
    }

    public List<Usuarios> getUsuarios() throws Exception {
        return usuariosLogic.getUsuarios();
    }

    public void saveUsuarios(Usuarios entity) throws Exception {
        usuariosLogic.saveUsuarios(entity);
    }

    public void deleteUsuarios(Usuarios entity) throws Exception {
        usuariosLogic.deleteUsuarios(entity);
    }

    public void updateUsuarios(Usuarios entity) throws Exception {
        usuariosLogic.updateUsuarios(entity);
    }

    public Usuarios getUsuarios(Long usuCedula) throws Exception {
        Usuarios usuarios = null;

        try {
            usuarios = usuariosLogic.getUsuarios(usuCedula);
        } catch (Exception e) {
            throw e;
        }

        return usuarios;
    }

    public List<Usuarios> findByCriteriaInUsuarios(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception {
        return usuariosLogic.findByCriteria(variables, variablesBetween,
            variablesBetweenDates);
    }

    public List<Usuarios> findPageUsuarios(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception {
        return usuariosLogic.findPageUsuarios(sortColumnName, sortAscending,
            startRow, maxResults);
    }

    public Long findTotalNumberUsuarios() throws Exception {
        return usuariosLogic.findTotalNumberUsuarios();
    }

    public List<UsuariosDTO> getDataUsuarios() throws Exception {
        return usuariosLogic.getDataUsuarios();
    }

	@Override
	public Usuarios validarUsuario(String usuLogin, String usuClave) throws Exception {
		return usuariosLogic.validarUsuario(usuLogin, usuClave);
	}
}
