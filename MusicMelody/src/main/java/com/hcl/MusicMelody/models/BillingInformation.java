package com.hcl.MusicMelody.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "billing_information")
public class BillingInformation {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "billing_id")
	private Integer id;
	
	@Column(name="fname")
	@NotEmpty(message = "Please provide a First Name")
	private String fname;
	
	@Column(name="lname")
	@NotEmpty(message = "Please provide a Last Name")
	private String lname;
	
	@Column(name="billing_address")
	@NotEmpty(message = "Please provide a First Name")
	private String billingAddress;
	
	@Column(name="cvv")
	@NotEmpty(message = "Please provide a First Name")
	private String cvv;
	
	@Column(name="exp_date")
	@NotEmpty(message = "Please provide a First Name")
	private String expDate;
	
	@Column(name="card_number")
	@NotEmpty(message = "Please provide a First Name")
	private String cardNumber;

	@OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private UserCred userCred;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getFname() {
		return fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

	public String getLname() {
		return lname;
	}

	public void setLname(String lname) {
		this.lname = lname;
	}

	public String getBillingAddress() {
		return billingAddress;
	}

	public void setBillingAddress(String billingAddress) {
		this.billingAddress = billingAddress;
	}

	public String getCvv() {
		return cvv;
	}

	public void setCvv(String cvv) {
		this.cvv = cvv;
	}

	public String getExpDate() {
		return expDate;
	}

	public void setExpDate(String expDate) {
		this.expDate = expDate;
	}

	public String getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}
	
	
}
