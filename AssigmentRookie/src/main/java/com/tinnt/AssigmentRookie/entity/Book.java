package com.tinnt.AssigmentRookie.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "books")
public class Book {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "bookId")
	private long bookID;
	
	@Column(name = "bookName")
	private String book_name;
	
	@Column(name = "description")
	private String description;
	
	@Column(name = "price")
	private float price;
	
	@Column(name = "isDelete")
	private boolean isDelete;
	
	@Column(name = "author")
	private String author;
	
	@Column(name = "publisher")
	private String publisher;
	
	@Column(name = "publish_year")
	private int publish_year;
	
	@Column(name = "create_date")
	@CreatedDate
	private Date create_date;
	
	@Column(name = "update_date")
	@LastModifiedDate
	private Date update_date;
	
	@Column(name = "images")
	private String image;
	
	@Column(name = "quantity")
	private int quantity;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "categoryId")
	private Category category;
	
	@OneToMany(mappedBy = "book")
	private List<Rating> listRate = new ArrayList<>();

	public Book() {
		super();
		bookID = 0;
		book_name = null;
		description = null;
		price = 0;
		isDelete = false;
		author= null;
		publish_year = 0;
		publisher = null;
		quantity = 0;
		image = null;
		
	}

	public Book(long bookID, String book_name, String description, float price, boolean isDelete, String author,
			String publisher, int publish_year, String image, int quantity) {
		super();
		this.bookID = bookID;
		this.book_name = book_name;
		this.description = description;
		this.price = price;
		this.isDelete = isDelete;
		this.author = author;
		this.publisher = publisher;
		this.publish_year = publish_year;
		this.image = image;
		this.quantity = quantity;
	}

	public long getBookID() {
		return bookID;
	}

	public void setBookID(long bookID) {
		this.bookID = bookID;
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

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
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

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public List<Rating> getListRate() {
		return listRate;
	}

	public void setListRate(List<Rating> listRate) {
		this.listRate = listRate;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
}
