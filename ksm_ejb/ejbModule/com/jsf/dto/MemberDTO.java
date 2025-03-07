package com.jsf.dto;

public class MemberDTO {
    private Integer id;        // ID członka
    private String firstName;  // Imię członka
    private String lastName;   // Nazwisko członka
	private String email;

	private Integer idMemberStatus;
	private String memberStatusName;
	
	private Integer idMemberType;
	private String memberTypeName;
	
	private Integer idRole;
	private String roleName;

	public MemberDTO(Integer id, String firstName, String lastName, String email, Integer idMemberStatus,
			String memberStatus, Integer idMemberType, String memberType, Integer idRole, String role) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.idMemberStatus = idMemberStatus;
		this.memberStatusName = memberStatus;
		this.idMemberType = idMemberType;
		this.memberTypeName = memberType;
		this.idRole = idRole;
		this.roleName = role;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public Integer getIdMemberStatus() {
		return idMemberStatus;
	}

	public void setIdMemberStatus(Integer idMemberStatus) {
		this.idMemberStatus = idMemberStatus;
	}

	public String getMemberStatusName() {
		return memberStatusName;
	}

	public void setMemberStatusName(String memberStatusName) {
		this.memberStatusName = memberStatusName;
	}

	public Integer getIdMemberType() {
		return idMemberType;
	}

	public void setIdMemberType(Integer idMemberType) {
		this.idMemberType = idMemberType;
	}

	public String getMemberTypeName() {
		return memberTypeName;
	}

	public void setMemberTypeName(String memberTypeName) {
		this.memberTypeName = memberTypeName;
	}

	public Integer getIdRole() {
		return idRole;
	}

	public void setIdRole(Integer idRole) {
		this.idRole = idRole;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	
}
