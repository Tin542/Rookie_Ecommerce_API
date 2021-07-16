package com.tinnt.AssigmentRookie.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.tinnt.AssigmentRookie.constans.ErrorCode;
import com.tinnt.AssigmentRookie.constans.SuccessCode;
import com.tinnt.AssigmentRookie.converter.BookConverter;
import com.tinnt.AssigmentRookie.dto.ResponseDTO;
import com.tinnt.AssigmentRookie.entity.Book;
import com.tinnt.AssigmentRookie.entity.Category;
import com.tinnt.AssigmentRookie.exception.AddException;
import com.tinnt.AssigmentRookie.exception.DeleteException;
import com.tinnt.AssigmentRookie.exception.NotFoundException;
import com.tinnt.AssigmentRookie.exception.UpdateException;
import com.tinnt.AssigmentRookie.service.CategoryService;
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

import com.tinnt.AssigmentRookie.dto.BookDTO;
import com.tinnt.AssigmentRookie.service.BookService;

import javax.validation.Valid;

@CrossOrigin
@RestController
@RequestMapping("/BookStore/book")
public class BookController {
	
	@Autowired
	private BookService bookService;

	@Autowired
	private CategoryService cateService;

	@Autowired
	private BookConverter bookConverter;

	@PostMapping
	public ResponseEntity<ResponseDTO> addBook(@Valid @RequestBody BookDTO bookDTO){
		ResponseDTO response = new ResponseDTO();
		try {
			Optional<Category> optional = cateService.getCategoryByName(bookDTO.getCategoryName());
			if(optional.isEmpty()){
				response.setErrorCode(ErrorCode.CATEGORY_FIND_ERROR);
			}else{
				Category cateEntity = optional.get();
				Book bookEntity = bookConverter.toEntity(bookDTO);
				bookEntity.setCategory(cateEntity);
				bookEntity = bookService.saveBook(bookEntity);
				response.setData(bookConverter.toDTO(bookEntity));
				response.setSuccessCode(SuccessCode.BOOK_ADD_SUCCESS);
			}

		}catch(Exception e){
			response.setErrorCode(ErrorCode.BOOK_ADD_ERROR);
			throw new AddException(e.getMessage());

		}
		return ResponseEntity.ok().body(response);
	}

	@PutMapping(value = "/update/{id}")
	public ResponseEntity<ResponseDTO> updateBook(@Valid @RequestBody BookDTO bookDTO, @PathVariable(name = "id") long id){
		ResponseDTO response = new ResponseDTO();
		try {
			Optional<Book> optional = bookService.getBookByID(id);
			if(optional.isEmpty()){
				response.setErrorCode(ErrorCode.BOOK_FIND_ERROR);
				throw new NotFoundException("Book not found !");
			}else{
				Book bookEntity = optional.get();
				Optional<Category> optionalCategory = cateService.getCategoryByName(bookDTO.getCategoryName());
				if(optionalCategory.isEmpty()){
					response.setErrorCode(ErrorCode.CATEGORY_FIND_ERROR);
				}else{
					Category cateEntity = optionalCategory.get();
					bookEntity = bookConverter.toEntity(bookDTO);
					bookEntity.setCategory(cateEntity);
					bookEntity.setBookID(id);
					bookEntity = bookService.saveBook(bookEntity);

					response.setData(bookConverter.toDTO(bookEntity));
					response.setSuccessCode(SuccessCode.BOOK_UPDATE_SUCCESS);
				}
			}
		}catch(Exception e){
			throw new UpdateException(e.getMessage());
		}
		return ResponseEntity.ok().body(response);
	}

	@GetMapping
	public ResponseEntity<ResponseDTO> getAllBook(){
		ResponseDTO response = new ResponseDTO();
		try {
			List<Book> listBookEntity = bookService.getAllBook();
			List<BookDTO> listBookDTO = new ArrayList<>();
			for(Book bookEntity : listBookEntity) {
				BookDTO bookDTO = bookConverter.toDTO(bookEntity);
				listBookDTO.add(bookDTO);
			}
			response.setData(listBookDTO);
			response.setSuccessCode(SuccessCode.BOOK_GET_SUCCESS);
		}catch (Exception e){
			response.setErrorCode(ErrorCode.BOOK_GET_ERROR);
		}
		return ResponseEntity.ok().body(response);
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<ResponseDTO> getBookById(@PathVariable(name = "id")long id){
		ResponseDTO response = new ResponseDTO();
		try {
			Optional<Book> optional = bookService.getBookByID(id);
			if(optional.isPresent()){
				Book bookEntity = optional.get();
				response.setData(bookConverter.toDTO(bookEntity));
				response.setSuccessCode(SuccessCode.BOOK_FIND_SUCCESS);
			}else{
				response.setErrorCode(ErrorCode.BOOK_FIND_ERROR);
			}

		}catch (Exception e){
			throw new NotFoundException(e.getMessage());
		}
		return ResponseEntity.ok().body(response);
	}

	@GetMapping(value = "/search/{name}")
	public ResponseEntity<ResponseDTO> searchBook(@PathVariable(name = "name")String name){
		ResponseDTO response = new ResponseDTO();
		try {
			List<Book> listBookEntity = bookService.getBookByName(name);
			if(listBookEntity.isEmpty()){
				response.setErrorCode(ErrorCode.BOOK_FIND_ERROR);
			}else{
				List<BookDTO> listBookDTO = new ArrayList<>();
				for(Book bookEntity : listBookEntity) {
					BookDTO bookDTO = bookConverter.toDTO(bookEntity);
					listBookDTO.add(bookDTO);
				}
				response.setData(listBookDTO);
				response.setSuccessCode(SuccessCode.BOOK_FIND_SUCCESS);
			}

		}catch(Exception e){
			throw new NotFoundException(e.getMessage());
		}
		return ResponseEntity.ok().body(response);
	}

	@GetMapping(value = "/search-by-cate/{cateId}")
	public ResponseEntity<ResponseDTO> searchBookByCateId(@PathVariable(name = "cateId")long id){
		ResponseDTO response = new ResponseDTO();
		try {
			List<Book> listBookEntity = bookService.getBookByCategory(id);
			if(listBookEntity.isEmpty()){
				response.setErrorCode(ErrorCode.BOOK_FIND_ERROR);
			}else{
				List<BookDTO> listBookDTO = new ArrayList<>();
				for (Book bookEntity : listBookEntity) {
					BookDTO bookDTO = bookConverter.toDTO(bookEntity);
					listBookDTO.add(bookDTO);
				}
				response.setData(listBookDTO);
				response.setSuccessCode(SuccessCode.BOOK_FIND_SUCCESS);
			}
		}catch(Exception e){
			throw new NotFoundException(e.getMessage());
		}
		return ResponseEntity.ok().body(response);
	}

	@PutMapping(value = "/delete/{id}")
	public ResponseEntity<ResponseDTO> deleteBook(@PathVariable(name = "id")long id){
		ResponseDTO response = new ResponseDTO();
		try {
			Optional<Book> optional = bookService.getBookByID(id);
			if(optional.isEmpty()){
				response.setErrorCode(ErrorCode.BOOK_FIND_ERROR);
			}else{
				Book bookEntity = optional.get();
				bookService.deleteBook(id);

				response.setData(bookConverter.toDTO(bookEntity));
				response.setSuccessCode(SuccessCode.BOOK_DELETE_SUCCESS);
			}
		}catch (Exception e){
			throw new DeleteException(e.getMessage());
		}
		return ResponseEntity.ok().body(response);
	}
}
