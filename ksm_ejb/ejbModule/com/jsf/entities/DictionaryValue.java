package com.jsf.entities;

import java.io.Serializable;
import jakarta.persistence.*;
import java.util.List;


/**
 * The persistent class for the dictionary_value database table.
 * 
 */
@Entity
@Table(name="dictionary_value")
@NamedQuery(name="DictionaryValue.findAll", query="SELECT d FROM DictionaryValue d")
public class DictionaryValue implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_dictionary_value")
	private Integer idDictionaryValue;

	private byte editable;

	private String value;

	//bi-directional many-to-one association to AccessKey
	@OneToMany(mappedBy="keyType")
	private List<AccessKey> accessKeysByKeyType;

	//bi-directional many-to-one association to AccessKey
	@OneToMany(mappedBy="keyStatus")
	private List<AccessKey> accessKeysByKeyStatus;

	//bi-directional many-to-one association to DictionaryType
	@ManyToOne
	@JoinColumn(name="id_dictionary_type")
	private DictionaryType dictionaryType;

	//bi-directional many-to-one association to Member
	@OneToMany(mappedBy="memberType")
	private List<Member> membersByType;

	//bi-directional many-to-one association to Member
	@OneToMany(mappedBy="memberStatus")
	private List<Member> membersByStatus;

	//bi-directional many-to-one association to Transaction
	@OneToMany(mappedBy="transactionType")
	private List<Transaction> transactions;

	//bi-directional many-to-one association to Unit
	@OneToMany(mappedBy="unitType")
	private List<Unit> unitsByType;

	//bi-directional many-to-one association to Unit
	@OneToMany(mappedBy="unitStatus")
	private List<Unit> unitsByStatus;

	public DictionaryValue() {
	}

	public Integer getIdDictionaryValue() {
		return this.idDictionaryValue;
	}

	public void setIdDictionaryValue(Integer idDictionaryValue) {
		this.idDictionaryValue = idDictionaryValue;
	}

    public Boolean getEditable() {
        return editable == 1; // Konwersja byte -> Boolean
    }

    public void setEditable(Boolean editable) {
        this.editable = (editable != null && editable) ? (byte) 1 : (byte) 0; // Konwersja Boolean -> byte
    }
    
	public String getValue() {
		return this.value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public List<AccessKey> getAccessKeysByKeyType() {
		return this.accessKeysByKeyType;
	}

	public void setAccessKeysByKeyType(List<AccessKey> accessKeysByKeyType) {
		this.accessKeysByKeyType = accessKeysByKeyType;
	}

	public AccessKey addAccessKeysByKeyType(AccessKey accessKeysByKeyType) {
		getAccessKeysByKeyType().add(accessKeysByKeyType);
		accessKeysByKeyType.setKeyType(this);

		return accessKeysByKeyType;
	}

	public AccessKey removeAccessKeysByKeyType(AccessKey accessKeysByKeyType) {
		getAccessKeysByKeyType().remove(accessKeysByKeyType);
		accessKeysByKeyType.setKeyType(null);

		return accessKeysByKeyType;
	}

	public List<AccessKey> getAccessKeysByKeyStatus() {
		return this.accessKeysByKeyStatus;
	}

	public void setAccessKeysByKeyStatus(List<AccessKey> accessKeysByKeyStatus) {
		this.accessKeysByKeyStatus = accessKeysByKeyStatus;
	}

	public AccessKey addAccessKeysByKeyStatus(AccessKey accessKeysByKeyStatus) {
		getAccessKeysByKeyStatus().add(accessKeysByKeyStatus);
		accessKeysByKeyStatus.setKeyStatus(this);

		return accessKeysByKeyStatus;
	}

	public AccessKey removeAccessKeysByKeyStatus(AccessKey accessKeysByKeyStatus) {
		getAccessKeysByKeyStatus().remove(accessKeysByKeyStatus);
		accessKeysByKeyStatus.setKeyStatus(null);

		return accessKeysByKeyStatus;
	}

	public DictionaryType getDictionaryType() {
		return this.dictionaryType;
	}

	public void setDictionaryType(DictionaryType dictionaryType) {
		this.dictionaryType = dictionaryType;
	}

	public List<Member> getMembersByType() {
		return this.membersByType;
	}

	public void setMembersByType(List<Member> membersByType) {
		this.membersByType = membersByType;
	}

	public Member addMembersByType(Member membersByType) {
		getMembersByType().add(membersByType);
		membersByType.setMemberStatus(this);

		return membersByType;
	}

	public Member removeMembersByType(Member membersByType) {
		getMembersByType().remove(membersByType);
		membersByType.setMemberStatus(null);

		return membersByType;
	}

	public List<Member> getMembersByStatus() {
		return this.membersByStatus;
	}

	public void setMembersByStatus(List<Member> membersByStatus) {
		this.membersByStatus = membersByStatus;
	}

	public Member addMembersByStauts(Member membersByStatus) {
		getMembersByStatus().add(membersByStatus);
		membersByStatus.setMemberType(this);

		return membersByStatus;
	}

	public Member removeMembersByStatus(Member membersByStatus) {
		getMembersByStatus().remove(membersByStatus);
		membersByStatus.setMemberType(null);

		return membersByStatus;
	}

	public List<Transaction> getTransactions() {
		return this.transactions;
	}

	public void setTransactions(List<Transaction> transactions) {
		this.transactions = transactions;
	}

	public Transaction addTransactions(Transaction transaction) {
		getTransactions().add(transaction);
		transaction.setTransactionType(this);

		return transaction;
	}

	public Transaction removeTransactions(Transaction transaction) {
		getTransactions().remove(transaction);
		transaction.setTransactionType(null);

		return transaction;
	}

	public List<Unit> getUnitsByType() {
		return this.unitsByType;
	}

	public void setUnitsByType(List<Unit> unitsByType) {
		this.unitsByType = unitsByType;
	}

	public Unit addUnitsByType(Unit unitsByType) {
		getUnitsByType().add(unitsByType);
		unitsByType.setUnitType(this);

		return unitsByType;
	}

	public Unit removeUnitsByType(Unit unitsByType) {
		getUnitsByType().remove(unitsByType);
		unitsByType.setUnitType(null);

		return unitsByType;
	}

	public List<Unit> getUnitsByStatus() {
		return this.unitsByStatus;
	}

	public void setUnitsByStatus(List<Unit> unitsByStatus) {
		this.unitsByStatus = unitsByStatus;
	}

	public Unit addUnitsByStatus(Unit unitsByStatus) {
		getUnitsByStatus().add(unitsByStatus);
		unitsByStatus.setUnitStatus(this);

		return unitsByStatus;
	}

	public Unit removeUnitsByStatus(Unit unitsByStatus) {
		getUnitsByStatus().remove(unitsByStatus);
		unitsByStatus.setUnitStatus(null);

		return unitsByStatus;
	}


}