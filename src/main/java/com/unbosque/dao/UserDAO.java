package com.unbosque.dao;

import com.unbosque.entity.User;

public interface UserDAO {
	public User retrieveUser(String username, String password);

	// public String checkLogin(String username, String password);
}
