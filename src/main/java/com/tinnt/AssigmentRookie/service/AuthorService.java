package com.tinnt.AssigmentRookie.service;

import com.tinnt.AssigmentRookie.entity.Author;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface AuthorService {
    public List<Author> getAllAuthor();

    public Optional<Author> getAuthorByName(String name);

    public Optional<Author> getAuthorByID(long id);

    public Author addAuthor(Author author);

    public Author updateAuthor(Author author, long id);

    public Page<Author> searchAuthor(String name, Pageable pageable);

    public int deleteAuthor(long id);
}
