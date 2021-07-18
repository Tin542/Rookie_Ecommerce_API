package com.tinnt.AssigmentRookie.service.impl;

import java.util.List;
import java.util.Optional;

import com.tinnt.AssigmentRookie.entity.Rating;
import com.tinnt.AssigmentRookie.service.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.tinnt.AssigmentRookie.entity.Book;
import com.tinnt.AssigmentRookie.repository.BookRepository;
import com.tinnt.AssigmentRookie.service.BookService;

@Service
public class BookServiceImpl implements BookService{
	
	@Autowired
	private BookRepository bookRepository;

	@Autowired
	private RatingService rateService;

	@Override
	public Page<Book> getAllBook(Pageable pageable) {
		return bookRepository.findAllBook(pageable);
	}

	@Override
	public Optional<Book> getBookByID(long id) {
		return bookRepository.findById(id);
	}

	@Override
	public Page<Book> getBookByName(String name, Pageable pageable) {
		return bookRepository.getBookByName(name, pageable);
	}

	@Override
	public Page<Book> getBookByCategory(long id, Pageable pageable) {
		return bookRepository.getBookByCategory(id, pageable);
	}

	@Override
	public Book saveBook(Book book) {
		return bookRepository.save(book);
	}

	@Override
	public void updateBookRating(long bookid) {
		Book bookEntity  = bookRepository.findById(bookid).get();
		List<Rating> listRating = rateService.getRatingByBookID(bookid);
		float rate = 0;
		for ( Rating rateEntity : listRating  ) {
			rate += rateEntity.getRate();
		}
		rate = rate/listRating.size();
		bookEntity.setRate(rate);
		bookRepository.save(bookEntity);
	}
}
