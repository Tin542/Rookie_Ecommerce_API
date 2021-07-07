package com.tinnt.AssigmentRookie.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "orders")
public class Order {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "order_id")
	private long orderID;
	
	@Column(name = "address")
	private String address;
	
	@Column(name = "phone")
	private String phone;
	
	@Column(name = "total_price")
	private float totalPrice;
	
	@Column(name = "order_date")
	private Date orderDate;

	@ManyToOne
	@JoinColumn(name = "email")
	private Account account;
	
	@OneToOne(mappedBy = "order")
	private OrderDetail details;
	
	public Order() {
		super();
	}

	public Order(long orderID,String address, String phone, float totalPrice, Date orderDate) {
		super();
		this.orderID = orderID;
		this.address = address;
		this.phone = phone;
		this.totalPrice = totalPrice;
		this.orderDate = orderDate;
	}

	public long getOrderID() {
		return orderID;
	}

	public void setOrderID(long orderID) {
		this.orderID = orderID;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public float getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(float totalPrice) {
		this.totalPrice = totalPrice;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public OrderDetail getDetails() {
		return details;
	}

	public void setDetails(OrderDetail details) {
		this.details = details;
	}
	
}
