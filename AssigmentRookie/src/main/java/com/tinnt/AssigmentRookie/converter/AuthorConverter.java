package com.tinnt.AssigmentRookie.converter;

import com.tinnt.AssigmentRookie.dto.author.AuthorDTO;
import com.tinnt.AssigmentRookie.entity.Author;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AuthorConverter {

    private ModelMapper mapper;

    @Autowired
    public AuthorConverter(ModelMapper mapper) {
        this.mapper = mapper;
    }

    //convert to Entity
    public Author toEntity(AuthorDTO authorDTO) {
        Author authorEntity = mapper.map(authorDTO, Author.class);
        return authorEntity;
    }

    //convert to DTO
    public AuthorDTO toDTO(Author authorEntity) {
        AuthorDTO authorDTO = mapper.map(authorEntity, AuthorDTO.class);
        return authorDTO;
    }
}
