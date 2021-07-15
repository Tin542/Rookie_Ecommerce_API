package com.tinnt.AssigmentRookie.dto;

public class CategoryDTO {
	private long categoryID;
	private String categoryName;
	
	public CategoryDTO() {
		super();
		categoryID = 0;
		categoryName = null;
	}

	public CategoryDTO(long categoryID, String categoryName) {
		super();
		this.categoryID = categoryID;
		this.categoryName = categoryName;
	}

	public long getCategoryID() {
		return categoryID;
	}

	public void setCategoryID(long categoryID) {
		this.categoryID = categoryID;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	
}
