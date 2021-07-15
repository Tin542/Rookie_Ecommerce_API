package com.tinnt.AssigmentRookie.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "category")
public class Category {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "categoryId")
	private long categoryID;
	
	@Column(name = "categoryName")
	private String categoryName;

	@OneToMany(mappedBy = "category",fetch = FetchType.LAZY)
	private List<Book> listBook = new ArrayList<>();

	public Category(long categoryID, String categoryName) {
		this.categoryID = categoryID;
		this.categoryName = categoryName;
	}
}
