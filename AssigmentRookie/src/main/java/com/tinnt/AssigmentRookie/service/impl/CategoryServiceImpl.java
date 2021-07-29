package com.tinnt.AssigmentRookie.service.impl;

import java.util.List;
import java.util.Optional;

import com.tinnt.AssigmentRookie.constans.ErrorCode;
import com.tinnt.AssigmentRookie.exception.AddException;
import com.tinnt.AssigmentRookie.exception.NotFoundException;
import com.tinnt.AssigmentRookie.exception.UpdateException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.tinnt.AssigmentRookie.entity.Category;
import com.tinnt.AssigmentRookie.repository.CategoryRepository;
import com.tinnt.AssigmentRookie.service.CategoryService;

import javax.swing.text.html.Option;

@Service
public class CategoryServiceImpl implements CategoryService {

	private static final Logger LOGGER = LoggerFactory.getLogger(CategoryService.class);

	private CategoryRepository cateRepository;

	@Autowired
	public CategoryServiceImpl(CategoryRepository cateRepository) {
		this.cateRepository = cateRepository;
	}

	@Override
	public Category addCategory(Category category)throws AddException {
		Optional<Category> optional = cateRepository.findByName(category.getCategoryName());
		if(optional.isPresent()){
			LOGGER.info("Category is already existed !!");
			throw new AddException(ErrorCode.CATEGORY_EXISTED);
		}
		return cateRepository.save(category);
	}

	@Override
	public Category updateCategory(Category category, long id)throws UpdateException {
		Optional<Category> optional = cateRepository.findById(id);
		if(optional.isEmpty()){
			LOGGER.info("Not exist Category with id: "+id);
			throw new UpdateException(ErrorCode.CATEGORY_FIND_ERROR);
		}
		Category cateEntity = optional.get();
		cateEntity.setCreate_date(cateEntity.getCreate_date());
		cateEntity.setCategoryName(category.getCategoryName());
		cateEntity.setDelete(category.isDelete());
		cateEntity.setCategoryID(id);
		return cateRepository.save(cateEntity);
	}

	@Override
	public Page<Category> searchCategory(String name, Pageable pageable) {
		Page<Category> optional = cateRepository.searchCategory(name, pageable);
		if(optional.isEmpty()){
			LOGGER.info("Not exist Category with name: "+name);
			throw new NotFoundException(ErrorCode.CATEGORY_FIND_ERROR);
		}
		if(name.equals("")){
			return cateRepository.getAllCategory(pageable);
		}
		return cateRepository.searchCategory(name, pageable);
	}

	@Override
	public Optional<Category> getCategoryByID(long id)throws NotFoundException {
		Optional<Category> optional = cateRepository.findById(id);
		if(optional.isEmpty()){
			LOGGER.info("Not exist category with id: "+id);
			throw new NotFoundException(ErrorCode.CATEGORY_FIND_ERROR);
		}
		return cateRepository.findById(id);
	}

	@Override
	public Optional<Category> getCategoryByName(String name)throws NotFoundException {
		Optional<Category> optional = cateRepository.findByName(name);
		if(optional.isEmpty()){
			LOGGER.info("Not exist category with name: "+name);
			throw new NotFoundException(ErrorCode.CATEGORY_FIND_ERROR);
		}
		return cateRepository.findByName(name);
	}

	@Override
	public List<Category> getAllCategory()throws NotFoundException {
		return cateRepository.findAll();
	}

}
