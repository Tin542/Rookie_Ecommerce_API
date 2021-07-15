package com.tinnt.AssigmentRookie.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
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

}
