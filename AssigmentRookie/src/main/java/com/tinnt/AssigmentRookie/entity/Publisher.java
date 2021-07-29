package com.tinnt.AssigmentRookie.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@Table(name = "publisher",
        indexes = {
                @Index(name = "publisher_idx", columnList = "id, publisherName")
        })
public class Publisher implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "publisherName")
    private String name;

    @Column(name = "isDelete")
    private boolean isDelete;

    @Column(name = "create_date")
    @CreatedDate
    private Date create_date;

    @Column(name = "update_date")
    @LastModifiedDate
    private Date update_date;

    @OneToMany(mappedBy = "publisher",fetch = FetchType.LAZY)
    private List<Book> listBook = new ArrayList<>();
}
