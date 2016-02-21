package com.rr.springmvc.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;

@Entity
@Table(name="quote_request")
public class QuoteRequest {
	
	@Id
	private int id;
	
	@Size(min=3, max=255)
	@Column(name = "email",nullable=false)
	private String email;

	@Size(min=0, max=100)
	@Column(name = "name")
	private String name;

	@Size(min=0, max=2000)
	@Column(name = "message")
	private String message;

	@Size(min=0, max=100)
	@Column(name = "business_id")
	private String businessId;

	@Size(min=0, max=100)
	@Column(name = "service_requested")
	private String serviceRequested;

	@Column(name = "created_date")
	private Date dateRequested;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getBusinessId() {
		return businessId;
	}

	public void setBusinessId(String businessId) {
		this.businessId = businessId;
	}

	public String getServiceRequested() {
		return serviceRequested;
	}

	public void setServiceRequested(String serviceRequested) {
		this.serviceRequested = serviceRequested;
	}

	public Date getDateRequested() {
		return dateRequested;
	}

	public void setDateRequested(Date dateRequested) {
		this.dateRequested = dateRequested;
	}
	
	
}
