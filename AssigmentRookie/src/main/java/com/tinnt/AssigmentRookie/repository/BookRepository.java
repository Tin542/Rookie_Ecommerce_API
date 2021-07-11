package com.tinnt.AssigmentRookie.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.tinnt.AssigmentRookie.entity.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Long>{
	
	@Query(value = "select * from books b where b.category_id = ?1", nativeQuery = true)
	List<Book> getBookByCategory(long id);
	
	@Query(value = "select * from books b where b.book_name like %?1%", nativeQuery = true)
	List<Book> getBookByName(String name);
}
