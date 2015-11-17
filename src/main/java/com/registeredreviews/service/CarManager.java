package com.registeredreviews.service;

import java.util.List;

import com.registeredreviews.model.Review;

public class CarManager {

	private List<Review> carList;

	public List<Review> getCarList() {
		return carList;
	}

	public void setCarList(List<Review> carList) {
		this.carList = carList;
	}

	public Review createCar(Review c) {
		Review car = new Review();
		car.setId((long) carList.size() + 1);
		car.setBrand(c.getBrand());
		car.setModel(c.getModel());
		car.setPrice(c.getPrice());

		carList.add(car);

		return car;
	}
}
