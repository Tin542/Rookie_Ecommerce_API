package com.tinnt.AssigmentRookie.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class HomeDTO {
    private long id;

    @NotBlank
    @Length(min = 2, max = 50, message = "Book Name required 2-50 chars !")
    private String book_name;

    private float bookPrice;

    @NotBlank
    private String image;

    private float rate;
}
