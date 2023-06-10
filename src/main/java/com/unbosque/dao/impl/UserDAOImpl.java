package com.unbosque.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.unbosque.bean.RegisterBean;
import com.unbosque.dao.UserDAO;
import com.unbosque.entity.User;
import com.unbosque.util.HibernateUtil;

public class UserDAOImpl implements UserDAO {
	
	private RegisterBean rb = new RegisterBean();

	@Override
	public User retrieveUser(String username, String password) {
		Session sesion = HibernateUtil.getSessionFactory().openSession();
		Transaction t = sesion.beginTransaction();
		String encryptedPassword = rb.encryptPassword(password, "MD5");
		List response = sesion.createQuery("FROM User WHERE login='" + username + "' AND psswd='" + encryptedPassword + "'")
				.list();
		if (response.size() == 1) {
			return (User) response.get(0);
		}
		return null;
	}

	@Override
	public User retrieveUser(String username) {
		Session sesion = HibernateUtil.getSessionFactory().openSession();
		Transaction t = sesion.beginTransaction();
		List response = sesion.createQuery("FROM User WHERE login='" + username + "'").list();
		if (response.size() == 1) {
			return (User) response.get(0);
		}
		return null;
	}

	@Override
	public void update(User user) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction t = session.beginTransaction();
		session.update(user);
		t.commit();
	}

	@Override
	public void save(User user) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction t = session.beginTransaction();
		session.save(user);
		t.commit();
	}

	@Override
	public List<User> getUsers() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction t = session.beginTransaction();
		List users = session.createQuery("from User").list();
		t.commit();
		return users;
	}

	@Override
	public void remove(User user) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction t = session.beginTransaction();
		session.delete(user);
		t.commit();
	}

}
