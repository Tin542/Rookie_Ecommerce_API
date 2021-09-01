package com.tinnt.AssigmentRookie.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "orders",
		indexes = {
				@Index(name = "order_idx", columnList = "orderId")
		})
@EntityListeners(AuditingEntityListener.class)
public class Order implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "orderId")
	private long orderID;

	@NotBlank
	@Column(name = "address")
	private String address;

	@NotNull
	@Column(name = "phone")
	private String phone;
	
	@Column(name = "total_price")
	private float totalPrice;
	
	@Column(name = "order_date")
	@CreatedDate
	private Date orderDate;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "account_id")
	private Account account;
	
	@OneToOne(mappedBy = "order")
	private OrderDetail details;

	@Column(name = "status")
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "order_status",
			joinColumns = @JoinColumn(name = "order_id"),
			inverseJoinColumns = @JoinColumn(name = "status_id"))
	private Set<StatusOrder> status;


}
