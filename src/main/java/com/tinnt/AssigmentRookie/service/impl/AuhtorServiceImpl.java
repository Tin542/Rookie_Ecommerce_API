package com.tinnt.AssigmentRookie.service.impl;

import com.tinnt.AssigmentRookie.entity.Author;
import com.tinnt.AssigmentRookie.entity.Publisher;
import com.tinnt.AssigmentRookie.exception.NotFoundException;
import com.tinnt.AssigmentRookie.repository.AuthorRepository;
import com.tinnt.AssigmentRookie.service.AuthorService;
import com.tinnt.AssigmentRookie.service.PublisherService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuhtorServiceImpl implements AuthorService {

    private static final Logger LOGGER = LoggerFactory.getLogger(AuthorService.class);

    private AuthorRepository authorRepository;

    @Autowired
    public AuhtorServiceImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public List<Author> getAllAuthor() {
        return authorRepository.findAll();
    }

    @Override
    public Optional<Author> getAuthorByName(String name) {
        Optional<Author> optional = authorRepository.findByName(name);
        if(optional.isEmpty()){
            LOGGER.info("Not exist Author with name: "+name);
            throw new NotFoundException("Cannot find Author with name: "+name);
        }
        return authorRepository.findByName(name);
    }

    @Override
    public Optional<Author> getAuthorByID(long id) {
        Optional<Author> optional = authorRepository.findById(id);
        if(optional.isEmpty()){
            LOGGER.info("Not exist Author with id: "+id);
            throw new NotFoundException("Cannot find Author with id: "+id);
        }
        return authorRepository.findById(id);
    }

    @Override
    public Author addAuthor(Author author) {
        Optional<Author> optional = authorRepository.findByName(author.getName());
        if(optional.isPresent()){
            LOGGER.info("Author is already existed !!");
            throw new NotFoundException("Author is already existed !!");
        }
        return authorRepository.save(author);
    }

    @Override
    public Author updateAuthor(Author author, long id) {
        Optional<Author> optional = authorRepository.findById(id);
        if(optional.isEmpty()){
            LOGGER.info("Not exist Author with id: "+id);
            throw new NotFoundException("Cannot find Author with id: "+id);
        }
        Author authorEntity = optional.get();
        authorEntity.setId(id);
        authorEntity.setCreate_date(authorEntity.getCreate_date());
        authorEntity.setDelete(author.isDelete());
        authorEntity.setName(author.getName());
        return authorRepository.save(authorEntity);
    }

    @Override
    public Page<Author> searchAuthor(String name, Pageable pageable) {
        Page<Author> optional = authorRepository.searchAuthor(name, pageable);
        if(optional.isEmpty()){
            LOGGER.info("Not exist Author with name: "+name);
            throw new NotFoundException("Not exist Author with name: "+name);
        }
        if(name.equals("")){
            return authorRepository.getAllAuthor(pageable);
        }
        return authorRepository.searchAuthor(name, pageable);
    }

    @Override
    public int deleteAuthor(long id) {
        getAuthorByID(id);
        return authorRepository.deleteAuthor(id);
    }
}
