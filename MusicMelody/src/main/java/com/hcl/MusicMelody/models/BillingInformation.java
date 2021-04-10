package com.hcl.MusicMelody.models;

import java.util.Date;

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

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "billing_information")
public class BillingInformation {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "billing_id")
	private Integer id;

	@Column(name = "street")
	@NotEmpty(message = "Please provide a Street Name")
	private String street;

	@Column(name = "apartment")
	private String apt;

	@Column(name = "city")
	@NotEmpty(message = "An address should contain a city.")
	private String city;

	@Column(name = "state")
	@NotEmpty(message = "An address should contain a state.")
	private String state;

	@Column(name = "zip")
	@NotEmpty(message = "An address should contain a zip code.")
	private String zip;

	@Column(name = "cvv")
	@NotEmpty(message = "Please provide cvv")
	private String cvv;

	@Column(name = "exp_date")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date expDate;

	@Column(name = "card_number")
	@NotEmpty(message = "Please provide a card number")
	private String cardNumber;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getApt() {
		return apt;
	}

	public void setApt(String apt) {
		this.apt = apt;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	public String getCvv() {
		return cvv;
	}

	public void setCvv(String cvv) {
		this.cvv = cvv;
	}

	public Date getExpDate() {
		return expDate;
	}

	public void setExpDate(Date expDate) {
		this.expDate = expDate;
	}

	public String getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

}
