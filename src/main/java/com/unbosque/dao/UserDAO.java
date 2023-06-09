package com.unbosque.dao;

import java.util.List;

import com.unbosque.entity.User;

public interface UserDAO {
	public User retrieveUser(String username, String password);

	public User retrieveUser(String username);

	public void update(User user);
	// public String checkLogin(String username, String password);

	public void save(User user);

	public List<User> getUsers();
}
