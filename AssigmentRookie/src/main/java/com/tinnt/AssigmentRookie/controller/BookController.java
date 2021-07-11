package com.tinnt.AssigmentRookie.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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

@CrossOrigin
@RestController
@RequestMapping("/api/BookStore")
public class BookController {
	
	@Autowired
	private BookService bookService;
	
	@GetMapping(value = "/book")
	public List<BookDTO> getAllBook(){
		List<BookDTO> listBooks = bookService.getAllBook();
		return listBooks;
	}
	
	@GetMapping(value = "/book/{id}")
	public BookDTO getBookByID(@PathVariable(name = "id") Long id) {
		BookDTO book = bookService.getBookByID(id);
		return book;
	}
	
	@PostMapping(value = "/book")
	public BookDTO createBook(@RequestBody BookDTO book) {
		return bookService.saveBook(book);
	}
	
	@PutMapping(value = "/book/{id}")
	public BookDTO updateBook(@RequestBody BookDTO book, @PathVariable(name = "id") Long id) {
		book.setId(id);
		return bookService.updateBook(book, id);
	}
	
	@GetMapping(value = "/book/category/{categoryID}")
	public List<BookDTO> getBookByCategoryName(@PathVariable(name = "categoryID") Long id){
		List<BookDTO> listBooks = bookService.getBookByCategory(id);
		return listBooks;
	}
	
	@GetMapping(value = "/book/search/{bookName}")
	public List<BookDTO> getBookByCategoryName(@PathVariable(name = "bookName") String name){
		List<BookDTO> listBooks = bookService.getBookByName(name);
		return listBooks;
	}
	
}
