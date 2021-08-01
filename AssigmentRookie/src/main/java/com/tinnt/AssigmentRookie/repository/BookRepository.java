package com.tinnt.AssigmentRookie.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.tinnt.AssigmentRookie.entity.Book;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface BookRepository extends JpaRepository<Book, Long>{
	
	@Query(value = "select * from books b where b.category_id = ?1", nativeQuery = true)
	Page<Book> getBookByCategory(long id, Pageable pageable);
	
	@Query(value = "select * from books b where b.book_name = ?1", nativeQuery = true)
	Optional<Book> getBookByName(String name);

	@Query(value = "select * from books", nativeQuery = true)
	Page<Book> findAllBook(Pageable page);

	@Query(value = "SELECT * FROM books p WHERE p.book_name ILIKE %?1%", nativeQuery = true)
	Page<Book> searchBook(String keyword, Pageable pageable);

	@Transactional
	@Modifying
	@Query(value = "update books set is_delete = true where book_id = ?1", nativeQuery = true)
	int deleteBook (long id);


}
