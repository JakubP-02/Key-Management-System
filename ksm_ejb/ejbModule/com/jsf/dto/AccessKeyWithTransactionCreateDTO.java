package com.jsf.dto;

public class AccessKeyWithTransactionCreateDTO {

	// Część dotycząca AccessKey
    private Integer keyType;        // Typ klucza
    private String description;     // Opis klucza
	
    private Integer quantity;       // Liczba kluczy - ile instancji klucza zostanie dodanych
    
	// Część dotycząca Transaction
	private Integer idUnit;         // Do którego unitu należą tworzone klucze 
    private Integer idMember;       // ID osoby rejestrującej operację
    private Integer idSource;       // ID osoby/lokalizacji przekazującej klucze
    private Integer idRecipient;    // ID osoby/lokalizacji będącej odbiorcą kluczy
    private String details;         // Opis operacji
    
    
	public Integer getKeyType() {
		return keyType;
	}
	public void setKeyType(Integer keyType) {
		this.keyType = keyType;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	public Integer getIdUnit() {
		return idUnit;
	}
	public void setIdUnit(Integer idUnit) {
		this.idUnit = idUnit;
	}
	public Integer getIdMember() {
		return idMember;
	}
	public void setIdMember(Integer idMember) {
		this.idMember = idMember;
	}
	public Integer getIdSource() {
		return idSource;
	}
	public void setIdSource(Integer idSource) {
		this.idSource = idSource;
	}
	public Integer getIdRecipient() {
		return idRecipient;
	}
	public void setIdRecipient(Integer idRecipient) {
		this.idRecipient = idRecipient;
	}
	public String getDetails() {
		return details;
	}
	public void setDetails(String details) {
		this.details = details;
	}
    
     

}
