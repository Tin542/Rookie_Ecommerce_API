package com.tinnt.AssigmentRookie.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "order_detail")
public class OrderDetail {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "orderDetailId")
	private long orderDetailID;

	@NotNull
	@Column(name = "amount")
	private int amount;

	@NotNull
	@Column(name = "price")
	private float price;
	
	@OneToOne
	@JoinColumn(name = "orderId")
	private Order order;
	
	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "bookId")
    private Book books; 

}
