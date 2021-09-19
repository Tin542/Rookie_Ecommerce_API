package com.tinnt.AssigmentRookie.dto.publisher;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.sql.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PublisherDTO {
    private long publisherID;

    @NotBlank
    private String publisherName;

    private Date createDate;
    private Date updateDate;

    private boolean isDelete;
}
