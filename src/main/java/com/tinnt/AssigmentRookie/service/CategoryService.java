package com.tinnt.AssigmentRookie.service;

import java.util.List;
import java.util.Optional;

import com.tinnt.AssigmentRookie.entity.Book;
import com.tinnt.AssigmentRookie.entity.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CategoryService {

	public List<Category> getAllCategory();
	
	public Optional<Category> getCategoryByName(String name);
	
	public Optional<Category> getCategoryByID(long id);
	
	public Category addCategory(Category category);
	
	public Category updateCategory(Category category, long id);

	public Page<Category> searchCategory(String name, Pageable pageable);

	public int deleteCategory(long id);
}
