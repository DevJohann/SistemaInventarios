package com.unbosque.bean;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.DataModel;

import com.unbosque.dao.impl.ProductDAOImpl;
import com.unbosque.entity.Product;
import com.unbosque.dao.ProductDAO;

import javax.faces.model.ListDataModel;

@ManagedBean
@SessionScoped
public class ProductBean {
	
	private DataModel listaProductos;
	private Product product = new Product();
/*
	public List<Product> getProducts() {
		ProductDAOImpl productDao = new ProductDAOImpl();
		List<Product> products = productDao.getProducts();
		return products;
	}*/
	
	public String prepararUpdate() {
		product = (Product) (listaProductos.getRowData());
		return "actualizarProducto";
	}
	
	public String realizarUpdate() {
		ProductDAO dao = new ProductDAOImpl();
		dao.update(product);
		return "listarProductos";
	}
	
	public DataModel getListarProductos() {
		List<Product> lista = new ProductDAOImpl().getProducts();
		listaProductos = new ListDataModel(lista);
		return listaProductos;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}
	
}
