package com.tinnt.AssigmentRookie.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@Table(name = "category",
		indexes = {
		@Index(name = "cate_idx", columnList = "categoryId, categoryName")
		})
public class Category implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "categoryId")
	private long categoryID;
	
	@Column(name = "categoryName")
	@NotBlank
	@Length(min = 2, max = 50, message = "Category name required 2-50 chars !")
	private String categoryName;

	@Column(name = "create_date")
	@CreatedDate
	private Date create_date;

	@Column(name = "update_date")
	@LastModifiedDate
	private Date update_date;

	@Column(name = "isDelete")
	private boolean isDelete;

	@OneToMany(mappedBy = "category",fetch = FetchType.LAZY)
	private List<Book> listBook = new ArrayList<>();

	public Category(long categoryID, String categoryName) {
		this.categoryID = categoryID;
		this.categoryName = categoryName;
	}
}
