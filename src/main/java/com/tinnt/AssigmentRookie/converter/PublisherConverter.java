package com.tinnt.AssigmentRookie.converter;

import com.tinnt.AssigmentRookie.dto.PublisherDTO;
import com.tinnt.AssigmentRookie.entity.Publisher;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PublisherConverter {
    @Autowired
    private ModelMapper mapper;

    //convert to Entity
    public Publisher toEntity(PublisherDTO publisherDTO) {
        Publisher publisherEntity = mapper.map(publisherDTO, Publisher.class);
        return publisherEntity;
    }

    //convert to DTO
    public PublisherDTO toDTO(Publisher publisherEntity) {
        PublisherDTO publisherDTO = mapper.map(publisherEntity, PublisherDTO.class);
        return publisherDTO;
    }
}
