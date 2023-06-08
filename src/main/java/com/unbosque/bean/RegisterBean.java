package com.unbosque.bean;

import java.util.Date;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import com.unbosque.dao.UserDAO;
import com.unbosque.dao.impl.UserDAOImpl;
import com.unbosque.entity.User;

@ManagedBean
@SessionScoped
public class RegisterBean {

	private String login;
	private String password;
	private String userType;
	private String name;
	private String surname;
	private String email;
	private Date registerDate;
	private Date lastPsswdDate;
	private int psswdAttempts;
	private boolean userStatus;

	// For frontend
	private String[] userTypes = { "Empleado" };

	public RegisterBean() {

	}

	public String registerNewUser() {
		UserDAO userDAO = new UserDAOImpl();
		// Check if user already exists
		if (userDAO.retrieveUser(login) != null) {
			// User is not available
			return "nuevoRegistro";
		} else {
			// User is available
			User newUser = new User();
			newUser.setLogin(login);
			newUser.setPsswd(password);
			newUser.setUserType("E");
			newUser.setUserName(name);
			newUser.setUserSurname(surname);
			newUser.setUserEmail(email);
			newUser.setRegisterDate(new Date());
			newUser.setLastPsswdDate(new Date());
			newUser.setPsswdAttemps(0);
			newUser.setUserStatus(true);
			// Load user to DB
			userDAO.save(newUser);
			return "index";
		}
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getRegisterDate() {
		return registerDate;
	}

	public void setRegisterDate(Date registerDate) {
		this.registerDate = registerDate;
	}

	public Date getLastPsswdDate() {
		return lastPsswdDate;
	}

	public void setLastPsswdDate(Date lastPsswdDate) {
		this.lastPsswdDate = lastPsswdDate;
	}

	public int getPsswdAttempts() {
		return psswdAttempts;
	}

	public void setPsswdAttempts(int psswdAttempts) {
		this.psswdAttempts = psswdAttempts;
	}

	public boolean isUserStatus() {
		return userStatus;
	}

	public void setUserStatus(boolean userStatus) {
		this.userStatus = userStatus;
	}

	public String[] getUserTypes() {
		return userTypes;
	}

	public void setUserTypes(String[] userTypes) {
		this.userTypes = userTypes;
	}

}
