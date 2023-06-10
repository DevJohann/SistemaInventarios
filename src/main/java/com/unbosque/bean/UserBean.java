package com.unbosque.bean;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;

import com.unbosque.dao.AuditDAO;
import com.unbosque.dao.UserDAO;
import com.unbosque.dao.impl.AuditDAOImpl;
import com.unbosque.dao.impl.UserDAOImpl;
import com.unbosque.entity.Audit;
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
		AuditDAO auditDAO = new AuditDAOImpl();
		Audit audit = new Audit();
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

					// Audit register
					audit.setUserId(user.getLogin());
					audit.setDate(new Date());
					audit.setAction("L");
					audit.setTableId("users");
					try {
						audit.setIpAddress(InetAddress.getLocalHost().getHostAddress());
					} catch (UnknownHostException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					auditDAO.save(audit);
					// Employee view
					return "VistaEmpleado";
				}
				// Audit register
				audit.setUserId(user.getLogin());
				audit.setDate(new Date());
				audit.setAction("L");
				audit.setTableId("users");
				try {
					audit.setIpAddress(InetAddress.getLocalHost().getHostAddress());
				} catch (UnknownHostException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				auditDAO.save(audit);

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
			if (userNotValid.getUserType().equals("E")) {
				userNotValid.setPsswdAttemps(userNotValid.getPsswdAttemps() + 1);
			}

			// Validate if password attempts are greater than 3
			if (userNotValid.getPsswdAttemps() >= 3 && userNotValid.getUserType().equals("E")) {
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
		RegisterBean rb = new RegisterBean();
		UserDAO dao = new UserDAOImpl();
		user.setPsswd(rb.encryptPassword(user.getPsswd(), "MD5"));
		user.setLastPsswdDate(new Date());
		dao.update(user);
		return "listarUsuarios";
	}

	public String eliminarUsuario() {
		User userTemp = (User) (listaUsuarios.getRowData());
		UserDAO dao = new UserDAOImpl();
		userTemp.setUserStatus(false);
		dao.update(userTemp);
		return "listarUsuarios";
	}

	public String prepararInsert() {
		user = new User();
		user.setLastPsswdDate(new Date());
		user.setRegisterDate(new Date());
		user.setPsswdAttemps(0);
		// user.setUserStatus(true);
		return "insertarUsuario";
	}

	public String realizarInsert() {
		UserDAO dao = new UserDAOImpl();
		RegisterBean rb = new RegisterBean();
		if(dao.retrieveUser(user.getLogin()) == null) {
			user.setPsswd(rb.encryptPassword(user.getPsswd(), "MD5"));
			dao.save(user);
			return "listarUsuarios";
		}
		return "insertarUsuario";
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
