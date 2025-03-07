package com.jsf.entities;

import java.io.Serializable;
import jakarta.persistence.*;
import java.util.List;


/**
 * The persistent class for the member database table.
 * 
 */
@Entity
@NamedQuery(name="Member.findAll", query="SELECT m FROM Member m")
public class Member implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_member")
	private Integer idMember;

	private String email;

	@Column(name="first_name")
	private String firstName;

	@Column(name="last_name")
	private String lastName;

	private String password;

	//bi-directional many-to-one association to DictionaryValue
	@ManyToOne
	@JoinColumn(name="id_member_status")
	private DictionaryValue memberStatus;

	//bi-directional many-to-one association to DictionaryValue
	@ManyToOne
	@JoinColumn(name="id_member_type")
	private DictionaryValue memberType;

	//bi-directional many-to-one association to Role
	@ManyToOne
	@JoinColumn(name="id_role")
	private Role role;

	//bi-directional many-to-one association to Transaction
	@OneToMany(mappedBy="member")
	private List<Transaction> transactions;

	//bi-directional many-to-one association to Transaction
	@OneToMany(mappedBy="memberRecipient")
	private List<Transaction> transactionsRecipient;

	//bi-directional many-to-one association to Transaction
	@OneToMany(mappedBy="memberSource")
	private List<Transaction> transactionsSource;

	
	public Member() {
	}

	public Integer getIdMember() {
		return this.idMember;
	}

	public void setIdMember(Integer idMember) {
		this.idMember = idMember;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public DictionaryValue getMemberStatus() {
		return this.memberStatus;
	}

	public void setMemberStatus(DictionaryValue memberStatus) {
		this.memberStatus = memberStatus;
	}

	public DictionaryValue getMemberType() {
		return this.memberType;
	}

	public void setMemberType(DictionaryValue memberType) {
		this.memberType = memberType;
	}

	public Role getRole() {
		return this.role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public List<Transaction> getTransactions() {
		return this.transactions;
	}

	public void setTransactions(List<Transaction> transactions) {
		this.transactions = transactions;
	}

	public Transaction addTransactions(Transaction transactions) {
		getTransactions().add(transactions);
		transactions.setMember(this);

		return transactions;
	}

	public Transaction removeTransactions(Transaction transactions) {
		getTransactions().remove(transactions);
		transactions.setMember(null);

		return transactions;
	}

	public List<Transaction> getTransactionsRecipient() {
		return this.transactionsRecipient;
	}

	public void setTransactionsRecipient(List<Transaction> transactionsRecipient) {
		this.transactionsRecipient = transactionsRecipient;
	}

	public Transaction addTransactionsRecipient(Transaction transactionsRecipient) {
		getTransactionsRecipient().add(transactionsRecipient);
		transactionsRecipient.setMemberRecipient(this);

		return transactionsRecipient;
	}

	public Transaction removeTransactionsRecipient(Transaction transactionsRecipient) {
		getTransactionsRecipient().remove(transactionsRecipient);
		transactionsRecipient.setMemberRecipient(null);

		return transactionsRecipient;
	}



	public List<Transaction> getTransactionsSource() {
		return this.transactionsSource;
	}

	public void setTransactionsSource(List<Transaction> transactionsSource) {
		this.transactionsSource = transactionsSource;
	}

	public Transaction addTransactionsSource(Transaction transactionsSource) {
		getTransactionsSource().add(transactionsSource);
		transactionsSource.setMemberSource(this);

		return transactionsSource;
	}

	public Transaction removeTransactionsSource(Transaction transactionsSource) {
		getTransactionsSource().remove(transactionsSource);
		transactionsSource.setMemberSource(null);

		return transactionsSource;
	}

}