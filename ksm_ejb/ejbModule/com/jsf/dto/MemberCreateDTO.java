package com.jsf.dto;

import java.util.List;

import com.jsf.entities.DictionaryValue;
import com.jsf.entities.Role;
import com.jsf.entities.Transaction;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

public class MemberCreateDTO {
    private String firstName;  // Imię członka
    private String lastName;   // Nazwisko członka
	private String email;

	private String password;
	
	private Integer idMemberStatus;
	private Integer idMemberType;
	private Integer idRole;
	

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
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Integer getIdMemberStatus() {
		return idMemberStatus;
	}
	public void setIdMemberStatus(Integer idMemberStatus) {
		this.idMemberStatus = idMemberStatus;
	}
	public Integer getIdMemberType() {
		return idMemberType;
	}
	public void setIdMemberType(Integer idMemberType) {
		this.idMemberType = idMemberType;
	}
	public Integer getIdRole() {
		return idRole;
	}
	public void setIdRole(Integer idRole) {
		this.idRole = idRole;
	}
	
	

}
