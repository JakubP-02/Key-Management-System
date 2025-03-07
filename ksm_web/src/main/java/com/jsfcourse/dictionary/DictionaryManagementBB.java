package com.jsfcourse.dictionary;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import jakarta.annotation.ManagedBean;
import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import com.jsf.entities.DictionaryType;
import com.jsf.entities.DictionaryValue;
import com.jsf.service.DictionaryService;


@Named( "dictionaryBean")
@ViewScoped
public class DictionaryManagementBB implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6436623036540903308L;

	@Inject
    private DictionaryService dictionaryService;

    private List<DictionaryType> dictionaryTypes;
    private List<DictionaryValue> dictionaryValues;

    @PostConstruct
    public void init() {
        dictionaryTypes = dictionaryService.getDictionaryTypes();
        dictionaryValues = dictionaryService.getDictionaryValues();
    }

    public List<DictionaryType> getDictionaryTypes() {
        //return dictionaryTypes;
    	return dictionaryService.getDictionaryTypes();
    }

    public List<DictionaryValue> getDictionaryValues() {
        return dictionaryValues;
    }

    public void saveType(DictionaryType type) {
        dictionaryService.saveType(type);
    }

    public void saveValue(DictionaryValue value) {
        dictionaryService.saveValue(value);
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
    
    
}
