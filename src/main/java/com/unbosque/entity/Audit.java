package com.unbosque.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the audits database table.
 * 
 */
@Entity
@Table(name="audits")
@NamedQuery(name="Audit.findAll", query="SELECT a FROM Audit a")
public class Audit implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	private String action;

	@Temporal(TemporalType.TIMESTAMP)
	private Date date;

	@Column(name="ip_address")
	private String ipAddress;

	@Column(name="table_id")
	private String tableId;

	@Column(name="user_id")
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