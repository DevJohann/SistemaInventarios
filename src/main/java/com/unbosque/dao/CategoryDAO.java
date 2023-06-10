package com.unbosque.dao;

import java.util.List;

import com.unbosque.entity.Category;

public interface CategoryDAO {
	public List<Category> getCategory();

	public void update(Category category);

	public void remove(Category category);

	public void save(Category category);
}
