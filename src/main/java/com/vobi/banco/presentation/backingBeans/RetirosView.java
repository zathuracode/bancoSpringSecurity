package com.vobi.banco.presentation.backingBeans;

import com.vobi.banco.exceptions.*;
import com.vobi.banco.modelo.*;
import com.vobi.banco.modelo.dto.RetirosDTO;
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
public class RetirosView implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final Logger log = LoggerFactory.getLogger(RetirosView.class);
    private InputText txtRetDescripcion;
    private InputText txtRetValor;
    private InputText txtCueNumero_Cuentas;
    private InputText txtUsuCedula_Usuarios;
    private InputText txtRetCodigo;
    private InputText txtCueNumero;
    private Calendar txtRetFecha;
    private CommandButton btnSave;
    private CommandButton btnModify;
    private CommandButton btnDelete;
    private CommandButton btnClear;
    private List<RetirosDTO> data;
    private RetirosDTO selectedRetiros;
    private Retiros entity;
    private boolean showDialog;
    @ManagedProperty(value = "#{BusinessDelegatorView}")
    private IBusinessDelegatorView businessDelegatorView;

    public RetirosView() {
        super();
    }

    public void rowEventListener(RowEditEvent e) {
        try {
            RetirosDTO retirosDTO = (RetirosDTO) e.getObject();

            if (txtRetDescripcion == null) {
                txtRetDescripcion = new InputText();
            }

            txtRetDescripcion.setValue(retirosDTO.getRetDescripcion());

            if (txtRetValor == null) {
                txtRetValor = new InputText();
            }

            txtRetValor.setValue(retirosDTO.getRetValor());

            if (txtCueNumero_Cuentas == null) {
                txtCueNumero_Cuentas = new InputText();
            }

            txtCueNumero_Cuentas.setValue(retirosDTO.getCueNumero_Cuentas());

            if (txtUsuCedula_Usuarios == null) {
                txtUsuCedula_Usuarios = new InputText();
            }

            txtUsuCedula_Usuarios.setValue(retirosDTO.getUsuCedula_Usuarios());

            if (txtRetCodigo == null) {
                txtRetCodigo = new InputText();
            }

            txtRetCodigo.setValue(retirosDTO.getRetCodigo());

            if (txtCueNumero == null) {
                txtCueNumero = new InputText();
            }

            txtCueNumero.setValue(retirosDTO.getCueNumero());

            if (txtRetFecha == null) {
                txtRetFecha = new Calendar();
            }

            txtRetFecha.setValue(retirosDTO.getRetFecha());

            RetirosId id = new RetirosId();
            id.setRetCodigo((((txtRetCodigo.getValue()) == null) ||
                (txtRetCodigo.getValue()).equals("")) ? null
                                                      : FacesUtils.checkLong(
                    txtRetCodigo));
            id.setCueNumero((((txtCueNumero.getValue()) == null) ||
                (txtCueNumero.getValue()).equals("")) ? null
                                                      : FacesUtils.checkString(
                    txtCueNumero));
            entity = businessDelegatorView.getRetiros(id);

            action_modify();
        } catch (Exception ex) {
        }
    }

    public String action_new() {
        action_clear();
        selectedRetiros = null;
        setShowDialog(true);

        return "";
    }

    public String action_clear() {
        entity = null;
        selectedRetiros = null;

        if (txtRetDescripcion != null) {
            txtRetDescripcion.setValue(null);
            txtRetDescripcion.setDisabled(true);
        }

        if (txtRetValor != null) {
            txtRetValor.setValue(null);
            txtRetValor.setDisabled(true);
        }

        if (txtCueNumero_Cuentas != null) {
            txtCueNumero_Cuentas.setValue(null);
            txtCueNumero_Cuentas.setDisabled(true);
        }

        if (txtUsuCedula_Usuarios != null) {
            txtUsuCedula_Usuarios.setValue(null);
            txtUsuCedula_Usuarios.setDisabled(true);
        }

        if (txtRetFecha != null) {
            txtRetFecha.setValue(null);
            txtRetFecha.setDisabled(true);
        }

        if (txtRetCodigo != null) {
            txtRetCodigo.setValue(null);
            txtRetCodigo.setDisabled(false);
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

    public void listener_txtRetFecha() {
        Date inputDate = (Date) txtRetFecha.getValue();
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        FacesContext.getCurrentInstance()
                    .addMessage("",
            new FacesMessage("Selected Date " + dateFormat.format(inputDate)));
    }

    public void listener_txtId() {
        try {
            RetirosId id = new RetirosId();
            id.setRetCodigo((((txtRetCodigo.getValue()) == null) ||
                (txtRetCodigo.getValue()).equals("")) ? null
                                                      : FacesUtils.checkLong(
                    txtRetCodigo));
            id.setCueNumero((((txtCueNumero.getValue()) == null) ||
                (txtCueNumero.getValue()).equals("")) ? null
                                                      : FacesUtils.checkString(
                    txtCueNumero));
            entity = (id != null) ? businessDelegatorView.getRetiros(id) : null;
        } catch (Exception e) {
            entity = null;
        }

        if (entity == null) {
            txtRetDescripcion.setDisabled(false);
            txtRetValor.setDisabled(false);
            txtCueNumero_Cuentas.setDisabled(false);
            txtUsuCedula_Usuarios.setDisabled(false);
            txtRetFecha.setDisabled(false);
            txtRetCodigo.setDisabled(false);
            txtCueNumero.setDisabled(false);
            btnSave.setDisabled(false);
        } else {
            txtRetDescripcion.setValue(entity.getRetDescripcion());
            txtRetDescripcion.setDisabled(false);
            txtRetFecha.setValue(entity.getRetFecha());
            txtRetFecha.setDisabled(false);
            txtRetValor.setValue(entity.getRetValor());
            txtRetValor.setDisabled(false);
            txtCueNumero_Cuentas.setValue(entity.getCuentas().getCueNumero());
            txtCueNumero_Cuentas.setDisabled(false);
            txtUsuCedula_Usuarios.setValue(entity.getUsuarios().getUsuCedula());
            txtUsuCedula_Usuarios.setDisabled(false);
            txtRetCodigo.setValue(entity.getId().getRetCodigo());
            txtRetCodigo.setDisabled(true);
            txtCueNumero.setValue(entity.getId().getCueNumero());
            txtCueNumero.setDisabled(true);
            btnSave.setDisabled(false);

            if (btnDelete != null) {
                btnDelete.setDisabled(false);
            }
        }
    }

    public String action_edit(ActionEvent evt) {
        selectedRetiros = (RetirosDTO) (evt.getComponent().getAttributes()
                                           .get("selectedRetiros"));
        txtRetDescripcion.setValue(selectedRetiros.getRetDescripcion());
        txtRetDescripcion.setDisabled(false);
        txtRetFecha.setValue(selectedRetiros.getRetFecha());
        txtRetFecha.setDisabled(false);
        txtRetValor.setValue(selectedRetiros.getRetValor());
        txtRetValor.setDisabled(false);
        txtCueNumero_Cuentas.setValue(selectedRetiros.getCueNumero_Cuentas());
        txtCueNumero_Cuentas.setDisabled(false);
        txtUsuCedula_Usuarios.setValue(selectedRetiros.getUsuCedula_Usuarios());
        txtUsuCedula_Usuarios.setDisabled(false);
        txtRetCodigo.setValue(selectedRetiros.getRetCodigo());
        txtRetCodigo.setDisabled(true);
        txtCueNumero.setValue(selectedRetiros.getCueNumero());
        txtCueNumero.setDisabled(true);
        btnSave.setDisabled(false);
        setShowDialog(true);

        return "";
    }

    public String action_save() {
        try {
            if ((selectedRetiros == null) && (entity == null)) {
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
            entity = new Retiros();

            RetirosId id = new RetirosId();
            id.setRetCodigo((((txtRetCodigo.getValue()) == null) ||
                (txtRetCodigo.getValue()).equals("")) ? null
                                                      : FacesUtils.checkLong(
                    txtRetCodigo));
            id.setCueNumero((((txtCueNumero.getValue()) == null) ||
                (txtCueNumero.getValue()).equals("")) ? null
                                                      : FacesUtils.checkString(
                    txtCueNumero));

            entity.setId(id);
            entity.setRetDescripcion(FacesUtils.checkString(txtRetDescripcion));
            entity.setRetFecha(FacesUtils.checkDate(txtRetFecha));
            entity.setRetValor(FacesUtils.checkDouble(txtRetValor));
            entity.setCuentas(businessDelegatorView.getCuentas(
                    entity.getId().getCueNumero()));
            entity.setUsuarios((FacesUtils.checkLong(txtUsuCedula_Usuarios) != null)
                ? businessDelegatorView.getUsuarios(FacesUtils.checkLong(
                        txtUsuCedula_Usuarios)) : null);
            businessDelegatorView.saveRetiros(entity);
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
                RetirosId id = new RetirosId();
                id.setRetCodigo(selectedRetiros.getRetCodigo());
                id.setCueNumero(selectedRetiros.getCueNumero());
                entity = businessDelegatorView.getRetiros(id);
            }

            entity.setRetDescripcion(FacesUtils.checkString(txtRetDescripcion));
            entity.setRetFecha(FacesUtils.checkDate(txtRetFecha));
            entity.setRetValor(FacesUtils.checkDouble(txtRetValor));
            entity.setUsuarios((FacesUtils.checkLong(txtUsuCedula_Usuarios) != null)
                ? businessDelegatorView.getUsuarios(FacesUtils.checkLong(
                        txtUsuCedula_Usuarios)) : null);
            businessDelegatorView.updateRetiros(entity);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
        } catch (Exception e) {
            data = null;
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_delete_datatable(ActionEvent evt) {
        try {
            selectedRetiros = (RetirosDTO) (evt.getComponent().getAttributes()
                                               .get("selectedRetiros"));

            RetirosId id = new RetirosId();
            id.setRetCodigo(selectedRetiros.getRetCodigo());
            id.setCueNumero(selectedRetiros.getCueNumero());
            entity = businessDelegatorView.getRetiros(id);
            action_delete();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_delete_master() {
        try {
            RetirosId id = new RetirosId();
            id.setRetCodigo((((txtRetCodigo.getValue()) == null) ||
                (txtRetCodigo.getValue()).equals("")) ? null
                                                      : FacesUtils.checkLong(
                    txtRetCodigo));
            id.setCueNumero((((txtCueNumero.getValue()) == null) ||
                (txtCueNumero.getValue()).equals("")) ? null
                                                      : FacesUtils.checkString(
                    txtCueNumero));
            entity = businessDelegatorView.getRetiros(id);
            action_delete();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public void action_delete() throws Exception {
        try {
            businessDelegatorView.deleteRetiros(entity);
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
            selectedRetiros = (RetirosDTO) (evt.getComponent().getAttributes()
                                               .get("selectedRetiros"));

            RetirosId id = new RetirosId();
            id.setRetCodigo(selectedRetiros.getRetCodigo());
            id.setCueNumero(selectedRetiros.getCueNumero());
            entity = businessDelegatorView.getRetiros(id);
            businessDelegatorView.deleteRetiros(entity);
            data.remove(selectedRetiros);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYDELETED);
            action_clear();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_modifyWitDTO(Long retCodigo, String cueNumero,
        String retDescripcion, Date retFecha, Double retValor,
        String cueNumero_Cuentas, Long usuCedula_Usuarios)
        throws Exception {
        try {
            entity.setRetDescripcion(FacesUtils.checkString(retDescripcion));
            entity.setRetFecha(FacesUtils.checkDate(retFecha));
            entity.setRetValor(FacesUtils.checkDouble(retValor));
            businessDelegatorView.updateRetiros(entity);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
        } catch (Exception e) {
            //renderManager.getOnDemandRenderer("RetirosView").requestRender();
            FacesUtils.addErrorMessage(e.getMessage());
            throw e;
        }

        return "";
    }

    public InputText getTxtRetDescripcion() {
        return txtRetDescripcion;
    }

    public void setTxtRetDescripcion(InputText txtRetDescripcion) {
        this.txtRetDescripcion = txtRetDescripcion;
    }

    public InputText getTxtRetValor() {
        return txtRetValor;
    }

    public void setTxtRetValor(InputText txtRetValor) {
        this.txtRetValor = txtRetValor;
    }

    public InputText getTxtCueNumero_Cuentas() {
        return txtCueNumero_Cuentas;
    }

    public void setTxtCueNumero_Cuentas(InputText txtCueNumero_Cuentas) {
        this.txtCueNumero_Cuentas = txtCueNumero_Cuentas;
    }

    public InputText getTxtUsuCedula_Usuarios() {
        return txtUsuCedula_Usuarios;
    }

    public void setTxtUsuCedula_Usuarios(InputText txtUsuCedula_Usuarios) {
        this.txtUsuCedula_Usuarios = txtUsuCedula_Usuarios;
    }

    public Calendar getTxtRetFecha() {
        return txtRetFecha;
    }

    public void setTxtRetFecha(Calendar txtRetFecha) {
        this.txtRetFecha = txtRetFecha;
    }

    public InputText getTxtRetCodigo() {
        return txtRetCodigo;
    }

    public void setTxtRetCodigo(InputText txtRetCodigo) {
        this.txtRetCodigo = txtRetCodigo;
    }

    public InputText getTxtCueNumero() {
        return txtCueNumero;
    }

    public void setTxtCueNumero(InputText txtCueNumero) {
        this.txtCueNumero = txtCueNumero;
    }

    public List<RetirosDTO> getData() {
        try {
            if (data == null) {
                data = businessDelegatorView.getDataRetiros();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return data;
    }

    public void setData(List<RetirosDTO> retirosDTO) {
        this.data = retirosDTO;
    }

    public RetirosDTO getSelectedRetiros() {
        return selectedRetiros;
    }

    public void setSelectedRetiros(RetirosDTO retiros) {
        this.selectedRetiros = retiros;
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
