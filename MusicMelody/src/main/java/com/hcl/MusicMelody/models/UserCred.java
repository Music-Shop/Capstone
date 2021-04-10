package com.hcl.MusicMelody.models;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;


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
	
	@OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "billing_id")
    private BillingInformation billing;
	
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
	public BillingInformation getBilling() {
		return billing;
	}
	public void setBilling(BillingInformation billing) {
		this.billing = billing;
	}
	@Override
	public String toString() {
		return "UserCred [id=" + id + ", userName=" + userName + ", email=" + email + ", password=" + password
				+ ", name=" + name + ", lastName=" + lastName + ", active=" + active + ", roles=" + roles + "]";
	}
	
	
	

}
