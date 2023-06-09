package com.unbosque.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.unbosque.dao.CategoryDAO;
import com.unbosque.entity.Category;
import com.unbosque.util.HibernateUtil;

public class CategoryDAOImpl implements CategoryDAO {

	@Override
	public List<Category> getCategory() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction t = session.beginTransaction();
		List list = session.createQuery("from Category").list();
		t.commit();
		return list;
	}

	@Override
	public void update(Category category) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction t = session.beginTransaction();
		session.update(category);
		t.commit();
	}

	@Override
	public void remove(Category category) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction t = session.beginTransaction();
		session.delete(category);
		t.commit();
	}

}
