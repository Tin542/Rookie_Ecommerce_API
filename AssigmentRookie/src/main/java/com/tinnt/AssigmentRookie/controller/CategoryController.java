package com.tinnt.AssigmentRookie.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tinnt.AssigmentRookie.dto.CategoryDTO;
import com.tinnt.AssigmentRookie.service.CategoryService;

@CrossOrigin
@RestController
@RequestMapping("/api/BookStore/Category")
public class CategoryController {
	
	@Autowired
	private CategoryService cateService;
	
	@GetMapping(value = "/get-all")
	public List<CategoryDTO> getAllCategory(){
		List<CategoryDTO> listCate = cateService.getAllCategory();
		return listCate;
	}
	
	@GetMapping(value = "/get-by-name/{name}")
	public CategoryDTO getCateByName(@PathVariable(name = "name") String name) {
		CategoryDTO cate = cateService.getCategoryByName(name);
		return cate;
	}
	
	@GetMapping(value = "/get-by-id/{id}")
	public CategoryDTO getCateById(@PathVariable(name = "id") Long id) {
		CategoryDTO cate = cateService.getCategoryByID(id);
		return cate;
	}
	
	@PostMapping(value = "/add-category")
	public CategoryDTO addCategory(@RequestBody CategoryDTO cateDTO){
		return cateService.addCategory(cateDTO);
	}
	
	@PutMapping(value = "/update-category/{id}")
	public CategoryDTO updateCategory(@RequestBody CategoryDTO cateDTO, @PathVariable(name = "id")Long id) {
		cateDTO.setCategoryID(id);
		return cateService.updateCategory(id, cateDTO);
	}
	
	@DeleteMapping(value = "/delete/{id}")
	public void delteCategory(@PathVariable(name = "id")Long id) {
		cateService.deleteCategory(id);
	}
}
