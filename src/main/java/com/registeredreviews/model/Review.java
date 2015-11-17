package com.registeredreviews.model;

import java.math.BigDecimal;

public class Review {
	private Long id;
	private Business brand;
	private String model;
	private BigDecimal price;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Business getBrand() {
		return brand;
	}

	public void setBrand(Business brand) {
		this.brand = brand;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}
}
