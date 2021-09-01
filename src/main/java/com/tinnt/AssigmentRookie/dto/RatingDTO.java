package com.tinnt.AssigmentRookie.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RatingDTO {

    private long rateID;
    private long bookID;
    private String username;
    private String feedback;
    private float rate;

}
