package com.tinnt.AssigmentRookie.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.tinnt.AssigmentRookie.entity.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Long>{
	
	@Query(value = "select * from books b where b.category_id = ?1", nativeQuery = true)
	Page<Book> getBookByCategory(long id, Pageable pageable);
	
	@Query(value = "select * from books b where b.book_name like %?1%", nativeQuery = true)
	Page<Book> getBookByName(String name, Pageable pageable);

	@Query(value = "select * from books", nativeQuery = true)
	Page<Book> findAllBook(Pageable page);

}
