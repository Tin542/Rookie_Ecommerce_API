package com.tinnt.AssigmentRookie.converter;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tinnt.AssigmentRookie.dto.CategoryDTO;
import com.tinnt.AssigmentRookie.entity.Category;

@Component
public class CategoryConverter {
	@Autowired
	private ModelMapper mapper;
	
	//convert to Entity
	public Category toEntity(CategoryDTO cateDTO) {
		Category cateEntity = mapper.map(cateDTO, Category.class);	
		return cateEntity;
	}
	
	//convert to DTO
	public CategoryDTO toDTO(Category cateEntity) {
		CategoryDTO cateDTO = mapper.map(cateEntity, CategoryDTO.class);
		return cateDTO;
	}
	
	//convert to Entity for update
	public Category toEntity(CategoryDTO cateDTO, Category cateEntity) {
		cateEntity = mapper.map(cateDTO, Category.class);
		cateEntity.setCategoryID(cateDTO.getCategoryID());
		return cateEntity;
	}
}
