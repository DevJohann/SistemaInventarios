package com.unbosque.bean;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;

import com.unbosque.dao.AuditDAO;
import com.unbosque.dao.ProductDAO;
import com.unbosque.dao.impl.AuditDAOImpl;
import com.unbosque.dao.impl.ProductDAOImpl;
import com.unbosque.entity.Audit;
import com.unbosque.entity.Product;

@ManagedBean
@SessionScoped
public class ProductBean {

	private DataModel listaProductos;
	private Product product = new Product();
	private String actualKey = "";
	/*
	 * public List<Product> getProducts() { ProductDAOImpl productDao = new
	 * ProductDAOImpl(); List<Product> products = productDao.getProducts(); return
	 * products; }
	 */

	public String prepararUpdate() {
		product = (Product) (listaProductos.getRowData());
		actualKey = product.getProductRef();
		return "actualizarProducto";
	}

	public String realizarUpdate() {
		ProductDAO dao = new ProductDAOImpl();
		AuditDAO auditDAO = new AuditDAOImpl();
		Audit audit = new Audit();

		// Check if available
		List<Product> list = dao.getProducts();
		if (!actualKey.equals(product.getProductRef())) {
			for (Product x : list) {
				if (x.getProductRef().equals(product.getProductRef())) {
					// Cant save
					return "listarProductos";
				}
			}
		}
		dao.update(product);

		// Audit register
		audit.setUserId(product.getId() + "");
		audit.setDate(new Date());
		audit.setAction("U");
		audit.setTableId("products");
		try {
			audit.setIpAddress(InetAddress.getLocalHost().getHostAddress());
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		auditDAO.save(audit);

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
		AuditDAO auditDAO = new AuditDAOImpl();
		Audit audit = new Audit();

		dao.remove(productTemp);

		// Audit register
		audit.setUserId(product.getId() + "");
		audit.setDate(new Date());
		audit.setAction("D");
		audit.setTableId("products");
		try {
			audit.setIpAddress(InetAddress.getLocalHost().getHostAddress());
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		auditDAO.save(audit);

		return "listarProductos";
	}

	public String restarStock() {
		ProductDAO dao = new ProductDAOImpl();
		AuditDAO auditDAO = new AuditDAOImpl();
		Audit audit = new Audit();

		product = (Product) (listaProductos.getRowData());

		if (product.getProductStock() >= 1) {
			product.setProductStock(product.getProductStock() - 1);
			dao.update(product);

			// Audit register
			audit.setUserId(product.getId() + "");
			audit.setDate(new Date());
			audit.setAction("U");
			audit.setTableId("products");
			try {
				audit.setIpAddress(InetAddress.getLocalHost().getHostAddress());
			} catch (UnknownHostException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			auditDAO.save(audit);

			return "vistaEmpleado";
		} else {
			return "vistaEmpleado";
		}
	}

	public String prepararInsert() {
		product = new Product();
		return "insertarProducto";
	}

	public String realizarInsert() {
		ProductDAO dao = new ProductDAOImpl();
		AuditDAO auditDAO = new AuditDAOImpl();
		Audit audit = new Audit();

		// Check if available
		List<Product> list = dao.getProducts();
		for (Product x : list) {
			if (x.getProductRef().equals(product.getProductRef())) {
				// Cant save
				return "listarProductos";
			}
		}
		dao.save(product);

		// Audit register
		audit.setUserId(product.getId() + "");
		audit.setDate(new Date());
		audit.setAction("I");
		audit.setTableId("products");
		try {
			audit.setIpAddress(InetAddress.getLocalHost().getHostAddress());
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		auditDAO.save(audit);
		return "listarProductos";
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

}
