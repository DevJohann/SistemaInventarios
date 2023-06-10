package com.unbosque.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * The persistent class for the categories database table.
 * 
 */
@Entity
@Table(name = "categories")
@NamedQuery(name = "Category.findAll", query = "SELECT c FROM Category c")
public class Category implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	@Column(name = "cat_name")
	private String catName;

	@Column(name = "cat_status")
	private byte catStatus;

	public Category() {
	}

	public String getCalculateStatus() {
		return (catStatus == (byte) 1) ? "ACTIVO" : "INACTIVO";
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCatName() {
		return this.catName;
	}

	public void setCatName(String catName) {
		this.catName = catName;
	}

	public byte getCatStatus() {
		return this.catStatus;
	}

	public void setCatStatus(byte catStatus) {
		this.catStatus = catStatus;
	}

}