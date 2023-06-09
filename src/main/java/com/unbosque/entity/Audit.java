package com.unbosque.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * The persistent class for the audits database table.
 * 
 */
@Entity
@Table(name = "audits")
public class Audit implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	@Column(name = "action")
	private String action;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "date")
	private Date date;

	@Column(name = "ip_address")
	private String ipAddress;

	@Column(name = "table_id")
	private String tableId;

	@Column(name = "user_id")
	private String userId;

	public Audit() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAction() {
		return this.action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public Date getDate() {
		return this.date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getIpAddress() {
		return this.ipAddress;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

	public String getTableId() {
		return this.tableId;
	}

	public void setTableId(String tableId) {
		this.tableId = tableId;
	}

	public String getUserId() {
		return this.userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

}