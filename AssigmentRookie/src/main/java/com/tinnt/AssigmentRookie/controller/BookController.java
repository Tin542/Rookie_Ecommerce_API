package com.tinnt.AssigmentRookie.controller;

import java.util.ArrayList;
import java.util.HashMap;
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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
	public ResponseEntity<ResponseDTO> getAllBook(@RequestParam(defaultValue = "0") int page,
												  @RequestParam(defaultValue = "3") int size){
		ResponseDTO response = new ResponseDTO();
		try {
			List<Book> listBookEntity = new ArrayList<>();
			Pageable paging = PageRequest.of(page, size);
			Page<Book> pageBook = bookService.getAllBook(paging);

			//retrieve the List of items in the page.
			listBookEntity = pageBook.getContent();

			List<BookDTO> listBookDTO = new ArrayList<>();
			for(Book bookEntity : listBookEntity) {
				BookDTO bookDTO = bookConverter.toDTO(bookEntity);
				listBookDTO.add(bookDTO);
			}

			HashMap<String, Object> map = new HashMap<>();
			map.put("Books",listBookDTO);
			map.put("currentPage", pageBook.getNumber());//current Page.
			map.put("totalItems", pageBook.getTotalElements());//total items stored in database.
			map.put("totalPages", pageBook.getTotalPages());//number of total pages.

			response.setData(map);
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
	public ResponseEntity<ResponseDTO> searchBook(@PathVariable(name = "name")String name,
												  @RequestParam(defaultValue = "0") int page,
												  @RequestParam(defaultValue = "3") int size){//number item of page
		ResponseDTO response = new ResponseDTO();
		try {
			List<Book> listBookEntity = new ArrayList<>();
			Pageable paging = PageRequest.of(page, size);
			Page<Book> pageBook = bookService.getBookByName(name,paging);

			//retrieve the List of items in the page.
			listBookEntity = pageBook.getContent();
			List<BookDTO> listBookDTO = new ArrayList<>();
			for(Book bookEntity : listBookEntity) {
				BookDTO bookDTO = bookConverter.toDTO(bookEntity);
				listBookDTO.add(bookDTO);
			}
			HashMap<String, Object> map = new HashMap<>();
			map.put("Books",listBookDTO);
			map.put("currentPage", pageBook.getNumber());//current Page.
			map.put("totalItems", pageBook.getTotalElements());//total items stored in database.
			map.put("totalPages", pageBook.getTotalPages());//number of total pages.

			response.setData(map);
			response.setSuccessCode(SuccessCode.BOOK_GET_SUCCESS);

		}catch(Exception e){
			throw new NotFoundException(e.getMessage());
		}
		return ResponseEntity.ok().body(response);
	}

	@GetMapping(value = "/search-by-cate/{cateId}")
	public ResponseEntity<ResponseDTO> searchBookByCateId(@PathVariable(name = "cateId")long id,
														  @RequestParam(defaultValue = "0")int page,
														  @RequestParam(defaultValue = "3")int size){
		ResponseDTO response = new ResponseDTO();
		try {
			List<Book> listBookEntity = new ArrayList<>();
			Pageable paging = PageRequest.of(page, size);
			Page<Book> pageBook = bookService.getBookByCategory(id, paging);

			////retrieve the List of items in the page.
			listBookEntity = pageBook.getContent();
			if(listBookEntity.isEmpty()){
				response.setErrorCode(ErrorCode.BOOK_FIND_ERROR);
			}else{
				List<BookDTO> listBookDTO = new ArrayList<>();
				for (Book bookEntity : listBookEntity) {
					BookDTO bookDTO = bookConverter.toDTO(bookEntity);
					listBookDTO.add(bookDTO);
				}

				HashMap<String, Object> map = new HashMap<>();
				map.put("Books",listBookDTO);
				map.put("currentPage", pageBook.getNumber());//current Page.
				map.put("totalItems", pageBook.getTotalElements());//total items stored in database.
				map.put("totalPages", pageBook.getTotalPages());//number of total pages.

				response.setData(map);
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
				bookEntity.setDelete(true);
				bookService.saveBook(bookEntity);

				response.setData(bookConverter.toDTO(bookEntity));
				response.setSuccessCode(SuccessCode.BOOK_DELETE_SUCCESS);
			}
		}catch (Exception e){
			throw new DeleteException(e.getMessage());
		}
		return ResponseEntity.ok().body(response);
	}

}
