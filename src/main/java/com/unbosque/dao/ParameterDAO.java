package com.unbosque.dao;

import java.util.List;

import com.unbosque.entity.Parameter;

public interface ParameterDAO {

	public List<Parameter> getParameters();

	public void update(Parameter parameter);
}
