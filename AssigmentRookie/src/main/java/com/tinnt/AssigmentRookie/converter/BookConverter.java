package com.tinnt.AssigmentRookie.converter;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tinnt.AssigmentRookie.dto.BookDTO;
import com.tinnt.AssigmentRookie.entity.Book;

@Component
public class BookConverter {
	
	@Autowired
	private ModelMapper mapper;
	
	//convert to Entity
	public Book toEntity(BookDTO bookDTO) {
		Book bookEntity = mapper.map(bookDTO, Book.class);	
		return bookEntity;
	}
	
	//convert to DTO
	public BookDTO toDTO(Book bookEntity) {
		BookDTO bookDTO = mapper.map(bookEntity, BookDTO.class);
		return bookDTO;
	}
}
