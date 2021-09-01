package com.tinnt.AssigmentRookie.entity;

import com.tinnt.AssigmentRookie.constans.EStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "status")
public class StatusOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "status_id")
    private long statusId;

    @Enumerated(EnumType.STRING)
    @Column(name = "status_name")
    @NaturalId
    private EStatus name;

}
