package com.tinnt.AssigmentRookie.entity;

import java.io.Serializable;

import java.util.Date;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.*;

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
@Table(name = "books",
		indexes = {
				@Index(name = "book_idx_1", columnList = "bookId"),
				@Index(name = "book_idx_2", columnList = "bookName"),
				@Index(name = "book_idx_3", columnList = "categoryId")
		})
public class Book implements Serializable {
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
	
//	@Column(name = "author")
//	private String author;

//	@Column(name = "publisher")
//	private String publisher;
	
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

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "publisherId")
	private Publisher publisher;

	@OneToMany(mappedBy = "book")
	private List<Rating> listRate = new ArrayList<>();

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "book_author",
			joinColumns = @JoinColumn(name = "book_id"),
			inverseJoinColumns = @JoinColumn(name = "author_id"))
	private Set<Author> author = new HashSet<>();

}
