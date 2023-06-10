package com.unbosque.bean;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;

import com.unbosque.dao.ProductDAO;
import com.unbosque.dao.impl.ProductDAOImpl;
import com.unbosque.entity.Product;

@ManagedBean
@SessionScoped
public class ProductBean {

	private DataModel listaProductos;
	private Product product;
	/*
	 * public List<Product> getProducts() { ProductDAOImpl productDao = new
	 * ProductDAOImpl(); List<Product> products = productDao.getProducts(); return
	 * products; }
	 */

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

	public String eliminarProducto() {
		Product productTemp = (Product) (listaProductos.getRowData());
		ProductDAO dao = new ProductDAOImpl();
		dao.remove(productTemp);
		return "listarProductos";
	}

	public String restarStock() {
		ProductDAO dao = new ProductDAOImpl();
		product = (Product) (listaProductos.getRowData());
		if (product.getProductStock() >= 1) {
			product.setProductStock(product.getProductStock() - 1);
			dao.update(product);
			return "vistaEmpleado";
		} else {
			return "vistaEmpleado";
		}
	}

	public String prepararInsert() {
		product = new Product();
		return "insertarProducto";
	}

	public String getRealizarInsert() {
		System.out.println("dlfjs");
		ProductDAO dao = new ProductDAOImpl();
		dao.save(product);
		return "listarProductos";
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

}
