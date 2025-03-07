package com.jsf.service;

import jakarta.annotation.PostConstruct;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;

import com.jsf.dao.DictionaryTypeDAO;
import com.jsf.dao.DictionaryValueDAO;
import com.jsf.dto.DictionaryValueDTO;
import com.jsf.entities.DictionaryType;
import com.jsf.entities.DictionaryValue;


@Stateless
public class DictionaryService {

    @Inject
    private DictionaryTypeDAO dictionaryTypeDAO;

    @Inject
    private DictionaryValueDAO dictionaryValueDAO;

    private List<DictionaryType> dictionaryTypes;
    private List<DictionaryValue> dictionaryValues;

    @PostConstruct
    public void init() {
        loadDictionaryTypes();
        loadDictionaryValues();
    }

    public List<DictionaryType> getDictionaryTypes() {
        return dictionaryTypes;
    }

    public List<DictionaryValue> getDictionaryValues() {
        return dictionaryValues;
    }

    public void loadDictionaryTypes() {
         dictionaryTypes = dictionaryTypeDAO.getFullList();
    }

    public void loadDictionaryValues() {
        dictionaryValues = dictionaryValueDAO.getFullList();
    }

    public void saveType(DictionaryType type) {
		/*
		 * if (type.getId() == null) { dictionaryTypeDAO.create(type); } else {
		 * dictionaryTypeDAO.update(type); }
		 */        
    	loadDictionaryTypes();
    }

    public void saveValue(DictionaryValue value) {
		/*
		 * if (value.getId() == null) { dictionaryValueDAO.create(value); } else {
		 * dictionaryValueDAO.update(value); }
		 */        
    	loadDictionaryValues();
    }

    public void addType() {
        DictionaryType newType = new DictionaryType();
        dictionaryTypes.add(newType);
    }

    public void addValue() {
        DictionaryValue newValue = new DictionaryValue();
        newValue.setEditable(true);
        dictionaryValues.add(newValue);
    }
 
    
    
    public List<DictionaryValueDTO> getValuesForDictionary (String dictionaryTypeName) {
    	
        List<DictionaryValue> values = dictionaryValueDAO.getValuesForDictionary(dictionaryTypeName);
        return values.stream()
                .map(value -> new DictionaryValueDTO(value.getIdDictionaryValue(), value.getValue()))
                .collect(Collectors.toList());
    }
    
}
