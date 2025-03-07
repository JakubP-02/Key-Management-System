package com.jsf.dto;

import java.util.Date;

public class AccessKeyWithTransactionDTO {
    private Integer idAccessKey; 
    private Integer idKeyType;
    private String 	keyType;
    private Integer idKeyStatus;
    private String 	keyStatus;
    private String  description;
    private Integer idTransaction;
    private Date 	transactionDate;
    private String  details;
    private Integer idRecipient;
    private String  firstName;
    private String  lastName;
    private String  email;

    public AccessKeyWithTransactionDTO(Integer idAccessKey, Integer idKeyType, String keyType, Integer idKeyStatus,
			String keyStatus, String description, Integer idTransaction, Date transactionDate, String details,
			Integer idRecipient, String firstName, String lastName, String email) {
		super();
		this.idAccessKey = idAccessKey;
		this.idKeyType = idKeyType;
		this.keyType = keyType;
		this.idKeyStatus = idKeyStatus;
		this.keyStatus = keyStatus;
		this.description = description;
		this.idTransaction = idTransaction;
		this.transactionDate = transactionDate;
		this.details = details;
		this.idRecipient = idRecipient;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
	}

	public Integer getIdAccessKey() {
		return idAccessKey;
	}

	public void setIdAccessKey(Integer idAccessKey) {
		this.idAccessKey = idAccessKey;
	}

	public Integer getIdKeyType() {
		return idKeyType;
	}

	public void setIdKeyType(Integer idKeyType) {
		this.idKeyType = idKeyType;
	}

	public String getKeyType() {
		return keyType;
	}

	public void setKeyType(String keyType) {
		this.keyType = keyType;
	}

	public Integer getIdKeyStatus() {
		return idKeyStatus;
	}

	public void setIdKeyStatus(Integer idKeyStatus) {
		this.idKeyStatus = idKeyStatus;
	}

	public String getKeyStatus() {
		return keyStatus;
	}

	public void setKeyStatus(String keyStatus) {
		this.keyStatus = keyStatus;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getIdTransaction() {
		return idTransaction;
	}

	public void setIdTransaction(Integer idTransaction) {
		this.idTransaction = idTransaction;
	}

	public Date getTransactionDate() {
		return transactionDate;
	}

	public void setTransactionDate(Date transactionDate) {
		this.transactionDate = transactionDate;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	public Integer getIdRecipient() {
		return idRecipient;
	}

	public void setIdRecipient(Integer idRecipient) {
		this.idRecipient = idRecipient;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

    

}
