package com.jsf.entities;

import java.io.Serializable;
import jakarta.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the unit database table.
 * 
 */
@Entity
@NamedQuery(name="Unit.findAll", query="SELECT u FROM Unit u")
public class Unit implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_unit")
	private Integer idUnit;

	private String address;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="closed_at")
	private Date closedAt;

	//bi-directional many-to-one association to AccessKey
	@OneToMany(mappedBy="unit")
	private List<AccessKey> accessKeys;

	//bi-directional many-to-one association to DictionaryValue
	@ManyToOne
	@JoinColumn(name="id_unit_status")
	private DictionaryValue unitStatus;

	//bi-directional many-to-one association to DictionaryValue
	@ManyToOne
	@JoinColumn(name="id_unit_type")
	private DictionaryValue unitType;

	public Unit() {
	}

	public Integer getIdUnit() {
		return this.idUnit;
	}

	public void setIdUnit(Integer idUnit) {
		this.idUnit = idUnit;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Date getClosedAt() {
		return this.closedAt;
	}

	public void setClosedAt(Date closedAt) {
		this.closedAt = closedAt;
	}

	public List<AccessKey> getAccessKeys() {
		return this.accessKeys;
	}

	public void setAccessKeys(List<AccessKey> accessKeys) {
		this.accessKeys = accessKeys;
	}

	public AccessKey addAccessKey(AccessKey accessKey) {
		getAccessKeys().add(accessKey);
		accessKey.setUnit(this);

		return accessKey;
	}

	public AccessKey removeAccessKey(AccessKey accessKey) {
		getAccessKeys().remove(accessKey);
		accessKey.setUnit(null);

		return accessKey;
	}

	public DictionaryValue getUnitStatus() {
		return this.unitStatus;
	}

	public void setUnitStatus(DictionaryValue unitStatus) {
		this.unitStatus = unitStatus;
	}

	public DictionaryValue getUnitType() {
		return this.unitType;
	}

	public void setUnitType(DictionaryValue unitType) {
		this.unitType = unitType;
	}

}