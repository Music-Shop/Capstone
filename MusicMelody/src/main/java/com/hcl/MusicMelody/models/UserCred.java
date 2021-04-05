package com.hcl.MusicMelody.models;

import java.lang.Thread.State;
import java.util.Set;

//import org.hibernate.validator.constraints.Length;
import javax.persistence.*;
import javax.validation.constraints.Email;
//import javax.validation.constraints.Email;
//import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;
import org.springframework.boot.autoconfigure.security.ConditionalOnDefaultWebSecurity;


@Entity
@Table(name = "users")
public class UserCred {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "user_id")
	private Integer id;
	
	@Column(name = "user_name")
	@Length(min = 5, message = "Your user name must have at least 5 character")
	@NotEmpty(message = "Please provide a user name")
	private String userName;
	
	@Column(name="email")
	@Email(message = "Please provide an email")
	@NotEmpty(message = "Please provide a valid email")
	private String email;
	
	@Column(name = "password")
	@Length(min = 8, message = "Your password must have at least 8 characters")
	@NotEmpty(message = "Please provide your password")
	private String password;
	
	@Column(name = "name")
	@NotEmpty(message = "Please provide your name")
	private String name;
	
	@Column(name = "last_name")
	@NotEmpty(message = "Please provide your last name")
	private String lastName;
	
	@Column(name = "active")
	private boolean active;

	/**
	 * TODO: Add Billing address information?
	 *  - Address (String)
	 *  - Apartment (int)
	 *  - City (String)
	 *  - State (String - either initials (2 char len) or full state name) - enum?
	 *  - Zip Code (int of len 5)
	 * 
	 * I think that this should be added when a user registers (Ref: pdf of capstone in resources).
	 * 
	 * Below is the rough draft of the variables to add
	 */

	// @Column(name = "address")
	// @Length(min = 5)
	// @NotEmpty(message = "Please enter a address")
	// private String address;

	// @Column(name = "apartment")
	// private Integer apartment;

	// @Column(name = "city")
	// @NotEmpty(message = "Please enter a name of city")
	// private String city;

	// @Column(name = "state")
	// @NotEmpty(message = "Please select a state")
	// private String state;

	// @Column(name = "zipcode")
	// @NotEmpty(message = "Please enter a zipcode")
	// private Integer zipCode;

	
	@ManyToMany(cascade = CascadeType.MERGE)
	@JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
	private Set<Role> roles;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
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
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}
	public Set<Role> getRoles() {
		return roles;
	}
	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}
	@Override
	public String toString() {
		return "UserCred [id=" + id + ", userName=" + userName + ", email=" + email + ", password=" + password
				+ ", name=" + name + ", lastName=" + lastName + ", active=" + active + ", roles=" + roles + "]";
	}
	
	

}
