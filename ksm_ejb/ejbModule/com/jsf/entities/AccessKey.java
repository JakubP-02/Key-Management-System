package com.jsf.entities;

import java.io.Serializable;
import jakarta.persistence.*;
import java.util.List;


/**
 * The persistent class for the access_key database table.
 * 
 */
@Entity
@Table(name="access_key")
@NamedQuery(name="AccessKey.findAll", query="SELECT a FROM AccessKey a")
public class AccessKey implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_access_key")
	private Integer idAccessKey;

	@Column(name="description")
	private String description;

	//bi-directional many-to-one association to DictionaryValue
	@ManyToOne
	@JoinColumn(name="id_key_type")
	private DictionaryValue keyType;

	//bi-directional many-to-one association to DictionaryValue
	@ManyToOne
	@JoinColumn(name="id_key_status")
	private DictionaryValue keyStatus;

	//bi-directional many-to-one association to Unit
	@ManyToOne
	@JoinColumn(name="id_unit")
	private Unit unit;

	//bi-directional many-to-one association to Transaction
	@OneToMany(mappedBy="accessKey")
	private List<Transaction> transactions;

	public AccessKey() {
	}

	public Integer getIdAccessKey() {
		return this.idAccessKey;
	}

	public void setIdAccessKey(Integer idAccessKey) {
		this.idAccessKey = idAccessKey;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public DictionaryValue getKeyType() {
		return this.keyType;
	}

	public void setKeyType(DictionaryValue keyType) {
		this.keyType = keyType;
	}

	public DictionaryValue getKeyStatus() {
		return this.keyStatus;
	}

	public void setKeyStatus(DictionaryValue keyStatus) {
		this.keyStatus = keyStatus;
	}

	public Unit getUnit() {
		return this.unit;
	}

	public void setUnit(Unit unit) {
		this.unit = unit;
	}

	public List<Transaction> getTransactions() {
		return this.transactions;
	}

	public void setTransactions(List<Transaction> transactions) {
		this.transactions = transactions;
	}

	public Transaction addTransaction(Transaction transaction) {
		getTransactions().add(transaction);
		transaction.setAccessKey(this);

		return transaction;
	}

	public Transaction removeTransaction(Transaction transaction) {
		getTransactions().remove(transaction);
		transaction.setAccessKey(null);

		return transaction;
	}

}