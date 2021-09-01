package com.tinnt.AssigmentRookie.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import com.tinnt.AssigmentRookie.constans.ErrorCode;
import com.tinnt.AssigmentRookie.entity.*;
import com.tinnt.AssigmentRookie.exception.AddException;
import com.tinnt.AssigmentRookie.exception.NotFoundException;
import com.tinnt.AssigmentRookie.exception.UpdateException;
import com.tinnt.AssigmentRookie.service.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.tinnt.AssigmentRookie.repository.BookRepository;

@Service
public class BookServiceImpl implements BookService{

	private static final Logger LOGGER = LoggerFactory.getLogger(BookService.class);

	private BookRepository bookRepository;
	private RatingService rateService;
	private CategoryService cateService;
	private PublisherService publisherService;
	private AuthorService authorService;

	@Autowired
	public BookServiceImpl(BookRepository bookRepository, RatingService rateService, CategoryService cateService, PublisherService publisherService, AuthorService authorService) {
		this.bookRepository = bookRepository;
		this.rateService = rateService;
		this.cateService = cateService;
		this.publisherService = publisherService;
		this.authorService = authorService;
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
			throw new NotFoundException("Not exist book with id: "+id);
		}
		return bookRepository.findById(id);
	}

	@Override
	public Optional<Book> getBookByName(String name)throws NotFoundException {
		Optional<Book> optionalBook = bookRepository.getBookByName(name);
		if(optionalBook.isEmpty()){
			LOGGER.info("Not exist book with name: "+name);
			throw new NotFoundException("Not exist book with name: "+name);
		}
		return bookRepository.getBookByName(name);
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
		Optional<Book> optional = bookRepository.getBookByName(book.getBook_name());
		Optional<Category> optionalCategory = cateService.getCategoryByName(book.getCategory().getCategoryName());
		Optional<Publisher> optionalPublisher = publisherService.getPublisherByName(book.getPublisher().getName());

		if(optional.isPresent()){
			LOGGER.info("Book "+book.getBook_name()+" already existed !!");
			throw new NotFoundException("Book "+book.getBook_name()+" already existed !!");
		}

		Category cateEntity = optionalCategory.get();
		Publisher publisherEntity =  optionalPublisher.get();
		Book bookEntity = new Book();

		bookEntity.setBook_name(book.getBook_name());
		bookEntity.setCategory(cateEntity);
		bookEntity.setRate(book.getRate());
		bookEntity.setImage(book.getImage());
		bookEntity.setPrice(book.getPrice());
		bookEntity.setDescription(book.getDescription());
		bookEntity.setPublish_year(book.getPublish_year());
		bookEntity.setPublisher(publisherEntity);
		bookEntity.setQuantity(book.getQuantity());
		bookEntity.setAuthor(book.getAuthor());
		return bookRepository.save(bookEntity);
	}

	@Override
	public Book updateBook(Book book, long id)throws UpdateException {
		Optional<Book> optionalBook = getBookByID(id);
		Optional<Category> optionalCategory = cateService.getCategoryByName(book.getCategory().getCategoryName());
		Optional<Publisher> optionalPublisher = publisherService.getPublisherByName(book.getPublisher().getName());

		Category cateEntity = optionalCategory.get();
		Publisher publisherEntity =  optionalPublisher.get();
		Book bookEntity = optionalBook.get();

		bookEntity.setBookID(id);
		bookEntity.setBook_name(book.getBook_name());
		bookEntity.setCategory(cateEntity);
		bookEntity.setDelete(book.isDelete());
		bookEntity.setRate(book.getRate());
		bookEntity.setImage(book.getImage());
		bookEntity.setPrice(book.getPrice());
		bookEntity.setDescription(book.getDescription());
		bookEntity.setPublish_year(book.getPublish_year());
		bookEntity.setPublisher(publisherEntity);
		bookEntity.setQuantity(book.getQuantity());
		bookEntity.setAuthor(book.getAuthor());

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

	@Override
	public int deleteBook(long id) {
		getBookByID(id);

		return bookRepository.deleteBook(id);
	}
}
