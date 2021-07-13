package com.tinnt.AssigmentRookie.service;

import java.util.List;

import com.tinnt.AssigmentRookie.dto.BookDTO;

public interface BookService {
	
	public List<BookDTO> getAllBook();
	
	public BookDTO getBookByID(long id);
	
	public List<BookDTO> getBookByName(String name);
	
	public List<BookDTO> getBookByCategory(long id);
	
	public BookDTO saveBook(BookDTO book);
	
	public BookDTO updateBook(BookDTO book, long id);
	
	public BookDTO deleteBook(long id);
	
}
