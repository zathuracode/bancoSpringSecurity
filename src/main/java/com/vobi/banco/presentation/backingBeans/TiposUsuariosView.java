package com.vobi.banco.presentation.backingBeans;

import com.vobi.banco.exceptions.*;
import com.vobi.banco.modelo.*;
import com.vobi.banco.modelo.dto.TiposUsuariosDTO;
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
public class TiposUsuariosView implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final Logger log = LoggerFactory.getLogger(TiposUsuariosView.class);
    private InputText txtTusuNombre;
    private InputText txtTusuCodigo;
    private CommandButton btnSave;
    private CommandButton btnModify;
    private CommandButton btnDelete;
    private CommandButton btnClear;
    private List<TiposUsuariosDTO> data;
    private TiposUsuariosDTO selectedTiposUsuarios;
    private TiposUsuarios entity;
    private boolean showDialog;
    @ManagedProperty(value = "#{BusinessDelegatorView}")
    private IBusinessDelegatorView businessDelegatorView;

    public TiposUsuariosView() {
        super();
    }

    public void rowEventListener(RowEditEvent e) {
        try {
            TiposUsuariosDTO tiposUsuariosDTO = (TiposUsuariosDTO) e.getObject();

            if (txtTusuNombre == null) {
                txtTusuNombre = new InputText();
            }

            txtTusuNombre.setValue(tiposUsuariosDTO.getTusuNombre());

            if (txtTusuCodigo == null) {
                txtTusuCodigo = new InputText();
            }

            txtTusuCodigo.setValue(tiposUsuariosDTO.getTusuCodigo());

            Long tusuCodigo = FacesUtils.checkLong(txtTusuCodigo);
            entity = businessDelegatorView.getTiposUsuarios(tusuCodigo);

            action_modify();
        } catch (Exception ex) {
        }
    }

    public String action_new() {
        action_clear();
        selectedTiposUsuarios = null;
        setShowDialog(true);

        return "";
    }

    public String action_clear() {
        entity = null;
        selectedTiposUsuarios = null;

        if (txtTusuNombre != null) {
            txtTusuNombre.setValue(null);
            txtTusuNombre.setDisabled(true);
        }

        if (txtTusuCodigo != null) {
            txtTusuCodigo.setValue(null);
            txtTusuCodigo.setDisabled(false);
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
            Long tusuCodigo = FacesUtils.checkLong(txtTusuCodigo);
            entity = (tusuCodigo != null)
                ? businessDelegatorView.getTiposUsuarios(tusuCodigo) : null;
        } catch (Exception e) {
            entity = null;
        }

        if (entity == null) {
            txtTusuNombre.setDisabled(false);
            txtTusuCodigo.setDisabled(false);
            btnSave.setDisabled(false);
        } else {
            txtTusuNombre.setValue(entity.getTusuNombre());
            txtTusuNombre.setDisabled(false);
            txtTusuCodigo.setValue(entity.getTusuCodigo());
            txtTusuCodigo.setDisabled(true);
            btnSave.setDisabled(false);

            if (btnDelete != null) {
                btnDelete.setDisabled(false);
            }
        }
    }

    public String action_edit(ActionEvent evt) {
        selectedTiposUsuarios = (TiposUsuariosDTO) (evt.getComponent()
                                                       .getAttributes()
                                                       .get("selectedTiposUsuarios"));
        txtTusuNombre.setValue(selectedTiposUsuarios.getTusuNombre());
        txtTusuNombre.setDisabled(false);
        txtTusuCodigo.setValue(selectedTiposUsuarios.getTusuCodigo());
        txtTusuCodigo.setDisabled(true);
        btnSave.setDisabled(false);
        setShowDialog(true);

        return "";
    }

    public String action_save() {
        try {
            if ((selectedTiposUsuarios == null) && (entity == null)) {
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
            entity = new TiposUsuarios();

            Long tusuCodigo = FacesUtils.checkLong(txtTusuCodigo);

            entity.setTusuCodigo(tusuCodigo);
            entity.setTusuNombre(FacesUtils.checkString(txtTusuNombre));
            businessDelegatorView.saveTiposUsuarios(entity);
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
                Long tusuCodigo = new Long(selectedTiposUsuarios.getTusuCodigo());
                entity = businessDelegatorView.getTiposUsuarios(tusuCodigo);
            }

            entity.setTusuNombre(FacesUtils.checkString(txtTusuNombre));
            businessDelegatorView.updateTiposUsuarios(entity);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
        } catch (Exception e) {
            data = null;
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_delete_datatable(ActionEvent evt) {
        try {
            selectedTiposUsuarios = (TiposUsuariosDTO) (evt.getComponent()
                                                           .getAttributes()
                                                           .get("selectedTiposUsuarios"));

            Long tusuCodigo = new Long(selectedTiposUsuarios.getTusuCodigo());
            entity = businessDelegatorView.getTiposUsuarios(tusuCodigo);
            action_delete();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_delete_master() {
        try {
            Long tusuCodigo = FacesUtils.checkLong(txtTusuCodigo);
            entity = businessDelegatorView.getTiposUsuarios(tusuCodigo);
            action_delete();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public void action_delete() throws Exception {
        try {
            businessDelegatorView.deleteTiposUsuarios(entity);
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
            selectedTiposUsuarios = (TiposUsuariosDTO) (evt.getComponent()
                                                           .getAttributes()
                                                           .get("selectedTiposUsuarios"));

            Long tusuCodigo = new Long(selectedTiposUsuarios.getTusuCodigo());
            entity = businessDelegatorView.getTiposUsuarios(tusuCodigo);
            businessDelegatorView.deleteTiposUsuarios(entity);
            data.remove(selectedTiposUsuarios);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYDELETED);
            action_clear();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_modifyWitDTO(Long tusuCodigo, String tusuNombre)
        throws Exception {
        try {
            entity.setTusuNombre(FacesUtils.checkString(tusuNombre));
            businessDelegatorView.updateTiposUsuarios(entity);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
        } catch (Exception e) {
            //renderManager.getOnDemandRenderer("TiposUsuariosView").requestRender();
            FacesUtils.addErrorMessage(e.getMessage());
            throw e;
        }

        return "";
    }

    public InputText getTxtTusuNombre() {
        return txtTusuNombre;
    }

    public void setTxtTusuNombre(InputText txtTusuNombre) {
        this.txtTusuNombre = txtTusuNombre;
    }

    public InputText getTxtTusuCodigo() {
        return txtTusuCodigo;
    }

    public void setTxtTusuCodigo(InputText txtTusuCodigo) {
        this.txtTusuCodigo = txtTusuCodigo;
    }

    public List<TiposUsuariosDTO> getData() {
        try {
            if (data == null) {
                data = businessDelegatorView.getDataTiposUsuarios();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return data;
    }

    public void setData(List<TiposUsuariosDTO> tiposUsuariosDTO) {
        this.data = tiposUsuariosDTO;
    }

    public TiposUsuariosDTO getSelectedTiposUsuarios() {
        return selectedTiposUsuarios;
    }

    public void setSelectedTiposUsuarios(TiposUsuariosDTO tiposUsuarios) {
        this.selectedTiposUsuarios = tiposUsuarios;
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
