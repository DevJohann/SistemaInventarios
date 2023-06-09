package com.unbosque.bean;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;

import com.unbosque.dao.UserDAO;
import com.unbosque.dao.impl.UserDAOImpl;
import com.unbosque.entity.User;

@ManagedBean
@SessionScoped
public class UserBean {

	private String tempLoginVal;
	private String tempPassVal;

	private DataModel listaUsuarios;
	private User user;

	private String[] userTypes = { "Empleado", "Administrador" };

	public UserBean() {
		// TODO Auto-generated constructor stub
	}

	public String checkLogin() {
		UserDAO userDAO = new UserDAOImpl();
		// Retrieving params
		// System.out.println(tempLoginVal + tempPassVal);
		User user = userDAO.retrieveUser(tempLoginVal, tempPassVal);
		if (user != null) {
			// Check if user is available
			if (user.getUserStatus()) {
				// Log in
				// Set password attempts to 0
				user.setPsswdAttemps(0);
				userDAO.update(user);
				// Check user status
				// System.out.println(user.getUserType().equals("E"));
				if (user.getUserType().equals("E")) {
					// Employee view
					return "VistaEmpleado";
				}
				// Admin view
				return "ListaTablas";
			} else {
				// Cannot log in: User not available
				return "login";
			}

		} else {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_WARN, "Credenciales incorrectas", "Vuelva a intentarlo"));

			// Find user when password was incorrect to add password attempt
			User userNotValid = userDAO.retrieveUser(tempLoginVal);
			// System.out.println(userNotValid.getLogin());

			if (userNotValid == null) {
				// Username is also invalid
				return "login";
			}
			userNotValid.setPsswdAttemps(userNotValid.getPsswdAttemps() + 1);

			// Validate if password attempts are greater than 3
			if (userNotValid.getPsswdAttemps() >= 3) {
				// Disable user
				userNotValid.setUserStatus(false);
				userDAO.update(userNotValid);
				return "login";
			}
			userDAO.update(userNotValid);
			return "login";
		}
	}

	public DataModel getListarUsuarios() {
		List<User> lista = new UserDAOImpl().getUsers();
		listaUsuarios = new ListDataModel(lista);
		return listaUsuarios;
	}

	public String prepararUpdate() {
		user = (User) listaUsuarios.getRowData();
		return "actualizarUsuario";
	}

	public String realizarUpdate() {
		UserDAO dao = new UserDAOImpl();
		dao.update(user);
		return "listarUsuarios";
	}

	public DataModel getListaUsuarios() {
		return listaUsuarios;
	}

	public void setListaUsuarios(DataModel listaUsuarios) {
		this.listaUsuarios = listaUsuarios;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String[] getUserTypes() {
		return userTypes;
	}

	public void setUserTypes(String[] userTypes) {
		this.userTypes = userTypes;
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
