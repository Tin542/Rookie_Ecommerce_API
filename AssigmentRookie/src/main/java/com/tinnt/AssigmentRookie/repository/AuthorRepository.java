package com.tinnt.AssigmentRookie.repository;

import com.tinnt.AssigmentRookie.entity.Author;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface AuthorRepository extends JpaRepository<Author, Long> {
    @Query(value = "select * from author a where a.author_name = ?1", nativeQuery = true)
    Optional<Author> findByName(String name);

    @Query(value = "select * from author a where a.author_name like %?1%", nativeQuery = true)
    Page<Author> searchAuthor(String name, Pageable pageable);

    @Query(value = "select * from author ", nativeQuery = true)
    Page<Author> getAllAuthor(Pageable pageable);
}
