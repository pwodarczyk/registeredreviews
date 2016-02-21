package com.rr.springmvc.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;


@Entity
@Table(name="site_user")
public class User {
	
	public User(String email,String password){
		setEmail(email);
		setPassword(password);
	}
	
	@Id
	private Long id;
	
	@Size(min=3, max=255)
	@Column(name = "email",nullable=false)
	private String email;
	
	@Size(min=0, max=100)
	@Column(name = "password", nullable = false)
	private String password;
	
	@Size(min=0, max=20)
	@Column(name = "first_name")
	private String firstName;
	
	@Size(min=0, max=40)
	@Column(name = "last_name")
	private String lastName;
	
	@Column(name = "verified",nullable=false)
	private boolean verified = false;

	@Column(name = "enabled",nullable=false)
	private boolean enabled = true;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isVerified() {
		return verified;
	}
	public void setVerified(boolean verified) {
		this.verified = verified;
	}
	public boolean isEnabled() {
		return enabled;
	}
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
}
