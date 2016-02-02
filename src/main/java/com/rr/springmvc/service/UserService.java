package com.rr.springmvc.service;

import com.rr.springmvc.model.User;

public interface UserService {
	public void save(User user);

	public User findById(int id);

	public User findByEmail(String email);
}
