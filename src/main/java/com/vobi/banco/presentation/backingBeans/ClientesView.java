package com.vobi.banco.presentation.backingBeans;

import com.vobi.banco.exceptions.*;
import com.vobi.banco.modelo.*;
import com.vobi.banco.modelo.dto.ClientesDTO;
import com.vobi.banco.presentation.businessDelegate.*;
import com.vobi.banco.utilities.*;

import org.primefaces.component.calendar.*;
import org.primefaces.component.commandbutton.CommandButton;
import org.primefaces.component.inputtext.InputText;

import org.primefaces.event.RowEditEvent;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;

import java.sql.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.TimeZone;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;


/**
 * @author Zathura Code Generator http://zathuracode.org/
 * www.zathuracode.org
 *
 */
@ManagedBean
@ViewScoped
public class ClientesView implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final Logger log = LoggerFactory.getLogger(ClientesView.class);
    private InputText txtCliDireccion;
    private InputText txtCliMail;
    private InputText txtCliNombre;
    private InputText txtCliTelefono;
    private InputText txtTdocCodigo_TiposDocumentos;
    private InputText txtCliId;
    private CommandButton btnSave;
    private CommandButton btnModify;
    private CommandButton btnDelete;
    private CommandButton btnClear;
    private List<ClientesDTO> data;
    private ClientesDTO selectedClientes;
    private Clientes entity;
    private boolean showDialog;
    @ManagedProperty(value = "#{BusinessDelegatorView}")
    private IBusinessDelegatorView businessDelegatorView;

    public ClientesView() {
        super();
    }

    public void rowEventListener(RowEditEvent e) {
        try {
            ClientesDTO clientesDTO = (ClientesDTO) e.getObject();

            if (txtCliDireccion == null) {
                txtCliDireccion = new InputText();
            }

            txtCliDireccion.setValue(clientesDTO.getCliDireccion());

            if (txtCliMail == null) {
                txtCliMail = new InputText();
            }

            txtCliMail.setValue(clientesDTO.getCliMail());

            if (txtCliNombre == null) {
                txtCliNombre = new InputText();
            }

            txtCliNombre.setValue(clientesDTO.getCliNombre());

            if (txtCliTelefono == null) {
                txtCliTelefono = new InputText();
            }

            txtCliTelefono.setValue(clientesDTO.getCliTelefono());

            if (txtTdocCodigo_TiposDocumentos == null) {
                txtTdocCodigo_TiposDocumentos = new InputText();
            }

            txtTdocCodigo_TiposDocumentos.setValue(clientesDTO.getTdocCodigo_TiposDocumentos());

            if (txtCliId == null) {
                txtCliId = new InputText();
            }

            txtCliId.setValue(clientesDTO.getCliId());

            Long cliId = FacesUtils.checkLong(txtCliId);
            entity = businessDelegatorView.getClientes(cliId);

            action_modify();
        } catch (Exception ex) {
        }
    }

    public String action_new() {
        action_clear();
        selectedClientes = null;
        setShowDialog(true);

        return "";
    }

    public String action_clear() {
        entity = null;
        selectedClientes = null;

        if (txtCliDireccion != null) {
            txtCliDireccion.setValue(null);
            txtCliDireccion.setDisabled(true);
        }

        if (txtCliMail != null) {
            txtCliMail.setValue(null);
            txtCliMail.setDisabled(true);
        }

        if (txtCliNombre != null) {
            txtCliNombre.setValue(null);
            txtCliNombre.setDisabled(true);
        }

        if (txtCliTelefono != null) {
            txtCliTelefono.setValue(null);
            txtCliTelefono.setDisabled(true);
        }

        if (txtTdocCodigo_TiposDocumentos != null) {
            txtTdocCodigo_TiposDocumentos.setValue(null);
            txtTdocCodigo_TiposDocumentos.setDisabled(true);
        }

        if (txtCliId != null) {
            txtCliId.setValue(null);
            txtCliId.setDisabled(false);
        }

        if (btnSave != null) {
            btnSave.setDisabled(true);
        }

        if (btnDelete != null) {
            btnDelete.setDisabled(true);
        }

        return "";
    }

    public void listener_txtId() {
        try {
            Long cliId = FacesUtils.checkLong(txtCliId);
            entity = (cliId != null) ? businessDelegatorView.getClientes(cliId)
                                     : null;
        } catch (Exception e) {
            entity = null;
        }

        if (entity == null) {
            txtCliDireccion.setDisabled(false);
            txtCliMail.setDisabled(false);
            txtCliNombre.setDisabled(false);
            txtCliTelefono.setDisabled(false);
            txtTdocCodigo_TiposDocumentos.setDisabled(false);
            txtCliId.setDisabled(false);
            btnSave.setDisabled(false);
        } else {
            txtCliDireccion.setValue(entity.getCliDireccion());
            txtCliDireccion.setDisabled(false);
            txtCliMail.setValue(entity.getCliMail());
            txtCliMail.setDisabled(false);
            txtCliNombre.setValue(entity.getCliNombre());
            txtCliNombre.setDisabled(false);
            txtCliTelefono.setValue(entity.getCliTelefono());
            txtCliTelefono.setDisabled(false);
            txtTdocCodigo_TiposDocumentos.setValue(entity.getTiposDocumentos()
                                                         .getTdocCodigo());
            txtTdocCodigo_TiposDocumentos.setDisabled(false);
            txtCliId.setValue(entity.getCliId());
            txtCliId.setDisabled(true);
            btnSave.setDisabled(false);

            if (btnDelete != null) {
                btnDelete.setDisabled(false);
            }
        }
    }

    public String action_edit(ActionEvent evt) {
        selectedClientes = (ClientesDTO) (evt.getComponent().getAttributes()
                                             .get("selectedClientes"));
        txtCliDireccion.setValue(selectedClientes.getCliDireccion());
        txtCliDireccion.setDisabled(false);
        txtCliMail.setValue(selectedClientes.getCliMail());
        txtCliMail.setDisabled(false);
        txtCliNombre.setValue(selectedClientes.getCliNombre());
        txtCliNombre.setDisabled(false);
        txtCliTelefono.setValue(selectedClientes.getCliTelefono());
        txtCliTelefono.setDisabled(false);
        txtTdocCodigo_TiposDocumentos.setValue(selectedClientes.getTdocCodigo_TiposDocumentos());
        txtTdocCodigo_TiposDocumentos.setDisabled(false);
        txtCliId.setValue(selectedClientes.getCliId());
        txtCliId.setDisabled(true);
        btnSave.setDisabled(false);
        setShowDialog(true);

        return "";
    }

    public String action_save() {
        try {
            if ((selectedClientes == null) && (entity == null)) {
                action_create();
            } else {
                action_modify();
            }

            data = null;
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_create() {
        try {
            entity = new Clientes();

            Long cliId = FacesUtils.checkLong(txtCliId);

            entity.setCliDireccion(FacesUtils.checkString(txtCliDireccion));
            entity.setCliId(cliId);
            entity.setCliMail(FacesUtils.checkString(txtCliMail));
            entity.setCliNombre(FacesUtils.checkString(txtCliNombre));
            entity.setCliTelefono(FacesUtils.checkString(txtCliTelefono));
            entity.setTiposDocumentos((FacesUtils.checkLong(
                    txtTdocCodigo_TiposDocumentos) != null)
                ? businessDelegatorView.getTiposDocumentos(FacesUtils.checkLong(
                        txtTdocCodigo_TiposDocumentos)) : null);
            businessDelegatorView.saveClientes(entity);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYSAVED);
            action_clear();
        } catch (Exception e) {
            entity = null;
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_modify() {
        try {
            if (entity == null) {
                Long cliId = new Long(selectedClientes.getCliId());
                entity = businessDelegatorView.getClientes(cliId);
            }

            entity.setCliDireccion(FacesUtils.checkString(txtCliDireccion));
            entity.setCliMail(FacesUtils.checkString(txtCliMail));
            entity.setCliNombre(FacesUtils.checkString(txtCliNombre));
            entity.setCliTelefono(FacesUtils.checkString(txtCliTelefono));
            entity.setTiposDocumentos((FacesUtils.checkLong(
                    txtTdocCodigo_TiposDocumentos) != null)
                ? businessDelegatorView.getTiposDocumentos(FacesUtils.checkLong(
                        txtTdocCodigo_TiposDocumentos)) : null);
            businessDelegatorView.updateClientes(entity);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
        } catch (Exception e) {
            data = null;
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_delete_datatable(ActionEvent evt) {
        try {
            selectedClientes = (ClientesDTO) (evt.getComponent().getAttributes()
                                                 .get("selectedClientes"));

            Long cliId = new Long(selectedClientes.getCliId());
            entity = businessDelegatorView.getClientes(cliId);
            action_delete();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_delete_master() {
        try {
            Long cliId = FacesUtils.checkLong(txtCliId);
            entity = businessDelegatorView.getClientes(cliId);
            action_delete();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public void action_delete() throws Exception {
        try {
            businessDelegatorView.deleteClientes(entity);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYDELETED);
            action_clear();
            data = null;
        } catch (Exception e) {
            throw e;
        }
    }

    public String action_closeDialog() {
        setShowDialog(false);
        action_clear();

        return "";
    }

    public String actionDeleteDataTableEditable(ActionEvent evt) {
        try {
            selectedClientes = (ClientesDTO) (evt.getComponent().getAttributes()
                                                 .get("selectedClientes"));

            Long cliId = new Long(selectedClientes.getCliId());
            entity = businessDelegatorView.getClientes(cliId);
            businessDelegatorView.deleteClientes(entity);
            data.remove(selectedClientes);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYDELETED);
            action_clear();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_modifyWitDTO(String cliDireccion, Long cliId,
        String cliMail, String cliNombre, String cliTelefono,
        Long tdocCodigo_TiposDocumentos) throws Exception {
        try {
            entity.setCliDireccion(FacesUtils.checkString(cliDireccion));
            entity.setCliMail(FacesUtils.checkString(cliMail));
            entity.setCliNombre(FacesUtils.checkString(cliNombre));
            entity.setCliTelefono(FacesUtils.checkString(cliTelefono));
            businessDelegatorView.updateClientes(entity);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
        } catch (Exception e) {
            //renderManager.getOnDemandRenderer("ClientesView").requestRender();
            FacesUtils.addErrorMessage(e.getMessage());
            throw e;
        }

        return "";
    }

    public InputText getTxtCliDireccion() {
        return txtCliDireccion;
    }

    public void setTxtCliDireccion(InputText txtCliDireccion) {
        this.txtCliDireccion = txtCliDireccion;
    }

    public InputText getTxtCliMail() {
        return txtCliMail;
    }

    public void setTxtCliMail(InputText txtCliMail) {
        this.txtCliMail = txtCliMail;
    }

    public InputText getTxtCliNombre() {
        return txtCliNombre;
    }

    public void setTxtCliNombre(InputText txtCliNombre) {
        this.txtCliNombre = txtCliNombre;
    }

    public InputText getTxtCliTelefono() {
        return txtCliTelefono;
    }

    public void setTxtCliTelefono(InputText txtCliTelefono) {
        this.txtCliTelefono = txtCliTelefono;
    }

    public InputText getTxtTdocCodigo_TiposDocumentos() {
        return txtTdocCodigo_TiposDocumentos;
    }

    public void setTxtTdocCodigo_TiposDocumentos(
        InputText txtTdocCodigo_TiposDocumentos) {
        this.txtTdocCodigo_TiposDocumentos = txtTdocCodigo_TiposDocumentos;
    }

    public InputText getTxtCliId() {
        return txtCliId;
    }

    public void setTxtCliId(InputText txtCliId) {
        this.txtCliId = txtCliId;
    }

    public List<ClientesDTO> getData() {
        try {
            if (data == null) {
                data = businessDelegatorView.getDataClientes();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return data;
    }

    public void setData(List<ClientesDTO> clientesDTO) {
        this.data = clientesDTO;
    }

    public ClientesDTO getSelectedClientes() {
        return selectedClientes;
    }

    public void setSelectedClientes(ClientesDTO clientes) {
        this.selectedClientes = clientes;
    }

    public CommandButton getBtnSave() {
        return btnSave;
    }

    public void setBtnSave(CommandButton btnSave) {
        this.btnSave = btnSave;
    }

    public CommandButton getBtnModify() {
        return btnModify;
    }

    public void setBtnModify(CommandButton btnModify) {
        this.btnModify = btnModify;
    }

    public CommandButton getBtnDelete() {
        return btnDelete;
    }

    public void setBtnDelete(CommandButton btnDelete) {
        this.btnDelete = btnDelete;
    }

    public CommandButton getBtnClear() {
        return btnClear;
    }

    public void setBtnClear(CommandButton btnClear) {
        this.btnClear = btnClear;
    }

    public TimeZone getTimeZone() {
        return java.util.TimeZone.getDefault();
    }

    public IBusinessDelegatorView getBusinessDelegatorView() {
        return businessDelegatorView;
    }

    public void setBusinessDelegatorView(
        IBusinessDelegatorView businessDelegatorView) {
        this.businessDelegatorView = businessDelegatorView;
    }

    public boolean isShowDialog() {
        return showDialog;
    }

    public void setShowDialog(boolean showDialog) {
        this.showDialog = showDialog;
    }
}
