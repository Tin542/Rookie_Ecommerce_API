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

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
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

	@Column(name = "rate")
	private float rate;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "categoryId")
	private Category category;
	
	@OneToMany(mappedBy = "book")
	private List<Rating> listRate = new ArrayList<>();

}
