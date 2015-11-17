package com.registeredreviews.service;

import java.util.List;

import com.registeredreviews.model.Business;

public class BrandManager {

	private List<Business> brandList;

	public List<Business> getBrandList() {
		return brandList;
	}

	public void setBrandList(List<Business> brandList) {
		this.brandList = brandList;
	}

	public Business getBrandById(Long id) {
		for (Business brand : brandList) {
			if (brand.getId().equals(id))
				return brand;
		}
		return null;
	}
}
