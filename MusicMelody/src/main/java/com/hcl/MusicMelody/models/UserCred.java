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
import lombok.Data;


@Entity
@Table(name = "users")
@Data
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
	
}
