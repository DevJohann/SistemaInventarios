package com.unbosque.dao;

import java.util.List;

import com.unbosque.entity.Product;

public interface ProductDAO {

	public List<Product> getProducts();
	
	public void update(Product products);
	
}
