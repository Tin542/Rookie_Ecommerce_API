package com.tinnt.AssigmentRookie.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BookDTO {

	private long id;

	@NotBlank
	@Length(min = 2, max = 50, message = "Book Name required 2-50 chars !")
	private String book_name;

	@NotBlank
	@Length(min = 2, message = "Description required 2-255 chars !")
	private String bookDescription;

	private float bookPrice;

	private String categoryName;

	private boolean isDelete;

	@NotBlank
	@Length(min = 2, max = 100, message = "author required 2-100 chars !")
	private String author;

	@NotBlank
	@Length(min = 2, max = 50, message = "publisher required 2-50 chars !")
	private String publisher;

	@NotNull(message = "Please enter publish year !")
	private int publish_year;

	@NotBlank(message = "Should be input link image !!!")
	private String image;

	@NotNull
	private int quantity;

}
