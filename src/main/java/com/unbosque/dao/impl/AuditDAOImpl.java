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
		t.commit();
		return list;
	}

	@Override
	public void update(Audit audit) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction t = session.beginTransaction();
		session.update(audit);
		t.commit();
	}

	
}
