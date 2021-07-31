package com.tinnt.AssigmentRookie.converter;

import com.tinnt.AssigmentRookie.entity.Author;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tinnt.AssigmentRookie.dto.BookDTO;
import com.tinnt.AssigmentRookie.entity.Book;

import java.util.HashSet;
import java.util.Set;

@Component
public class BookConverter {

	private ModelMapper mapper;

	@Autowired
	public BookConverter(ModelMapper mapper) {
		this.mapper = mapper;
	}

	//convert to Entity
	public Book toEntity(BookDTO bookDTO) {
		Book bookEntity = mapper.map(bookDTO, Book.class);
		return bookEntity;
	}
	
	//convert to DTO
	public BookDTO toDTO(Book bookEntity) {
		BookDTO bookDTO = mapper.map(bookEntity, BookDTO.class);
		Set<Author> author = bookEntity.getAuthor();
		Set<String> name = new HashSet<>();
		for ( Author authorName : author) {
			name.add(authorName.getName());
		}
		bookDTO.setAuthorName(name);
		return bookDTO;
	}
}
