package com.unbosque.util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.unbosque.entity.Audit;
import com.unbosque.entity.Category;
import com.unbosque.entity.Parameter;
import com.unbosque.entity.Product;
import com.unbosque.entity.User;

public class HibernateUtil {

	private static SessionFactory sessionFactory;

	private HibernateUtil() {
	}

	@SuppressWarnings("deprecation")
	public static SessionFactory getSessionFactory() {

		if (sessionFactory == null) {
			try {
				@SuppressWarnings("deprecation")
				Configuration ac = new Configuration();
				ac.addAnnotatedClass(User.class);
				ac.addAnnotatedClass(Audit.class);
				ac.addAnnotatedClass(Category.class);
				ac.addAnnotatedClass(Parameter.class);
				ac.addAnnotatedClass(Product.class);

				sessionFactory = ac.configure().buildSessionFactory();

			} catch (Throwable ex) {
				// Log the exception.
				System.err.println("Initial SessionFactory creation failed." + ex);
				throw new ExceptionInInitializerError(ex);
			}
			return sessionFactory;
		} else {
			return sessionFactory;
		}

	}

}