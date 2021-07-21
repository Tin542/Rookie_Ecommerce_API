package com.tinnt.AssigmentRookie.service.impl;

import java.util.List;
import java.util.Optional;

import com.tinnt.AssigmentRookie.constans.ErrorCode;
import com.tinnt.AssigmentRookie.entity.Category;
import com.tinnt.AssigmentRookie.entity.Rating;
import com.tinnt.AssigmentRookie.exception.AddException;
import com.tinnt.AssigmentRookie.exception.NotFoundException;
import com.tinnt.AssigmentRookie.exception.UpdateException;
import com.tinnt.AssigmentRookie.service.CategoryService;
import com.tinnt.AssigmentRookie.service.RatingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.tinnt.AssigmentRookie.entity.Book;
import com.tinnt.AssigmentRookie.repository.BookRepository;
import com.tinnt.AssigmentRookie.service.BookService;

@Service
public class BookServiceImpl implements BookService{

	private static final Logger LOGGER = LoggerFactory.getLogger(BookService.class);

	private BookRepository bookRepository;
	private RatingService rateService;
	private CategoryService cateService;

	@Autowired
	public BookServiceImpl(BookRepository bookRepository, RatingService rateService, CategoryService cateService) {
		this.bookRepository = bookRepository;
		this.rateService = rateService;
		this.cateService = cateService;
	}

	@Override
	public Page<Book> getAllBook(Pageable pageable) throws NotFoundException{
		return bookRepository.findAllBook(pageable);
	}

	@Override
	public Optional<Book> getBookByID(long id)throws NotFoundException {
		Optional<Book> optional = bookRepository.findById(id);
		if(optional.isEmpty()){
			LOGGER.info("Not exist book with id: "+id);
			throw new NotFoundException(ErrorCode.BOOK_FIND_ERROR);
		}
		return bookRepository.findById(id);
	}

	@Override
	public Page<Book> getBookByName(String name, Pageable pageable)throws NotFoundException {
		Page<Book> optional = bookRepository.getBookByName(name, pageable);
		if(optional.isEmpty()){
			LOGGER.info("Not exist book with name: "+name);
			throw new NotFoundException(ErrorCode.BOOK_FIND_ERROR);
		}
		return bookRepository.getBookByName(name, pageable);
	}

	@Override
	public Page<Book> getBookByCategory(long id, Pageable pageable)throws NotFoundException {
		Page<Book> optional = bookRepository.getBookByCategory(id, pageable);
		if(optional.isEmpty()){
			LOGGER.info("No book exist in category !");
			throw new NotFoundException(ErrorCode.BOOK_FIND_ERROR);
		}
		return bookRepository.getBookByCategory(id, pageable);
	}

	@Override
	public Book saveBook(Book book)throws AddException {
		return bookRepository.save(book);
	}

	@Override
	public Book updateBook(Book book, long id)throws UpdateException {
		Optional<Book> optionalBook = bookRepository.findById(id);
		Optional<Category> optionalCategory = cateService.getCategoryByName(book.getCategory().getCategoryName());

		if (optionalCategory.isEmpty()){
			LOGGER.info("Cannot found category with name");
			throw new NotFoundException(ErrorCode.CATEGORY_FIND_ERROR);
		}
		if(optionalBook.isEmpty()){
			LOGGER.info("Not exist book with id: "+id);
			throw new NotFoundException(ErrorCode.BOOK_FIND_ERROR);
		}

		Category cateEntity = optionalCategory.get();
		Book bookEntity = optionalBook.get();

		bookEntity.setBookID(id);
		bookEntity.setBook_name(book.getBook_name());
		bookEntity.setCategory(cateEntity);
		bookEntity.setAuthor(book.getAuthor());
		bookEntity.setDelete(book.isDelete());
		bookEntity.setRate(book.getRate());
		bookEntity.setImage(book.getImage());
		bookEntity.setPrice(book.getPrice());
		bookEntity.setDescription(book.getDescription());
		bookEntity.setPublish_year(book.getPublish_year());
		bookEntity.setPublisher(book.getPublisher());
		bookEntity.setQuantity(book.getQuantity());

		return bookRepository.save(bookEntity);
	}

	@Override
	public void updateBookRating(long bookid)throws UpdateException {
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

	@Override
	public Page<Book> searchBook(String keyword, Pageable pageable) throws NotFoundException{
		Page<Book> optional = bookRepository.searchBook(keyword, pageable);
		if(optional.isEmpty()){
			LOGGER.info("Books not found !");
			throw new NotFoundException(ErrorCode.BOOK_FIND_ERROR);
		}
		return bookRepository.searchBook(keyword, pageable);
	}
}
