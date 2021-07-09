package com.tinnt.AssigmentRookie.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tinnt.AssigmentRookie.entity.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Long>{
	
}
