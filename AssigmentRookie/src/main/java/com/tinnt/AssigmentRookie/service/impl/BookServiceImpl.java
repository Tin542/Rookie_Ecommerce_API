package com.tinnt.AssigmentRookie.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tinnt.AssigmentRookie.converter.BookConverter;
import com.tinnt.AssigmentRookie.dto.BookDTO;
import com.tinnt.AssigmentRookie.entity.Book;
import com.tinnt.AssigmentRookie.entity.Category;
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
	public BookDTO save(BookDTO book) {
		Category categoryEntity = categoryRepository.findOneByCategoryName(book.getCategoryName());
		Book bookEntity = bookConvert.toEntity(book);
		bookEntity.setCategory(categoryEntity);
		bookEntity = bookRepository.save(bookEntity);
		return bookConvert.toDTO(bookEntity);
	}
	
}
