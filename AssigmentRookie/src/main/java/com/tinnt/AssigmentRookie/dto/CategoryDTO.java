package com.tinnt.AssigmentRookie.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CategoryDTO {

	private long categoryID;

	@NotBlank(message = "Please enter category name !")
	private String categoryName;

	
}
