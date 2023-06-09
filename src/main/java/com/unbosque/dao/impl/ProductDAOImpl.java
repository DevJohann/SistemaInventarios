package com.unbosque.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.unbosque.dao.ProductDAO;
import com.unbosque.util.HibernateUtil;
import com.unbosque.entity.Product;

public class ProductDAOImpl implements ProductDAO {

	@Override
	public List<Product> getProducts() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction t = session.beginTransaction();
		List products = session.createQuery("from Product").list();
		t.commit();
		return products;
	}

	@Override
	public void update(Product products) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction t = session.beginTransaction();
		session.update(products);
		t.commit();
	}

	@Override
	public void remove(Product product) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction t = session.beginTransaction();
		session.delete(product);
		t.commit();
	}

}
