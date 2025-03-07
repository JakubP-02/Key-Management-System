package com.jsfcourse.unit;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;

import jakarta.ejb.EJB;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.context.Flash;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;


import com.jsf.entities.Unit;
import com.jsf.service.UnitService;
import com.jsf.entities.DictionaryValue;

@Named
@ViewScoped
public class UnitEditBB implements Serializable {
	private static final long serialVersionUID = 1L;

	private static final String PAGE_UNIT_LIST = "unitList?faces-redirect=true";
	private static final String PAGE_STAY_AT_THE_SAME = null;

	private Unit unit = new Unit();
	private Unit loaded = null;

	// wpisy/opcje do formularza

	private List<DictionaryValue> unitTypes;
	private List<DictionaryValue> unitStatuses;

	@Inject
	private UnitService unitService;

	@Inject
	private FacesContext context;

	@Inject
	private Flash flash;
	
	private Integer selectedTypeId;
    private Integer selectedStatusId;

	// settery są potrzebne do rozwiązania The class 'com.jsfcourse.unit.UnitEditBB'
	// does not have the property 'unitTypes'.

	public Unit getUnit() {
		return unit;
	}

	public void setUnit(Unit unit) {
		this.unit = unit;
	}

	public List<DictionaryValue> getUnitTypes() {
		return unitTypes;
	}

	public void setUnitTypes(List<DictionaryValue> unitType) {
		this.unitTypes = unitType;
	}

	public List<DictionaryValue> getUnitStatuses() {
		return unitStatuses;
	}

	public void setUnitStatuses(List<DictionaryValue> unitStatuses) {
		this.unitStatuses = unitStatuses;
	}

	// konkretne id przekazywane z formularza

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

	public void onLoad() throws IOException {

		 // Pobranie list słownikowych przez UnitService
        unitTypes = unitService.getDictionaryValuesByType(1); // ID dla 'unit_type'
        unitStatuses = unitService.getDictionaryValuesByType(3); // ID dla 'unit_status'

		// 1. load person passed through session
		// HttpSession session = (HttpSession)
		// context.getExternalContext().getSession(true);
		// loaded = (Person) session.getAttribute("person");

		// 2. load person passed through flash
		loaded = (Unit) flash.get("unit");

		// cleaning: attribute received => delete it from session
		if (loaded != null) {
			unit = loaded;
			// Przypisanie wartości dla selectedTypeId i selectedStatusId
	        
			if (unit.getUnitType() != null) {
	            selectedTypeId = unit.getUnitType().getIdDictionaryValue();
	        }
	        if (unit.getUnitStatus() != null) {
	            selectedStatusId = unit.getUnitStatus().getIdDictionaryValue();
	        }
		} else {

			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Błędne użycie systemu", null));
			context.getExternalContext().getFlash().setKeepMessages(true);
			context.getExternalContext().redirect("unitList.xhtml?faces-redirect=true");
			context.responseComplete();
			return;
		}

	}

	public String saveData() {
		// no Person object passed
		if (loaded == null) {
			return PAGE_STAY_AT_THE_SAME;
		}

		try {
			  // Delegowanie zapisu logiki biznesowej do UnitService
            unitService.saveUnit(unit, selectedTypeId, selectedStatusId);
			
		} catch (Exception e) {
			e.printStackTrace();
			context.addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "wystąpił błąd podczas zapisu", null));
			return PAGE_STAY_AT_THE_SAME;
		}

		return PAGE_UNIT_LIST;
	}
}
