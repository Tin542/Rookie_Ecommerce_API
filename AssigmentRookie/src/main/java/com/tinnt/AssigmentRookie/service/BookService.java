package com.tinnt.AssigmentRookie.service;

import java.util.List;
import java.util.Optional;

import com.tinnt.AssigmentRookie.dto.BookDTO;
import com.tinnt.AssigmentRookie.entity.Book;

public interface BookService {
	
	public List<Book> getAllBook();
	
	public Optional<Book> getBookByID(long id);
	
	public List<Book> getBookByName(String name);
	
	public List<Book> getBookByCategory(long id);
	
	public Book saveBook(Book book);
	
}
