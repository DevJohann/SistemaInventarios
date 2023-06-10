package com.unbosque.bean;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.Properties;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.unbosque.dao.AuditDAO;
import com.unbosque.dao.UserDAO;
import com.unbosque.dao.impl.AuditDAOImpl;
import com.unbosque.dao.impl.UserDAOImpl;
import com.unbosque.entity.Audit;
import com.unbosque.entity.User;

@ManagedBean
@RequestScoped
public class RegisterBean {

	private String login;
	private String password;
	private String userType;
	private String name;
	private String surname;
	private String email;
	private Date registerDate;
	private Date lastPsswdDate;
	private int psswdAttempts;
	private boolean userStatus;

	// For frontend
	private String[] userTypes = { "Empleado" };

	public RegisterBean() {

	}

	public String registerNewUser() {
		UserDAO userDAO = new UserDAOImpl();
		AuditDAO auditDAO = new AuditDAOImpl();
		Audit audit = new Audit();

		// Check if user already exists
		if (userDAO.retrieveUser(login) == null) {
			// User is available
			User newUser = new User();
			newUser.setLogin(login);
			newUser.setPsswd(encryptPassword(password, "MD5"));
			newUser.setUserType("E");
			newUser.setUserName(name);
			newUser.setUserSurname(surname);
			newUser.setUserEmail(email);
			newUser.setRegisterDate(new Date());
			newUser.setLastPsswdDate(new Date());
			newUser.setPsswdAttemps(0);
			newUser.setUserStatus(true);
			// Load user to DB
			this.sendEmail();
			userDAO.save(newUser);

			// Audit register
			audit.setUserId(newUser.getLogin());
			audit.setDate(new Date());
			audit.setAction("I");
			audit.setTableId("users");
			try {
				audit.setIpAddress(InetAddress.getLocalHost().getHostAddress());
			} catch (UnknownHostException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			auditDAO.save(audit);

			return "index";
		} else {
			System.out.println("Paila");
			// User is not available
			return "nuevoRegistro";
		}
	}

	public String encryptPassword(String psswd, String algorithm) {
		byte[] digest = null;
		byte[] buffer = psswd.getBytes();
		try {
			MessageDigest messageDigest = MessageDigest.getInstance(algorithm);
			messageDigest.reset();
			messageDigest.update(buffer);
			digest = messageDigest.digest();
		} catch (NoSuchAlgorithmException e) {
			System.out.println("Error encriptando la contraseña");
		}
		return toHex(digest);
	}

	private String toHex(byte[] digest) {
		String hash = "";
		for (byte aux : digest) {
			int b = aux & 0xff;
			if (Integer.toHexString(b).length() == 1) {
				hash += "0";
			}
			hash += Integer.toHexString(b);
		}
		return hash;
	}

	public void sendEmail() {
		Properties prop = new Properties();
		prop.setProperty("mail.smtp.host", "smtp.gmail.com");
		prop.setProperty("mail.smtp.starttls.enable", "true");
		prop.setProperty("mail.smtp.port", "587");
		prop.setProperty("mail.smtp.auth", "true");

		Session session = Session.getDefaultInstance(prop);
		String sourceMail = "inventariojsf@gmail.com";
		String sourceMailPassword = "yudwrujxoyloubqv";
		String targetMail = email;
		// System.out.println("Target mail: " + target.getMail());
		String header = "Gracias por registrarse en el software de inventarios";
		String body = "Bienvenido";
		MimeMessage mail = new MimeMessage(session);
		try {
			mail.setFrom(new InternetAddress(sourceMail));
			mail.addRecipient(Message.RecipientType.TO, new InternetAddress(targetMail));
			mail.setSubject(header);
			mail.setText(body);

			Transport proto = session.getTransport("smtp");
			proto.connect(sourceMail, sourceMailPassword);
			proto.sendMessage(mail, mail.getRecipients(Message.RecipientType.TO));
			proto.close();
		} catch (AddressException e) {
			System.out.println("Error con las direcciones de correo");
		} catch (MessagingException e) {
			System.out.println("Error creando el mensaje del correo");
			e.printStackTrace();
		}
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getRegisterDate() {
		return registerDate;
	}

	public void setRegisterDate(Date registerDate) {
		this.registerDate = registerDate;
	}

	public Date getLastPsswdDate() {
		return lastPsswdDate;
	}

	public void setLastPsswdDate(Date lastPsswdDate) {
		this.lastPsswdDate = lastPsswdDate;
	}

	public int getPsswdAttempts() {
		return psswdAttempts;
	}

	public void setPsswdAttempts(int psswdAttempts) {
		this.psswdAttempts = psswdAttempts;
	}

	public boolean isUserStatus() {
		return userStatus;
	}

	public void setUserStatus(boolean userStatus) {
		this.userStatus = userStatus;
	}

	public String[] getUserTypes() {
		return userTypes;
	}

	public void setUserTypes(String[] userTypes) {
		this.userTypes = userTypes;
	}

}
