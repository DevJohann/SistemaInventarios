package com.unbosque.bean;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;

import com.unbosque.dao.AuditDAO;
import com.unbosque.dao.ParameterDAO;
import com.unbosque.dao.impl.AuditDAOImpl;
import com.unbosque.dao.impl.ParameterDAOImpl;
import com.unbosque.entity.Audit;
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
		AuditDAO auditDAO = new AuditDAOImpl();
		Audit audit = new Audit();

		dao.update(parameter);

		// Audit register
		audit.setUserId(parameter.getId() + "");
		audit.setDate(new Date());
		audit.setAction("U");
		audit.setTableId("parameters");
		try {
			audit.setIpAddress(InetAddress.getLocalHost().getHostAddress());
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		auditDAO.save(audit);
		return "listarParametros";
	}

	public String eliminarParameter() {
		Parameter parameterTemp = (Parameter) (listaParametros.getRowData());
		ParameterDAO dao = new ParameterDAOImpl();
		AuditDAO auditDAO = new AuditDAOImpl();
		Audit audit = new Audit();

		dao.remove(parameterTemp);

		// Audit register
		audit.setUserId(parameter.getId() + "");
		audit.setDate(new Date());
		audit.setAction("D");
		audit.setTableId("parameters");
		try {
			audit.setIpAddress(InetAddress.getLocalHost().getHostAddress());
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		auditDAO.save(audit);
		return "listarParametros";
	}

	public Parameter getParameter() {
		return parameter;
	}

	public void setParameter(Parameter parameter) {
		this.parameter = parameter;
	}

}
