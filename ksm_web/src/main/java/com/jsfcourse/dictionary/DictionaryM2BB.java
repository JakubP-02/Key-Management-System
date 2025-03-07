package com.jsfcourse.dictionary;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;

import jakarta.annotation.ManagedBean;
import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import com.jsf.entities.DictionaryType;
import com.jsf.entities.DictionaryValue;
import com.jsf.service.DictionaryService;


@Named( "dictionaryM2Bean")
@ViewScoped
public class DictionaryM2BB implements Serializable {


	/**
	 * 
	 */
	private static final long serialVersionUID = -5502838683568478684L;

	@Inject
    private DictionaryService dictionaryService;
	
    private TreeNode root;
    private TreeNode selectedNode;

	
    private List<DictionaryType> dictionaryTypes;
    private List<DictionaryValue> dictionaryValues;
    private DictionaryType selectedType;

    
    @PostConstruct
    public void init() {
        // Symulowane dane
        dictionaryTypes =  dictionaryService.getDictionaryTypes();
        dictionaryValues = dictionaryService.getDictionaryValues();
    }

    // Metoda do filtrowania wartości dla wybranego typu
    public List<DictionaryValue> getFilteredValues() {
    	if (selectedType == null) {
            return new ArrayList<>();
        }
    	
        int selectedTypeId = selectedType.getIdDictionaryType();
        List<DictionaryValue> filtered = new ArrayList<>();
        for (DictionaryValue value : dictionaryValues) {
            if (value.getDictionaryType().getIdDictionaryType() == selectedTypeId) {
                filtered.add(value);
            }
        }
        return filtered;
    }

    // Gettery i settery
    public List<DictionaryType> getDictionaryTypes() {
        return dictionaryTypes;
    }

    public DictionaryType getSelectedType() {
        return selectedType;
    }

    public void setSelectedType(DictionaryType selectedType) {
        this.selectedType = selectedType;
    }
    
}
