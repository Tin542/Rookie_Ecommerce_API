package com.tinnt.AssigmentRookie.converter;

import com.tinnt.AssigmentRookie.dto.rate.RatingDTO;
import com.tinnt.AssigmentRookie.entity.Rating;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RatingConverter {

    @Autowired
    private ModelMapper mapper;

    //convert to Entity
    public Rating toEntity(RatingDTO rateDTO) {
        Rating rateEntity = mapper.map(rateDTO, Rating.class);
        return rateEntity;
    }

    //convert to DTO
    public RatingDTO toDTO(Rating rateEntity) {
        RatingDTO rateDTO = mapper.map(rateEntity, RatingDTO.class);
        rateDTO.setUsername(rateEntity.getAccountRate().getUsername());
        return rateDTO;
    }
}
