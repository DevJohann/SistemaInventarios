package com.unbosque.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the users database table.
 * 
 */
@Entity
@Table(name="users")
@NamedQuery(name="User.findAll", query="SELECT u FROM User u")
public class User implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String login;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="last_psswd_date")
	private Date lastPsswdDate;

	private String psswd;

	@Column(name="psswd_attemps")
	private int psswdAttemps;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="register_date")
	private Date registerDate;

	@Column(name="user_email")
	private String userEmail;

	@Column(name="user_name")
	private String userName;

	@Column(name="user_status")
	private byte userStatus;

	@Column(name="user_surname")
	private String userSurname;

	@Column(name="user_type")
	private String userType;

	public User() {
	}

	public String getLogin() {
		return this.login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public Date getLastPsswdDate() {
		return this.lastPsswdDate;
	}

	public void setLastPsswdDate(Date lastPsswdDate) {
		this.lastPsswdDate = lastPsswdDate;
	}

	public String getPsswd() {
		return this.psswd;
	}

	public void setPsswd(String psswd) {
		this.psswd = psswd;
	}

	public int getPsswdAttemps() {
		return this.psswdAttemps;
	}

	public void setPsswdAttemps(int psswdAttemps) {
		this.psswdAttemps = psswdAttemps;
	}

	public Date getRegisterDate() {
		return this.registerDate;
	}

	public void setRegisterDate(Date registerDate) {
		this.registerDate = registerDate;
	}

	public String getUserEmail() {
		return this.userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public byte getUserStatus() {
		return this.userStatus;
	}

	public void setUserStatus(byte userStatus) {
		this.userStatus = userStatus;
	}

	public String getUserSurname() {
		return this.userSurname;
	}

	public void setUserSurname(String userSurname) {
		this.userSurname = userSurname;
	}

	public String getUserType() {
		return this.userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

}