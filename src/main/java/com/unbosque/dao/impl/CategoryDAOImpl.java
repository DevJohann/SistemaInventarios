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
		return list;
	}

}
