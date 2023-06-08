package com.unbosque.bean;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import com.unbosque.dao.UserDAO;
import com.unbosque.dao.impl.UserDAOImpl;
import com.unbosque.entity.User;

@ManagedBean
@SessionScoped
public class UserBean {

	private String tempLoginVal;
	private String tempPassVal;

	public UserBean() {
		// TODO Auto-generated constructor stub
	}

	public String checkLogin() {
		UserDAO userDAO = new UserDAOImpl();
		// Retrieving params
		/*
		 * FacesContext fc = FacesContext.getCurrentInstance(); ExternalContext ex =
		 * fc.getExternalContext(); Map<String, String> params =
		 * ex.getRequestParameterMap(); System.out.println(params.get("login") +
		 * params.get("password")); User user =
		 * userDAO.retrieveUser(params.get("login"), params.get("password"));
		 */
		System.out.println(tempLoginVal + tempPassVal);
		User user = userDAO.retrieveUser(tempLoginVal, tempPassVal);
		if (user != null) {
			return "listarProductos";
		} else {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_WARN, "Credenciales incorrectas", "Vuelva a intentarlo"));
			return "login";
		}
	}

	public String getTempLoginVal() {
		return tempLoginVal;
	}

	public void setTempLoginVal(String tempLoginVal) {
		this.tempLoginVal = tempLoginVal;
	}

	public String getTempPassVal() {
		return tempPassVal;
	}

	public void setTempPassVal(String tempPassVal) {
		this.tempPassVal = tempPassVal;
	}

}
