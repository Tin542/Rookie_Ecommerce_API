package com.tinnt.AssigmentRookie.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
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
	
	@PostMapping(value = "/book")
	public BookDTO createBook(@RequestBody BookDTO book) {
		return bookService.save(book);
	}
}
