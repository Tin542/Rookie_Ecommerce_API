package com.tinnt.AssigmentRookie.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tinnt.AssigmentRookie.converter.BookConverter;
import com.tinnt.AssigmentRookie.dto.BookDTO;
import com.tinnt.AssigmentRookie.entity.Book;
import com.tinnt.AssigmentRookie.entity.Category;
import com.tinnt.AssigmentRookie.exception.NotFoundException;
import com.tinnt.AssigmentRookie.repository.BookRepository;
import com.tinnt.AssigmentRookie.repository.CategoryRepository;
import com.tinnt.AssigmentRookie.service.BookService;

@Service
public class BookServiceImpl implements BookService{
	
	@Autowired
	private BookRepository bookRepository;

	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired
	private BookConverter bookConvert;

	@Override
	public List<Book> getAllBook() {
		return bookRepository.findAll();
	}

	@Override
	public Optional<Book> getBookByID(long id) {
		return bookRepository.findById(id);
	}

	@Override
	public List<Book> getBookByName(String name) {
		return bookRepository.getBookByName(name);
	}

	@Override
	public List<Book> getBookByCategory(long id) {
		return bookRepository.getBookByCategory(id);
	}

	@Override
	public Book saveBook(Book book) {
		return bookRepository.save(book);
	}

	@Override
	public Book updateBook(Book book) {
		return bookRepository.save(book);
	}

	@Override
	public void deleteBook(long id) {
		bookRepository.deleteBook(id);
	}
}
