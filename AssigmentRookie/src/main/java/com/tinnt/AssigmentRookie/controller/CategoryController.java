package com.tinnt.AssigmentRookie.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
@RequestMapping("/category")
public class CategoryController {

	private CategoryService cateService;
	private CategoryConverter cateConvert;

	@Autowired
	public CategoryController(CategoryService cateService, CategoryConverter cateConvert) {
		this.cateService = cateService;
		this.cateConvert = cateConvert;
	}

	@GetMapping
	public ResponseEntity<ResponseDTO> getAllCategory(){
			
		ResponseDTO response = new ResponseDTO();
		try {
			List<Category> listCateEntity = cateService.getAllCategory();
			List<CategoryDTO> listCateDTO = listCateEntity.stream().map(cateConvert::toDTO).collect(Collectors.toList());

			response.setData(listCateDTO);
			response.setSuccessCode(SuccessCode.CATEGORY_GET_SUCCESS);
		} catch (Exception e) {
			throw new NotFoundException(ErrorCode.CATEGORY_FIND_ERROR);
		}
		return ResponseEntity.ok().body(response);
	}
	
	//Get by Name
	@GetMapping(value = "/get-by-name/{name}")
	public ResponseEntity<ResponseDTO> getCateByName(@PathVariable(name = "name") String name){
		ResponseDTO response = new ResponseDTO();
		try {
			Category cateEntity = cateService.getCategoryByName(name).get();
			response.setData(cateConvert.toDTO(cateEntity));
			response.setSuccessCode(SuccessCode.CATEGORY_FIND_SUCCESS);
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
			Category cateEntity = cateService.getCategoryByID(id).get();
			response.setData(cateConvert.toDTO(cateEntity));
			response.setSuccessCode(SuccessCode.CATEGORY_FIND_SUCCESS);
			
		} catch (Exception e) {
			throw new NotFoundException(e.getMessage());
		}
		return ResponseEntity.ok().body(response);
	}
	
	//Add Category
	@PostMapping(value = "/admin")
	public ResponseEntity<ResponseDTO> addCategory(@Valid @RequestBody CategoryDTO categoryDTO){
		ResponseDTO responseDTO = new ResponseDTO();
		try {
			Category cateEntity = cateConvert.toEntity(categoryDTO);
			cateEntity = cateService.addCategory(cateEntity);
			responseDTO.setData(cateConvert.toDTO(cateEntity));
			responseDTO.setSuccessCode(SuccessCode.CATEGORY_ADD_SUCCESS);
		} catch (Exception e) {
			throw new AddException(e.getMessage());
		}
		return ResponseEntity.ok().body(responseDTO);
	}
	
	//Update Category
	@PutMapping(value = "/admin/update/{id}")
	public ResponseEntity<ResponseDTO> updateCategory(@Valid @RequestBody CategoryDTO cateDTO, @PathVariable(name = "id")Long id) {
		ResponseDTO respone = new ResponseDTO();
		try {
			Category cateEntity = cateService.updateCategory(cateConvert.toEntity(cateDTO), id);
			respone.setData(cateConvert.toDTO(cateEntity));
			respone.setSuccessCode(SuccessCode.CATEGORY_UPDATE_SUCCESS);
			
		} catch (Exception e) {
			throw new UpdateException(e.getMessage());
		}
		return ResponseEntity.ok().body(respone);
	}
}
