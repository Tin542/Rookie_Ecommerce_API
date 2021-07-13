package com.tinnt.AssigmentRookie.service;

import java.util.List;

import com.tinnt.AssigmentRookie.dto.CategoryDTO;

public interface CategoryService {

	public List<CategoryDTO> getAllCategory();
	
	public CategoryDTO getCategoryByName(String name);
	
	public CategoryDTO getCategoryByID(long id);
	
	public CategoryDTO addCategory(CategoryDTO cateDTO);
	
	public CategoryDTO updateCategory(long id, CategoryDTO cateDTO);
	
	public void deleteCategory (long id);
}
