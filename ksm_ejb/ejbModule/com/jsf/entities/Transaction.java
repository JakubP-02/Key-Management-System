package com.jsf.entities;

import java.io.Serializable;
import jakarta.persistence.*;
import java.util.Date;


/**
 * The persistent class for the transaction database table.
 * 
 */
@Entity
@NamedQuery(name="Transaction.findAll", query="SELECT t FROM Transaction t")
public class Transaction implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_transaction")
	private Integer idTransaction;

	@Lob
	private String details;

	@ManyToOne
	@JoinColumn(name="id_source")
	private Member memberSource;
	
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="transaction_date")
	private Date transactionDate;

	//bi-directional many-to-one association to AccessKey
	@ManyToOne
	@JoinColumn(name="id_access_key")
	private AccessKey accessKey;

	//bi-directional many-to-one association to Member
	@ManyToOne
	@JoinColumn(name="id_member")
	private Member member;

	//bi-directional many-to-one association to Member
	@ManyToOne
	@JoinColumn(name="id_recipient")
	private Member memberRecipient;

	//bi-directional many-to-one association to DictionaryValue
	@ManyToOne
	@JoinColumn(name="id_transaction_type")
	private DictionaryValue transactionType;

	public Transaction() {
	}

	public Integer getIdTransaction() {
		return this.idTransaction;
	}

	public void setIdTransaction(Integer idTransaction) {
		this.idTransaction = idTransaction;
	}

	public String getDetails() {
		return this.details;
	}

	public void setDetails(String details) {
		this.details = details;
	}


	public Member getMemberSource() {
		return memberSource;
	}

	public void setMemberSource(Member memberSource) {
		this.memberSource = memberSource;
	}

	public Date getTransactionDate() {
		return this.transactionDate;
	}

	public void setTransactionDate(Date transactionDate) {
		this.transactionDate = transactionDate;
	}

	public AccessKey getAccessKey() {
		return this.accessKey;
	}

	public void setAccessKey(AccessKey accessKey) {
		this.accessKey = accessKey;
	}

	public Member getMemberRecipient() {
		return this.memberRecipient;
	}

	public void setMemberRecipient(Member memberRecipient) {
		this.memberRecipient = memberRecipient;
	}

	public Member getMember() {
		return this.member;
	}

	public void setMember(Member member) {
		this.member = member;
	}

	public DictionaryValue getTransactionType() {
		return transactionType;
	}

	public void setTransactionType(DictionaryValue transactionType) {
		this.transactionType = transactionType;
	}


}