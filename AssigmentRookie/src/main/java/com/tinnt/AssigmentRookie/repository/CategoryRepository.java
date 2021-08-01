package com.tinnt.AssigmentRookie.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.tinnt.AssigmentRookie.entity.Category;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Long>{

	@Query(value = "select * from category c where c.category_name = ?1", nativeQuery = true)
	Optional<Category> findByName(String name);

	@Query(value = "select * from category c where c.category_name like %?1%", nativeQuery = true)
	Page<Category> searchCategory(String name, Pageable pageable);

	@Query(value = "select * from category c ", nativeQuery = true)
	Page<Category> getAllCategory(Pageable pageable);

	@Transactional
	@Modifying
	@Query(value = "update category set is_delete = true where category_id = ?1", nativeQuery = true)
	int deleteCategory (long id);

}
