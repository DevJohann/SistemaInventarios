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
import com.unbosque.dao.CategoryDAO;
import com.unbosque.dao.impl.AuditDAOImpl;
import com.unbosque.dao.impl.CategoryDAOImpl;
import com.unbosque.entity.Audit;
import com.unbosque.entity.Category;

@ManagedBean
@SessionScoped
public class CategoryBean {

	private Category category = new Category();
	private DataModel listaCategorias;

	/*
	 * public List<Category> getCategoryList() { CategoryDAO categoryDAO = new
	 * CategoryDAOImpl(); List<Category> categoryList = categoryDAO.getCategory();
	 * return categoryList; }
	 */

	public DataModel getListarCategoria() {
		List<Category> lista = new CategoryDAOImpl().getCategory();
		listaCategorias = new ListDataModel(lista);
		return listaCategorias;
	}

	public String prepararUpdate() {
		category = (Category) (listaCategorias.getRowData());
		return "actualizarCategoria";
	}

	public String realizarUpdate() {
		CategoryDAO dao = new CategoryDAOImpl();
		AuditDAO auditDAO = new AuditDAOImpl();
		Audit audit = new Audit();

		dao.update(category);

		// Audit register
		audit.setUserId(category.getId() + "");
		audit.setDate(new Date());
		audit.setAction("U");
		audit.setTableId("categories");
		try {
			audit.setIpAddress(InetAddress.getLocalHost().getHostAddress());
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		auditDAO.save(audit);
		return "listarCategorias";
	}

	public String eliminarCategoria() {
		Category categoryTemp = (Category) (listaCategorias.getRowData());
		CategoryDAO dao = new CategoryDAOImpl();
		AuditDAO auditDAO = new AuditDAOImpl();
		Audit audit = new Audit();

		categoryTemp.setCatStatus((byte) 0);
		dao.update(categoryTemp);

		// Audit register
		audit.setUserId(category.getId() + "");
		audit.setDate(new Date());
		audit.setAction("D");
		audit.setTableId("categories");
		try {
			audit.setIpAddress(InetAddress.getLocalHost().getHostAddress());
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		auditDAO.save(audit);
		return "listarCategorias";
	}

	public String prepararInsertar() {
		category = new Category();
		category.setCatStatus((byte) 0);
		return "insertarCategoria";
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

}
