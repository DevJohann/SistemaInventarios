package com.unbosque.bean;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import com.unbosque.dao.impl.ProductDAOImpl;
import com.unbosque.entity.Product;

@ManagedBean
@SessionScoped
public class ProductBean {

	public List<Product> getProducts() {
		ProductDAOImpl productDao = new ProductDAOImpl();
		List<Product> products = productDao.getProducts();
		return products;

	}
}
