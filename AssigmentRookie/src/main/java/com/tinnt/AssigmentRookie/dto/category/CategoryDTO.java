package com.tinnt.AssigmentRookie.dto.category;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import java.sql.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CategoryDTO {

	private long categoryID;

	@NotBlank
	@Length(min = 2, max = 50, message = "Category name required 2-50 chars !")
	private String categoryName;

	private Date createDate;
	private Date updateDate;

	private boolean isDelete;

	
}
