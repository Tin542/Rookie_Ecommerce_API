package com.tinnt.AssigmentRookie.service;

import java.util.List;
import java.util.Optional;

import com.tinnt.AssigmentRookie.entity.Category;

public interface CategoryService {

	public List<Category> getAllCategory();
	
	public Optional<Category> getCategoryByName(String name);
	
	public Optional<Category> getCategoryByID(long id);
	
	public Category addCategory(Category category);
	
	public Category updateCategory(Category category);
}
