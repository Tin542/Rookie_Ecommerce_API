package com.tinnt.AssigmentRookie.service.impl;

import java.util.ArrayList;
import java.util.List;

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
	public BookDTO saveBook(BookDTO book) {
		Category categoryEntity = categoryRepository.findOneByCategoryName(book.getCategoryName());
		if(categoryEntity == null) {
			throw new NotFoundException("Can not find category");
		}
		Book bookEntity = bookConvert.toEntity(book);
		bookEntity.setCategory(categoryEntity);
		bookEntity = bookRepository.save(bookEntity);
		return bookConvert.toDTO(bookEntity);
	}

	@Override
	public List<BookDTO> getAllBook() {
		List<Book> listBookEntity = bookRepository.findAll();
		List<BookDTO> listBookDTO = new ArrayList<BookDTO>();
		for (Book bookEntity : listBookEntity) {
			BookDTO bookDTO = bookConvert.toDTO(bookEntity);
			bookDTO.setId(bookEntity.getBook_id());
			listBookDTO.add(bookDTO);
		}
		return listBookDTO;
	}

	@Override
	public BookDTO getBookByID(long id) {
		Book bookEntity = bookRepository.findById(id).get();
		if(bookEntity == null) {
			throw new NotFoundException("Can not find book with id: "+id);
		}
		BookDTO bookDTO = bookConvert.toDTO(bookEntity);
		bookDTO.setId(bookEntity.getBook_id());
		return bookDTO;
	}

	@Override
	public BookDTO updateBook(BookDTO book, long id) {
		Book bookEntity = bookRepository.findById(id).get();
		if(bookEntity == null) {
			throw new NotFoundException("Can not find book with id: "+id);
		}
		Category categoryEntity = categoryRepository.findOneByCategoryName(book.getCategoryName());
		if(categoryEntity == null) {
			throw new NotFoundException("Can not find category");
		}
		Book newBookEntity = bookConvert.toEntity(book, bookEntity);
		newBookEntity.setCategory(categoryEntity);
		newBookEntity = bookRepository.save(newBookEntity);
		return bookConvert.toDTO(newBookEntity);
	}

	@Override
	public List<BookDTO> getBookByCategory(long id) {
		List<Book> listBookEntity = bookRepository.getBookByCategory(id);
		List<BookDTO> listBookDTO = new ArrayList<BookDTO>();
		for (Book bookEntity : listBookEntity) {
			BookDTO bookDTO = bookConvert.toDTO(bookEntity);
			bookDTO.setId(bookEntity.getBook_id());
			listBookDTO.add(bookDTO);
		}
		return listBookDTO;
	}

	@Override
	public List<BookDTO> getBookByName(String name) {
		List<Book> listBookEntity = bookRepository.getBookByName(name);
		List<BookDTO> listBookDTO = new ArrayList<BookDTO>();
		for (Book bookEntity : listBookEntity) {
			BookDTO bookDTO = bookConvert.toDTO(bookEntity);
			bookDTO.setId(bookEntity.getBook_id());
			listBookDTO.add(bookDTO);
		}
		return listBookDTO;
	}

}
