package com.tinnt.AssigmentRookie.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tinnt.AssigmentRookie.constans.ErrorCode;
import com.tinnt.AssigmentRookie.constans.SuccessCode;
import com.tinnt.AssigmentRookie.converter.CategoryConverter;
import com.tinnt.AssigmentRookie.dto.CategoryDTO;
import com.tinnt.AssigmentRookie.dto.ResponseDTO;
import com.tinnt.AssigmentRookie.entity.Category;
import com.tinnt.AssigmentRookie.exception.AddException;
import com.tinnt.AssigmentRookie.exception.NotFoundException;
import com.tinnt.AssigmentRookie.exception.UpdateException;
import com.tinnt.AssigmentRookie.service.CategoryService;

import javax.validation.Valid;

@CrossOrigin
@RestController
@RequestMapping("BookStore/category")
public class CategoryController {
	
	@Autowired
	private CategoryService cateService;
	
	@Autowired
	private CategoryConverter cateConvert;
	
	@GetMapping
	public ResponseEntity<ResponseDTO> getAllCategory(){
			
		ResponseDTO response = new ResponseDTO();
		try {
			List<Category> listCateEntity = cateService.getAllCategory();
			List<CategoryDTO> listCateDTO = new ArrayList<CategoryDTO>();
			for (Category category : listCateEntity) {
				CategoryDTO cateDTO = cateConvert.toDTO(category);
				listCateDTO.add(cateDTO);
			}
			response.setData(listCateDTO);
			response.setSuccessCode(SuccessCode.CATEGORY_GET_SUCCESS);
		} catch (Exception e) {
				throw new NotFoundException("No category exist !");
		}
		return ResponseEntity.ok().body(response);
	}
	
	//Get by Name
	@GetMapping(value = "/get-by-name/{name}")
	public ResponseEntity<ResponseDTO> getCateByName(@PathVariable(name = "name") String name){
		ResponseDTO response = new ResponseDTO();
		try {
			Optional<Category> optional = cateService.getCategoryByName(name);
			if(optional.isEmpty()) {
				response.setErrorCode(ErrorCode.CATEGORY_FIND_ERROR);
			}else{
				Category cateEntity = optional.get();
				response.setSuccessCode(SuccessCode.CATEGORY_FIND_SUCCESS);
				response.setData(cateConvert.toDTO(cateEntity));
			}

			
		} catch (Exception e) {
			throw new NotFoundException(e.getMessage());
		}
		return ResponseEntity.ok().body(response);
	}
	
	//Get by Id
	@GetMapping(value = "/get-by-id/{id}")
	public ResponseEntity<ResponseDTO> getCateById(@PathVariable(name = "id")Long id) throws NotFoundException{
		ResponseDTO response = new ResponseDTO();
		try {
			Optional<Category> optional = cateService.getCategoryByID(id);
			if(optional.isPresent()) {
				response.setSuccessCode(SuccessCode.CATEGORY_FIND_SUCCESS);
				Category cateEntity = optional.get();
				response.setData(cateConvert.toDTO(cateEntity));
			}else {
				response.setErrorCode(ErrorCode.CATEGORY_FIND_ERROR);
			}
			
		} catch (Exception e) {
			response.setErrorCode(ErrorCode.CATEGORY_FIND_ERROR);
			throw new NotFoundException("Category not found !");
		}
		return ResponseEntity.ok().body(response);
	}
	
	//Add Category
	@PostMapping
	public ResponseEntity<ResponseDTO> addCategory(@Valid @RequestBody CategoryDTO categoryDTO){
		ResponseDTO responseDTO = new ResponseDTO();
		try {
			Category cateEntity = cateConvert.toEntity(categoryDTO);
			cateEntity = cateService.addCategory(cateEntity);
			responseDTO.setData(cateConvert.toDTO(cateEntity));
			responseDTO.setSuccessCode(SuccessCode.CATEGORY_ADD_SUCCESS);
		} catch (Exception e) {
			responseDTO.setErrorCode(ErrorCode.CATEGORY_ADD_ERROR);
			throw new AddException("Add fail !");
		}
		
		return ResponseEntity.ok().body(responseDTO);
	}
	
	//Update Category
	@PutMapping(value = "/update/{id}")
	public ResponseEntity<ResponseDTO> updateCategory(@Valid @RequestBody CategoryDTO cateDTO, @PathVariable(name = "id")Long id) {
		ResponseDTO respone = new ResponseDTO();
		try {
			Optional<Category> cate = cateService.getCategoryByID(id);
			if(cate.isEmpty()) {
				respone.setErrorCode(ErrorCode.CATEGORY_FIND_ERROR);
				throw new NotFoundException("Cannot found category !");
			}
			
			String name = cateDTO.getCategoryName();
			Category cateEntity = cate.get();
			cateEntity.setCategoryName(name);
			cateEntity.setCategoryID(id);
			respone.setData(cateConvert.toDTO(cateEntity));
			respone.setSuccessCode(SuccessCode.CATEGORY_UPDATE_SUCCESS);
			
		} catch (Exception e) {
			respone.setErrorCode(ErrorCode.CATEGORY_UPDATE_ERROR);
			throw new UpdateException("Update fail");
		}
		return ResponseEntity.ok().body(respone);
	}
}
