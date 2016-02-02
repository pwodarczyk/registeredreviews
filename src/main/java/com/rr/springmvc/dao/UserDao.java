package com.rr.springmvc.dao;

import com.rr.springmvc.model.User;

public interface UserDao {

	User findById(int id);

	void save(User user);
	
	User findByEmail(String email);
	
	

}
