package com.tinnt.AssigmentRookie.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tinnt.AssigmentRookie.entity.Category;

public interface CategoryRepository extends JpaRepository<Category, Long>{
	Category findOneByCategoryName(String name);
}
