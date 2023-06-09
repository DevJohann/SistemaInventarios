package com.unbosque.bean;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;

import com.unbosque.dao.ParameterDAO;
import com.unbosque.dao.impl.ParameterDAOImpl;
import com.unbosque.entity.Parameter;

@ManagedBean
@SessionScoped
public class ParameterBean {
	private DataModel listaParametros;
	private Parameter parameter = new Parameter();

	public DataModel getListarProductos() {
		List<Parameter> lista = new ParameterDAOImpl().getParameters();
		listaParametros = new ListDataModel(lista);
		return listaParametros;
	}

	public String prepararUpdate() {
		parameter = (Parameter) listaParametros.getRowData();
		return "actualizarParametro";
	}

	public String realizarUpdate() {
		ParameterDAO dao = new ParameterDAOImpl();
		dao.update(parameter);
		return "listarParametros";
	}
	
	public String eliminarParameter() {
		Parameter parameterTemp = (Parameter)(listaParametros.getRowData());
		ParameterDAO dao = new ParameterDAOImpl();
		dao.remove(parameterTemp);
		return "listarParametros";
	}

	public Parameter getParameter() {
		return parameter;
	}

	public void setParameter(Parameter parameter) {
		this.parameter = parameter;
	}

}
