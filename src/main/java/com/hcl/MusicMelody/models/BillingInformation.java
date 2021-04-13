package com.hcl.MusicMelody.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "billing_information")
@Data
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

}
