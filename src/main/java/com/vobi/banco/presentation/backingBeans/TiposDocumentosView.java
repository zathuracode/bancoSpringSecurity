package com.vobi.banco.presentation.backingBeans;

import com.vobi.banco.exceptions.*;
import com.vobi.banco.modelo.*;
import com.vobi.banco.modelo.dto.TiposDocumentosDTO;
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
public class TiposDocumentosView implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final Logger log = LoggerFactory.getLogger(TiposDocumentosView.class);
    private InputText txtTdocNombre;
    private InputText txtTdocCodigo;
    private CommandButton btnSave;
    private CommandButton btnModify;
    private CommandButton btnDelete;
    private CommandButton btnClear;
    private List<TiposDocumentosDTO> data;
    private TiposDocumentosDTO selectedTiposDocumentos;
    private TiposDocumentos entity;
    private boolean showDialog;
    @ManagedProperty(value = "#{BusinessDelegatorView}")
    private IBusinessDelegatorView businessDelegatorView;

    public TiposDocumentosView() {
        super();
    }

    public void rowEventListener(RowEditEvent e) {
        try {
            TiposDocumentosDTO tiposDocumentosDTO = (TiposDocumentosDTO) e.getObject();

            if (txtTdocNombre == null) {
                txtTdocNombre = new InputText();
            }

            txtTdocNombre.setValue(tiposDocumentosDTO.getTdocNombre());

            if (txtTdocCodigo == null) {
                txtTdocCodigo = new InputText();
            }

            txtTdocCodigo.setValue(tiposDocumentosDTO.getTdocCodigo());

            Long tdocCodigo = FacesUtils.checkLong(txtTdocCodigo);
            entity = businessDelegatorView.getTiposDocumentos(tdocCodigo);

            action_modify();
        } catch (Exception ex) {
        }
    }

    public String action_new() {
        action_clear();
        selectedTiposDocumentos = null;
        setShowDialog(true);

        return "";
    }

    public String action_clear() {
        entity = null;
        selectedTiposDocumentos = null;

        if (txtTdocNombre != null) {
            txtTdocNombre.setValue(null);
            txtTdocNombre.setDisabled(true);
        }

        if (txtTdocCodigo != null) {
            txtTdocCodigo.setValue(null);
            txtTdocCodigo.setDisabled(false);
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
            Long tdocCodigo = FacesUtils.checkLong(txtTdocCodigo);
            entity = (tdocCodigo != null)
                ? businessDelegatorView.getTiposDocumentos(tdocCodigo) : null;
        } catch (Exception e) {
            entity = null;
        }

        if (entity == null) {
            txtTdocNombre.setDisabled(false);
            txtTdocCodigo.setDisabled(false);
            btnSave.setDisabled(false);
        } else {
            txtTdocNombre.setValue(entity.getTdocNombre());
            txtTdocNombre.setDisabled(false);
            txtTdocCodigo.setValue(entity.getTdocCodigo());
            txtTdocCodigo.setDisabled(true);
            btnSave.setDisabled(false);

            if (btnDelete != null) {
                btnDelete.setDisabled(false);
            }
        }
    }

    public String action_edit(ActionEvent evt) {
        selectedTiposDocumentos = (TiposDocumentosDTO) (evt.getComponent()
                                                           .getAttributes()
                                                           .get("selectedTiposDocumentos"));
        txtTdocNombre.setValue(selectedTiposDocumentos.getTdocNombre());
        txtTdocNombre.setDisabled(false);
        txtTdocCodigo.setValue(selectedTiposDocumentos.getTdocCodigo());
        txtTdocCodigo.setDisabled(true);
        btnSave.setDisabled(false);
        setShowDialog(true);

        return "";
    }

    public String action_save() {
        try {
            if ((selectedTiposDocumentos == null) && (entity == null)) {
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
            entity = new TiposDocumentos();

            Long tdocCodigo = FacesUtils.checkLong(txtTdocCodigo);

            entity.setTdocCodigo(tdocCodigo);
            entity.setTdocNombre(FacesUtils.checkString(txtTdocNombre));
            businessDelegatorView.saveTiposDocumentos(entity);
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
                Long tdocCodigo = new Long(selectedTiposDocumentos.getTdocCodigo());
                entity = businessDelegatorView.getTiposDocumentos(tdocCodigo);
            }

            entity.setTdocNombre(FacesUtils.checkString(txtTdocNombre));
            businessDelegatorView.updateTiposDocumentos(entity);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
        } catch (Exception e) {
            data = null;
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_delete_datatable(ActionEvent evt) {
        try {
            selectedTiposDocumentos = (TiposDocumentosDTO) (evt.getComponent()
                                                               .getAttributes()
                                                               .get("selectedTiposDocumentos"));

            Long tdocCodigo = new Long(selectedTiposDocumentos.getTdocCodigo());
            entity = businessDelegatorView.getTiposDocumentos(tdocCodigo);
            action_delete();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_delete_master() {
        try {
            Long tdocCodigo = FacesUtils.checkLong(txtTdocCodigo);
            entity = businessDelegatorView.getTiposDocumentos(tdocCodigo);
            action_delete();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public void action_delete() throws Exception {
        try {
            businessDelegatorView.deleteTiposDocumentos(entity);
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
            selectedTiposDocumentos = (TiposDocumentosDTO) (evt.getComponent()
                                                               .getAttributes()
                                                               .get("selectedTiposDocumentos"));

            Long tdocCodigo = new Long(selectedTiposDocumentos.getTdocCodigo());
            entity = businessDelegatorView.getTiposDocumentos(tdocCodigo);
            businessDelegatorView.deleteTiposDocumentos(entity);
            data.remove(selectedTiposDocumentos);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYDELETED);
            action_clear();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_modifyWitDTO(Long tdocCodigo, String tdocNombre)
        throws Exception {
        try {
            entity.setTdocNombre(FacesUtils.checkString(tdocNombre));
            businessDelegatorView.updateTiposDocumentos(entity);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
        } catch (Exception e) {
            //renderManager.getOnDemandRenderer("TiposDocumentosView").requestRender();
            FacesUtils.addErrorMessage(e.getMessage());
            throw e;
        }

        return "";
    }

    public InputText getTxtTdocNombre() {
        return txtTdocNombre;
    }

    public void setTxtTdocNombre(InputText txtTdocNombre) {
        this.txtTdocNombre = txtTdocNombre;
    }

    public InputText getTxtTdocCodigo() {
        return txtTdocCodigo;
    }

    public void setTxtTdocCodigo(InputText txtTdocCodigo) {
        this.txtTdocCodigo = txtTdocCodigo;
    }

    public List<TiposDocumentosDTO> getData() {
        try {
            if (data == null) {
                data = businessDelegatorView.getDataTiposDocumentos();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return data;
    }

    public void setData(List<TiposDocumentosDTO> tiposDocumentosDTO) {
        this.data = tiposDocumentosDTO;
    }

    public TiposDocumentosDTO getSelectedTiposDocumentos() {
        return selectedTiposDocumentos;
    }

    public void setSelectedTiposDocumentos(TiposDocumentosDTO tiposDocumentos) {
        this.selectedTiposDocumentos = tiposDocumentos;
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
