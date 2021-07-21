package com.tinnt.AssigmentRookie.service;

import java.util.List;
import java.util.Optional;

import com.tinnt.AssigmentRookie.dto.BookDTO;
import com.tinnt.AssigmentRookie.entity.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BookService {
	
	public Page<Book> getAllBook(Pageable pageable);
	
	public Optional<Book> getBookByID(long id);
	
	public Page<Book> getBookByName(String name, Pageable pageable);
	
	public Page<Book> getBookByCategory(long cateID, Pageable pageable);
	
	public Book saveBook(Book book);

	public Book updateBook(Book book, long id);

	public void updateBookRating(long bookID);

	public Page<Book> searchBook(String keyword, Pageable pageable);
	
}
