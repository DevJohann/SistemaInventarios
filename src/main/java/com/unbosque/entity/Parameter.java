package com.unbosque.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the parameters database table.
 * 
 */
@Entity
@Table(name="parameters")
@NamedQuery(name="Parameter.findAll", query="SELECT p FROM Parameter p")
public class Parameter implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	@Column(name="param_desc")
	private String paramDesc;

	@Column(name="param_name")
	private String paramName;

	@Column(name="param_status")
	private String paramStatus;

	@Column(name="param_value")
	private String paramValue;

	public Parameter() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getParamDesc() {
		return this.paramDesc;
	}

	public void setParamDesc(String paramDesc) {
		this.paramDesc = paramDesc;
	}

	public String getParamName() {
		return this.paramName;
	}

	public void setParamName(String paramName) {
		this.paramName = paramName;
	}

	public String getParamStatus() {
		return this.paramStatus;
	}

	public void setParamStatus(String paramStatus) {
		this.paramStatus = paramStatus;
	}

	public String getParamValue() {
		return this.paramValue;
	}

	public void setParamValue(String paramValue) {
		this.paramValue = paramValue;
	}

}