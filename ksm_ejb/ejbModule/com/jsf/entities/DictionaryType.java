package com.jsf.entities;

import java.io.Serializable;
import jakarta.persistence.*;
import java.util.List;


/**
 * The persistent class for the dictionary_type database table.
 * 
 */
@Entity
@Table(name="dictionary_type")
@NamedQuery(name="DictionaryType.findAll", query="SELECT d FROM DictionaryType d")
public class DictionaryType implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_dictionary_type")
	private Integer idDictionaryType;

	private String description;

	private String name;

	//bi-directional many-to-one association to DictionaryValue
	@OneToMany(mappedBy="dictionaryType")
	private List<DictionaryValue> dictionaryValues;

	public DictionaryType() {
	}

	public Integer getIdDictionaryType() {
		return this.idDictionaryType;
	}

	public void setIdDictionaryType(Integer idDictionaryType) {
		this.idDictionaryType = idDictionaryType;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	// tutaj powinna być raczej lista danego słownika
	// List<DictionaryValue> getDictionaryValues(idDictionaryType) {
	
	public List<DictionaryValue> getDictionaryValues() {
		return this.dictionaryValues;
	}

	public void setDictionaryValues(List<DictionaryValue> dictionaryValues) {
		this.dictionaryValues = dictionaryValues;
	}

	public DictionaryValue addDictionaryValue(DictionaryValue dictionaryValue) {
		getDictionaryValues().add(dictionaryValue);
		dictionaryValue.setDictionaryType(this);

		return dictionaryValue;
	}

	public DictionaryValue removeDictionaryValue(DictionaryValue dictionaryValue) {
		getDictionaryValues().remove(dictionaryValue);
		dictionaryValue.setDictionaryType(null);

		return dictionaryValue;
	}

}