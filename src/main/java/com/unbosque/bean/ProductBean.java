package com.unbosque.bean;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import com.unbosque.entity.Product;
import com.unbosque.dao.impl.ProductDAOImpl;

@ManagedBean
@SessionScoped
public class ProductBean {

	private List<Product> products = null;
	
	public List<Product> getProducts(){
		ProductDAOImpl productDao = new ProductDAOImpl();
		products = productDao.getProducts();
		return products;
		
	}
}
