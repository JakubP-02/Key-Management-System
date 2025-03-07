package com.jsf.dto;

import java.util.Date;

public class UnitDTO {
	private Integer id;
	private String address;
	private Integer idUnitStatus;
	private Integer idUnitType;
	private Date closedAt;
	
    public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Integer getIdUnitStatus() {
		return idUnitStatus;
	}
	public void setIdUnitStatus(Integer idUnitStatus) {
		this.idUnitStatus = idUnitStatus;
	}
	public Integer getIdUnitType() {
		return idUnitType;
	}
	public void setIdUnitType(Integer idUnitType) {
		this.idUnitType = idUnitType;
	}
	public Date getClosedAt() {
		return closedAt;
	}
	public void setClosedAt(Date closedAt) {
		this.closedAt = closedAt;
	}
}
