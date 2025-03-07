package com.jsfcourse.unit;

import java.util.List;

import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.context.ExternalContext;
import jakarta.faces.context.Flash;

import com.jsf.entities.Unit;
import com.jsf.service.UnitService;

@Named
@RequestScoped
public class UnitListBB {
	private static final String PAGE_UNIT_EDIT = "unitEdit?faces-redirect=true";
	private static final String PAGE_STAY_AT_THE_SAME = null;

	private String address;

	@Inject
	private UnitService unitService;

	@Inject
	ExternalContext extcontext;

	@Inject
	Flash flash;

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public List<Unit> getFullList() {
		return unitService.getAllUnits();
	}

	public List<Unit> getList() {
		return unitService.getUnitsByAddress(address);
	}

	public String newUnit() {
		Unit unit = unitService.createNewUnit();

		// 2. Pass object through flash
		flash.put("unit", unit);

		return PAGE_UNIT_EDIT;
	}

	public String editUnit(Unit unit) {
		// 1. Pass object through session
		// HttpSession session = (HttpSession) extcontext.getSession(true);
		// session.setAttribute("person", person);

		// 2. Pass object through flash
		flash.put("unit", unit);

		return PAGE_UNIT_EDIT;
	}

	public String deleteUnit(Unit unit) {
		unitService.deleteUnit(unit);
		return PAGE_STAY_AT_THE_SAME;
	}
}
