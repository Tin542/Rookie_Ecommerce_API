package com.tinnt.AssigmentRookie.dto;

public class BookDTO {
	private long id;
	private String bookName;
	private String bookDescription;
	private String bookPrice;
	private String categoryName;
	private String status;
	private String author;
	private String publisher;
	private String book_language;
	private String publish_year;
	
	
	
	public BookDTO() {
		super();
	}

	
	public BookDTO(long id, String bookName, String bookDescription, String bookPrice, String categoryName,
			String status, String author, String publisher, String book_language, String publish_year) {
		super();
		this.id = id;
		this.bookName = bookName;
		this.bookDescription = bookDescription;
		this.bookPrice = bookPrice;
		this.categoryName = categoryName;
		this.status = status;
		this.author = author;
		this.publisher = publisher;
		this.book_language = book_language;
		this.publish_year = publish_year;
	}


	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getBookName() {
		return bookName;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName;
	}


	public String getBookDescription() {
		return bookDescription;
	}

	public void setBookDescription(String bookDescription) {
		this.bookDescription = bookDescription;
	}

	public String getBookPrice() {
		return bookPrice;
	}

	public void setBookPrice(String bookPrice) {
		this.bookPrice = bookPrice;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public String getBook_language() {
		return book_language;
	}

	public void setBook_language(String book_language) {
		this.book_language = book_language;
	}

	public String getPublish_year() {
		return publish_year;
	}

	public void setPublish_year(String publish_year) {
		this.publish_year = publish_year;
	}
	
	
}
