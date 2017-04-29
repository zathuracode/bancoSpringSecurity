package com.vobi.banco.presentation.backingBeans;

import com.vobi.banco.exceptions.*;
import com.vobi.banco.modelo.*;
import com.vobi.banco.modelo.dto.CuentasDTO;
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
public class CuentasView implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final Logger log = LoggerFactory.getLogger(CuentasView.class);
    private InputText txtCueActiva;
    private InputText txtCueClave;
    private InputText txtCueSaldo;
    private InputText txtCliId_Clientes;
    private InputText txtCueNumero;
    private CommandButton btnSave;
    private CommandButton btnModify;
    private CommandButton btnDelete;
    private CommandButton btnClear;
    private List<CuentasDTO> data;
    private CuentasDTO selectedCuentas;
    private Cuentas entity;
    private boolean showDialog;
    @ManagedProperty(value = "#{BusinessDelegatorView}")
    private IBusinessDelegatorView businessDelegatorView;

    public CuentasView() {
        super();
    }

    public void rowEventListener(RowEditEvent e) {
        try {
            CuentasDTO cuentasDTO = (CuentasDTO) e.getObject();

            if (txtCueActiva == null) {
                txtCueActiva = new InputText();
            }

            txtCueActiva.setValue(cuentasDTO.getCueActiva());

            if (txtCueClave == null) {
                txtCueClave = new InputText();
            }

            txtCueClave.setValue(cuentasDTO.getCueClave());

            if (txtCueSaldo == null) {
                txtCueSaldo = new InputText();
            }

            txtCueSaldo.setValue(cuentasDTO.getCueSaldo());

            if (txtCliId_Clientes == null) {
                txtCliId_Clientes = new InputText();
            }

            txtCliId_Clientes.setValue(cuentasDTO.getCliId_Clientes());

            if (txtCueNumero == null) {
                txtCueNumero = new InputText();
            }

            txtCueNumero.setValue(cuentasDTO.getCueNumero());

            String cueNumero = FacesUtils.checkString(txtCueNumero);
            entity = businessDelegatorView.getCuentas(cueNumero);

            action_modify();
        } catch (Exception ex) {
        }
    }

    public String action_new() {
        action_clear();
        selectedCuentas = null;
        setShowDialog(true);

        return "";
    }

    public String action_clear() {
        entity = null;
        selectedCuentas = null;

        if (txtCueActiva != null) {
            txtCueActiva.setValue(null);
            txtCueActiva.setDisabled(true);
        }

        if (txtCueClave != null) {
            txtCueClave.setValue(null);
            txtCueClave.setDisabled(true);
        }

        if (txtCueSaldo != null) {
            txtCueSaldo.setValue(null);
            txtCueSaldo.setDisabled(true);
        }

        if (txtCliId_Clientes != null) {
            txtCliId_Clientes.setValue(null);
            txtCliId_Clientes.setDisabled(true);
        }

        if (txtCueNumero != null) {
            txtCueNumero.setValue(null);
            txtCueNumero.setDisabled(false);
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
            String cueNumero = FacesUtils.checkString(txtCueNumero);
            entity = (cueNumero != null)
                ? businessDelegatorView.getCuentas(cueNumero) : null;
        } catch (Exception e) {
            entity = null;
        }

        if (entity == null) {
            txtCueActiva.setDisabled(false);
            txtCueClave.setDisabled(false);
            txtCueSaldo.setDisabled(false);
            txtCliId_Clientes.setDisabled(false);
            txtCueNumero.setDisabled(false);
            btnSave.setDisabled(false);
        } else {
            txtCueActiva.setValue(entity.getCueActiva());
            txtCueActiva.setDisabled(false);
            txtCueClave.setValue(entity.getCueClave());
            txtCueClave.setDisabled(false);
            txtCueSaldo.setValue(entity.getCueSaldo());
            txtCueSaldo.setDisabled(false);
            txtCliId_Clientes.setValue(entity.getClientes().getCliId());
            txtCliId_Clientes.setDisabled(false);
            txtCueNumero.setValue(entity.getCueNumero());
            txtCueNumero.setDisabled(true);
            btnSave.setDisabled(false);

            if (btnDelete != null) {
                btnDelete.setDisabled(false);
            }
        }
    }

    public String action_edit(ActionEvent evt) {
        selectedCuentas = (CuentasDTO) (evt.getComponent().getAttributes()
                                           .get("selectedCuentas"));
        txtCueActiva.setValue(selectedCuentas.getCueActiva());
        txtCueActiva.setDisabled(false);
        txtCueClave.setValue(selectedCuentas.getCueClave());
        txtCueClave.setDisabled(false);
        txtCueSaldo.setValue(selectedCuentas.getCueSaldo());
        txtCueSaldo.setDisabled(false);
        txtCliId_Clientes.setValue(selectedCuentas.getCliId_Clientes());
        txtCliId_Clientes.setDisabled(false);
        txtCueNumero.setValue(selectedCuentas.getCueNumero());
        txtCueNumero.setDisabled(true);
        btnSave.setDisabled(false);
        setShowDialog(true);

        return "";
    }

    public String action_save() {
        try {
            if ((selectedCuentas == null) && (entity == null)) {
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
            entity = new Cuentas();

            String cueNumero = FacesUtils.checkString(txtCueNumero);

            entity.setCueActiva(FacesUtils.checkString(txtCueActiva));
            entity.setCueClave(FacesUtils.checkString(txtCueClave));
            entity.setCueNumero(cueNumero);
            entity.setCueSaldo(FacesUtils.checkDouble(txtCueSaldo));
            entity.setClientes((FacesUtils.checkLong(txtCliId_Clientes) != null)
                ? businessDelegatorView.getClientes(FacesUtils.checkLong(
                        txtCliId_Clientes)) : null);
            businessDelegatorView.saveCuentas(entity);
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
                String cueNumero = new String(selectedCuentas.getCueNumero());
                entity = businessDelegatorView.getCuentas(cueNumero);
            }

            entity.setCueActiva(FacesUtils.checkString(txtCueActiva));
            entity.setCueClave(FacesUtils.checkString(txtCueClave));
            entity.setCueSaldo(FacesUtils.checkDouble(txtCueSaldo));
            entity.setClientes((FacesUtils.checkLong(txtCliId_Clientes) != null)
                ? businessDelegatorView.getClientes(FacesUtils.checkLong(
                        txtCliId_Clientes)) : null);
            businessDelegatorView.updateCuentas(entity);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
        } catch (Exception e) {
            data = null;
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_delete_datatable(ActionEvent evt) {
        try {
            selectedCuentas = (CuentasDTO) (evt.getComponent().getAttributes()
                                               .get("selectedCuentas"));

            String cueNumero = new String(selectedCuentas.getCueNumero());
            entity = businessDelegatorView.getCuentas(cueNumero);
            action_delete();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_delete_master() {
        try {
            String cueNumero = FacesUtils.checkString(txtCueNumero);
            entity = businessDelegatorView.getCuentas(cueNumero);
            action_delete();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public void action_delete() throws Exception {
        try {
            businessDelegatorView.deleteCuentas(entity);
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
            selectedCuentas = (CuentasDTO) (evt.getComponent().getAttributes()
                                               .get("selectedCuentas"));

            String cueNumero = new String(selectedCuentas.getCueNumero());
            entity = businessDelegatorView.getCuentas(cueNumero);
            businessDelegatorView.deleteCuentas(entity);
            data.remove(selectedCuentas);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYDELETED);
            action_clear();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_modifyWitDTO(String cueActiva, String cueClave,
        String cueNumero, Double cueSaldo, Long cliId_Clientes)
        throws Exception {
        try {
            entity.setCueActiva(FacesUtils.checkString(cueActiva));
            entity.setCueClave(FacesUtils.checkString(cueClave));
            entity.setCueSaldo(FacesUtils.checkDouble(cueSaldo));
            businessDelegatorView.updateCuentas(entity);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
        } catch (Exception e) {
            //renderManager.getOnDemandRenderer("CuentasView").requestRender();
            FacesUtils.addErrorMessage(e.getMessage());
            throw e;
        }

        return "";
    }

    public InputText getTxtCueActiva() {
        return txtCueActiva;
    }

    public void setTxtCueActiva(InputText txtCueActiva) {
        this.txtCueActiva = txtCueActiva;
    }

    public InputText getTxtCueClave() {
        return txtCueClave;
    }

    public void setTxtCueClave(InputText txtCueClave) {
        this.txtCueClave = txtCueClave;
    }

    public InputText getTxtCueSaldo() {
        return txtCueSaldo;
    }

    public void setTxtCueSaldo(InputText txtCueSaldo) {
        this.txtCueSaldo = txtCueSaldo;
    }

    public InputText getTxtCliId_Clientes() {
        return txtCliId_Clientes;
    }

    public void setTxtCliId_Clientes(InputText txtCliId_Clientes) {
        this.txtCliId_Clientes = txtCliId_Clientes;
    }

    public InputText getTxtCueNumero() {
        return txtCueNumero;
    }

    public void setTxtCueNumero(InputText txtCueNumero) {
        this.txtCueNumero = txtCueNumero;
    }

    public List<CuentasDTO> getData() {
        try {
            if (data == null) {
                data = businessDelegatorView.getDataCuentas();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return data;
    }

    public void setData(List<CuentasDTO> cuentasDTO) {
        this.data = cuentasDTO;
    }

    public CuentasDTO getSelectedCuentas() {
        return selectedCuentas;
    }

    public void setSelectedCuentas(CuentasDTO cuentas) {
        this.selectedCuentas = cuentas;
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
