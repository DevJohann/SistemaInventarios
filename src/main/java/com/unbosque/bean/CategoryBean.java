package com.unbosque.bean;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;

import com.unbosque.dao.CategoryDAO;
import com.unbosque.dao.impl.CategoryDAOImpl;
import com.unbosque.entity.Category;



@ManagedBean
@SessionScoped
public class CategoryBean {
	
	private Category category = new Category();
	private DataModel listaCategorias;
	
	/*
	public List<Category> getCategoryList() {
		CategoryDAO categoryDAO = new CategoryDAOImpl();
		List<Category> categoryList = categoryDAO.getCategory();
		return categoryList;
	}*/
	
	public DataModel getListarCategoria() {
		List<Category> lista = new CategoryDAOImpl().getCategory();
		listaCategorias = new ListDataModel(lista);
		return listaCategorias;
	}
	
	public String prepararUpdate() {
		category = (Category)(listaCategorias.getRowData());
		return "actualizarCategoria";
	}
	
	public String realizarUpdate() {
		CategoryDAO dao = new CategoryDAOImpl();
		dao.update(category);
		return "listarCategorias";
	}
	
	public String eliminarCategoria() {
		Category categoryTemp = (Category)(listaCategorias.getRowData());
		CategoryDAO dao = new CategoryDAOImpl();
		dao.remove(categoryTemp);
		return "listarCategorias";
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}
	
}
