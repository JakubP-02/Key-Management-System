package com.jsfcourse.access_key;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;

import jakarta.faces.view.ViewScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.context.Flash;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import com.jsf.entities.AccessKey;
import com.jsf.entities.DictionaryValue;
import com.jsf.entities.Unit;
import com.jsf.service.AccessKeyService;
import com.jsf.service.UnitService;

@Named
@ViewScoped
public class AccessKeyEditBB implements Serializable {
    private static final long serialVersionUID = 1L;

    private static final String PAGE_ACCESS_KEY_LIST = "accessKeyList?faces-redirect=true";
    private static final String PAGE_STAY_AT_THE_SAME = null;

    private AccessKey accessKey = new AccessKey();
    private AccessKey loaded = null;

    // Listy do wyboru
    private List<DictionaryValue> keyTypes;
    private List<DictionaryValue> keyStatuses;
    private List<Unit> availableUnits;  // <-- Lokale do wyboru

    // Wybrane ID: typ, status i jednostka
    private Integer selectedTypeId;
    private Integer selectedStatusId;
    private Integer selectedUnitId;     // <-- ID wybranego lokalu

    @Inject
    private AccessKeyService accessKeyService;

    @Inject
    private UnitService unitService; // potrzebny do pobrania listy lokali i/lub find(id)

    @Inject
    private FacesContext context;

    @Inject
    private Flash flash;

    // GETTERY i SETTERY
    
    public AccessKey getAccessKey() {
        return accessKey;
    }

    public void setAccessKey(AccessKey accessKey) {
        this.accessKey = accessKey;
    }

    public List<DictionaryValue> getKeyTypes() {
        return keyTypes;
    }

    public void setKeyTypes(List<DictionaryValue> keyTypes) {
        this.keyTypes = keyTypes;
    }

    public List<DictionaryValue> getKeyStatuses() {
        return keyStatuses;
    }

    public void setKeyStatuses(List<DictionaryValue> keyStatuses) {
        this.keyStatuses = keyStatuses;
    }

    public List<Unit> getAvailableUnits() {
        return availableUnits;
    }

    public void setAvailableUnits(List<Unit> availableUnits) {
        this.availableUnits = availableUnits;
    }

    public Integer getSelectedTypeId() {
        return selectedTypeId;
    }

    public void setSelectedTypeId(Integer selectedTypeId) {
        this.selectedTypeId = selectedTypeId;
    }

    public Integer getSelectedStatusId() {
        return selectedStatusId;
    }

    public void setSelectedStatusId(Integer selectedStatusId) {
        this.selectedStatusId = selectedStatusId;
    }

    public Integer getSelectedUnitId() {
        return selectedUnitId;
    }

    public void setSelectedUnitId(Integer selectedUnitId) {
        this.selectedUnitId = selectedUnitId;
    }

    public void onLoad() throws IOException {
        // 1. Pobranie list słownikowych z AccessKeyService
        keyTypes = accessKeyService.getDictionaryValuesByType(7);    // 'key_type'
        keyStatuses = accessKeyService.getDictionaryValuesByType(4); // 'key_status'

        // 2. Pobranie listy lokali
        availableUnits = unitService.getAllUnits();

        // 3. Odczyt obiektu z Flash (edycja)
        loaded = (AccessKey) flash.get("accessKey");
        if (loaded != null) {
            this.accessKey = loaded;
            
            // Uzupełnienie ID dla typu/statusu klucza
            if (accessKey.getKeyType() != null) {
                selectedTypeId = accessKey.getKeyType().getIdDictionaryValue();
            }
            if (accessKey.getKeyStatus() != null) {
                selectedStatusId = accessKey.getKeyStatus().getIdDictionaryValue();
            }
            
            // Uzupełnienie ID lokalu
            if (accessKey.getUnit() != null && accessKey.getUnit().getIdUnit() != null) {
                selectedUnitId = accessKey.getUnit().getIdUnit();
            }

        } else {
            // Błędne użycie systemu (brak obiektu w flashu)
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Błędne użycie systemu", null));
            context.getExternalContext().getFlash().setKeepMessages(true);
            context.getExternalContext().redirect(PAGE_ACCESS_KEY_LIST);
            context.responseComplete();
        }
    }

    public String saveData() {
        if (loaded == null) {
            return PAGE_STAY_AT_THE_SAME;
        }
        try {
            // Wywołujemy serwis, który sam ustawi keyType, keyStatus, unit
            accessKeyService.saveAccessKey(accessKey,
                                           selectedTypeId,
                                           selectedStatusId,
                                           selectedUnitId);
        } catch (Exception e) {
            e.printStackTrace();
            context.addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_ERROR,
                                 "Wystąpił błąd podczas zapisu klucza", null));
            return PAGE_STAY_AT_THE_SAME;
        }
        return PAGE_ACCESS_KEY_LIST;
    }

}
