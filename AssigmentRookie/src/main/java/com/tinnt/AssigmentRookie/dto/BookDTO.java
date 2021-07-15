package com.tinnt.AssigmentRookie.dto;

public class BookDTO {
	private long id;
	private String book_name;
	private String bookDescription;
	private float bookPrice;
	private String categoryName;
	private boolean isDelete;
	private String author;
	private String publisher;
	private int publish_year;
	private String image;
	private int quantity;
	
	public BookDTO() {
		super();
		id = 0;
		book_name = null;
		bookDescription = null;
		bookPrice = 0;
		categoryName = null;
		isDelete = false;
		author = null;
		publish_year = 0;
		publisher = null;
		image = null;
		quantity = 0;
	}

	public BookDTO(long id, String book_name, String bookDescription, float bookPrice, String categoryName,
			boolean isDelete, String author, String publisher, int publish_year, String image, int quantity) {
		super();
		this.id = id;
		this.book_name = book_name;
		this.bookDescription = bookDescription;
		this.bookPrice = bookPrice;
		this.categoryName = categoryName;
		this.isDelete = isDelete;
		this.author = author;
		this.publisher = publisher;
		this.publish_year = publish_year;
		this.image = image;
		this.quantity = quantity;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getBook_name() {
		return book_name;
	}

	public void setBook_name(String book_name) {
		this.book_name = book_name;
	}

	public String getBookDescription() {
		return bookDescription;
	}

	public void setBookDescription(String bookDescription) {
		this.bookDescription = bookDescription;
	}

	public float getBookPrice() {
		return bookPrice;
	}

	public void setBookPrice(float bookPrice) {
		this.bookPrice = bookPrice;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public boolean isDelete() {
		return isDelete;
	}

	public void setDelete(boolean isDelete) {
		this.isDelete = isDelete;
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

	public int getPublish_year() {
		return publish_year;
	}

	public void setPublish_year(int publish_year) {
		this.publish_year = publish_year;
	}
	
}
