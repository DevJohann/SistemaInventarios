package com.unbosque.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.unbosque.dao.ParameterDAO;
import com.unbosque.entity.Parameter;
import com.unbosque.util.HibernateUtil;

public class ParameterDAOImpl implements ParameterDAO {

	@Override
	public List<Parameter> getParameters() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction t = session.beginTransaction();
		List parameters = session.createQuery("from Parameter").list();
		t.commit();
		return parameters;
	}

	@Override
	public void update(Parameter parameter) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction t = session.beginTransaction();
		session.update(parameter);
		t.commit();
	}

	@Override
	public void remove(Parameter parameter) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction t = session.beginTransaction();
		session.delete(parameter);
		t.commit();
	}

}
