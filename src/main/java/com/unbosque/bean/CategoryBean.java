package com.unbosque.bean;

import java.util.List;

import com.unbosque.dao.CategoryDAO;
import com.unbosque.dao.impl.CategoryDAOImpl;
import com.unbosque.entity.Category;

public class CategoryBean {
	public List<Category> getCategoryList() {
		CategoryDAO categoryDAO = new CategoryDAOImpl();
		List<Category> categoryList = categoryDAO.getCategory();
		return categoryList;

	}
}
