package com.hcl.MusicMelody.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Entity
public class Task {
	
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