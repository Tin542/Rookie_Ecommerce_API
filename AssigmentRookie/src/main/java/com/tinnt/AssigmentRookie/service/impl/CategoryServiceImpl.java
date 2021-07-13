package com.tinnt.AssigmentRookie.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tinnt.AssigmentRookie.converter.CategoryConverter;
import com.tinnt.AssigmentRookie.dto.CategoryDTO;
import com.tinnt.AssigmentRookie.entity.Category;
import com.tinnt.AssigmentRookie.exception.NotFoundException;
import com.tinnt.AssigmentRookie.repository.CategoryRepository;
import com.tinnt.AssigmentRookie.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService{

	@Autowired
	private CategoryRepository cateRepository;
	
	@Autowired
	private CategoryConverter cateConvert;
	
	@Override
	public List<CategoryDTO> getAllCategory() {
		List<Category> listCateEntity = cateRepository.findAll();
		List<CategoryDTO> listCateDTO = new ArrayList<CategoryDTO>();
		for (Category cateEntity : listCateEntity) {
			CategoryDTO catDTO = cateConvert.toDTO(cateEntity);
			listCateDTO.add(catDTO);
		}
		return listCateDTO;
	}

	@Override
	public CategoryDTO getCategoryByName(String name) {
		Category cateEntity = cateRepository.findOneByCategoryName(name);
		if(cateEntity == null) {
			throw new NotFoundException("Category not exist !");
		}
		CategoryDTO cateDTO = cateConvert.toDTO(cateEntity);
		return cateDTO;
	}

	@Override
	public CategoryDTO getCategoryByID(long id) {
		Category cateEntity = cateRepository.findById(id).get();
		if(cateEntity == null) {
			throw new NotFoundException("Category not exist !");
		}
		CategoryDTO cateDTO = cateConvert.toDTO(cateEntity);
		return cateDTO;
	}

	@Override
	public CategoryDTO addCategory(CategoryDTO cateDTO) {
		Category cateEntity = cateConvert.toEntity(cateDTO);
		cateEntity = cateRepository.save(cateEntity);
		return cateConvert.toDTO(cateEntity);
	}

	@Override
	public CategoryDTO updateCategory(long id, CategoryDTO cateDTO) {
		Category cateEntity = cateRepository.findById(id).get();
		if(cateEntity == null) {
			throw new NotFoundException("Categoey not exist !");
		}
		Category newCateEntity = cateConvert.toEntity(cateDTO, cateEntity);
		newCateEntity = cateRepository.save(newCateEntity);		
		return cateConvert.toDTO(newCateEntity);
	}

	@Override
	public void deleteCategory(long id) {
		Category cateEntity = cateRepository.findById(id).get();
		if(cateEntity == null) {
			throw new NotFoundException("category not exist !");
		}
		cateRepository.deleteById(id);
		
	}

}
