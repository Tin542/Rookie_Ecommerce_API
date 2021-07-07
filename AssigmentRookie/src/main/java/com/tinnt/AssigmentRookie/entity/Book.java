package com.tinnt.AssigmentRookie.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "books")
public class Book {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long book_id;
	
	@Column(name = "book_name")
	private String book_name;
	
	@Column(name = "description")
	private String description;
	
	@Column(name = "category_id")
	private String category_id;
	
	@Column(name = "price")
	private float price;
	
	@Column(name = "status")
	private String status;
	
	@Column(name = "author")
	private String author;
	
	@Column(name = "publisher")
	private String publisher;
	
	@Column(name = "book_language")
	private String book_language;
	
	@Column(name = "publish_year")
	private int publish_year;
	
	@Column(name = "create_date")
	private Date create_date;
	
	@Column(name = "update_date")
	private Date update_date;
	
	@Column(name = "images")
	private String image;

	public Book() {
		super();
	}

	public Book(long book_id, String book_name, String description, String category_id, float price, String status,
			String author, String publisher, String book_language, int publish_year, Date create_date, Date update_date,
			String image) {
		super();
		this.book_id = book_id;
		this.book_name = book_name;
		this.description = description;
		this.category_id = category_id;
		this.price = price;
		this.status = status;
		this.author = author;
		this.publisher = publisher;
		this.book_language = book_language;
		this.publish_year = publish_year;
		this.create_date = create_date;
		this.update_date = update_date;
		this.image = image;
	}

	public long getBook_id() {
		return book_id;
	}

	public void setBook_id(long book_id) {
		this.book_id = book_id;
	}

	public String getBook_name() {
		return book_name;
	}

	public void setBook_name(String book_name) {
		this.book_name = book_name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCategory_id() {
		return category_id;
	}

	public void setCategory_id(String category_id) {
		this.category_id = category_id;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
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

	public int getPublish_year() {
		return publish_year;
	}

	public void setPublish_year(int publish_year) {
		this.publish_year = publish_year;
	}

	public Date getCreate_date() {
		return create_date;
	}

	public void setCreate_date(Date create_date) {
		this.create_date = create_date;
	}

	public Date getUpdate_date() {
		return update_date;
	}

	public void setUpdate_date(Date update_date) {
		this.update_date = update_date;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}
	
	
}
