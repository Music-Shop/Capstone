package com.hcl.MusicMelody.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;
import org.springframework.boot.context.properties.bind.DefaultValue;
import org.springframework.format.annotation.DateTimeFormat;

import net.bytebuddy.implementation.bind.annotation.Default;

import java.util.Date;

@Entity
public class Task {
	
	/**
	 * TODO: revamp to fit format for a product
	 *  - Title of Song (String)
	 *  - Artist (String)
	 *  - Genre (enum) - allow for multiple selections? 
	 *  - Release (Set date when added or self entered date)
	 *  - Price (USD)
	 * 
	 * Below is the rough draft of the variables
	 */

	// @Id
	// @GeneratedValue(strategy = GenerationType.IDENTITY)
	// private Integer id;

	// @Column(name = "title")
	// @Length(message = "Song must have a title")
	// @NotEmpty(message = "Please provide a song title")
	// private String title;

	// @Column(name = "artist")
	// @Length(message = "Song must have an artist")
	// @NotEmpty(message = "Please provide a artist for the song")
	// private String artist;

	// @Column(name = "genre")
	// @Length(message = "Song must should have a genre")
	// @NotEmpty(message = "Please select a genre for the song")
	// private String genre;

	// @Column(name = "release")
	// @Length(message = "Song must have a release date")
	// @NotEmpty(message = "Please enter the release date")
	// private Date release;

	// @Column(name = "price")
	// @Length(message = "Song must have a asking price")
	// @NotEmpty(message = "Please enter a price")
	// private Double price;


	@Id
	   @GeneratedValue(strategy=GenerationType.IDENTITY)
	   private Integer id;
	   private String name;
	   
	   @DateTimeFormat(pattern = "yyyy-MM-dd")
	   private Date startDate;
	   private Date endDate;
	   private String severity;
	   private String description;
	   
	   @ManyToOne
	   private UserCred user;
	   
	   public Task() {
		   
	   }
	   
	public Task(String name, Date startDate, Date endDate, String severity, String description,
			UserCred user) {
		super();
		this.name = name;
		this.startDate = startDate;
		this.endDate = endDate;
		this.severity = severity;
		this.description = description;
		this.user = user;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getSeverity() {
		return severity;
	}

	public void setSeverity(String severity) {
		this.severity = severity;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public UserCred getUser() {
		return user;
	}

	public void setUser(UserCred user) {
		this.user = user;
	}

	

}