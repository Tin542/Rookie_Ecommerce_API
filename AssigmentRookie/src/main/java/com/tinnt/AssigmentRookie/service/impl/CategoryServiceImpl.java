package com.tinnt.AssigmentRookie.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tinnt.AssigmentRookie.entity.Category;
import com.tinnt.AssigmentRookie.repository.CategoryRepository;
import com.tinnt.AssigmentRookie.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private CategoryRepository cateRepository;

	@Override
	public Category addCategory(Category category) {
		return cateRepository.save(category);
	}

	@Override
	public Category updateCategory(Category category) {
		return cateRepository.save(category);
	}

	@Override
	public Optional<Category> getCategoryByID(long id) {
		return cateRepository.findById(id);
	}

	@Override
	public Optional<Category> getCategoryByName(String name) {
		return cateRepository.findByName(name);
	}

	@Override
	public List<Category> getAllCategory() {
		return cateRepository.findAll();
	}

}
