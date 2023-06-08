package com.unbosque.bean;

import java.util.Date;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

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

	public RegisterBean() {

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

}
