package com.jsf.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jakarta.ejb.Stateless;
import jakarta.inject.Inject;

import com.jsf.dao.DictionaryValueDAO;
import com.jsf.dao.UnitDAO;
import com.jsf.entities.Unit;
import com.jsf.entities.DictionaryValue;

@Stateless
public class UnitService {

	@Inject
	private UnitDAO unitDAO;

	@Inject
	private DictionaryValueDAO dictionaryValueDAO;

	// Pobieranie wszystkich jednostek
	public List<Unit> getAllUnits() {
		return unitDAO.getFullList();
	}

	// Pobieranie jednostek na podstawie adresu
	public List<Unit> getUnitsByAddress(String address) {
		if (address != null && !address.isEmpty()) {
			Map<String, Object> searchParams = new HashMap<>();
			searchParams.put("address", address);
			return unitDAO.getList(searchParams);
		}
		return getAllUnits(); // Jeśli brak parametrów wyszukiwania, zwróć pełną listę
	}

	public List<DictionaryValue> getDictionaryValuesByType(int typeId) {
		return dictionaryValueDAO.getByType(typeId);
	}

	public Unit createNewUnit() {
		return new Unit();
	}

	// Usuwanie jednostki
	public void deleteUnit(Unit unit) {
		unitDAO.remove(unit);
	}

	public void saveUnit(Unit unit, Integer selectedTypeId, Integer selectedStatusId) throws Exception {
		DictionaryValue typeDV = dictionaryValueDAO.find(selectedTypeId);
		DictionaryValue statusDV = dictionaryValueDAO.find(selectedStatusId);

		unit.setUnitType(typeDV);
		unit.setUnitStatus(statusDV);

		if (unit.getIdUnit() == null) {
			unitDAO.create(unit);
		} else {
			unitDAO.merge(unit);
		}
	}
}
