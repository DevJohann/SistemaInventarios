package com.unbosque.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.unbosque.dao.AuditDAO;
import com.unbosque.entity.Audit;
import com.unbosque.util.HibernateUtil;

public class AuditDAOImpl implements AuditDAO {

	@Override
	public List<Audit> getAudits() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction t = session.beginTransaction();
		List list = session.createQuery("from Audit").list();
		return list;
	}

	
}
