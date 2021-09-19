package com.tinnt.AssigmentRookie.dto.book;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.sql.Date;
import java.util.Set;

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

	private Set<String> authorName;

	private String publisherName;

	@NotNull
	private int publish_year;

	@NotBlank
	private String image;

	@NotNull
	private int quantity;

	private float rate;

	Date createDate;
	Date updateDate;

}
