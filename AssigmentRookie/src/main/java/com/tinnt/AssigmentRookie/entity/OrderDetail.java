package com.tinnt.AssigmentRookie.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "order_detail")
public class OrderDetail {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long orderDetailID;
	
	@Column(name = "order_id")
	private long orderID;
	
	@Column(name = "book_id")
	private long book_id;
	
	@Column(name = "amount")
	private int amount;
	
	@Column(name = "price")
	private float price;
	
	public OrderDetail() {
		super();
	}
	
	public OrderDetail(long orderDetailID, long orderID, long book_id, int amount, float price) {
		super();
		this.orderDetailID = orderDetailID;
		this.orderID = orderID;
		this.book_id = book_id;
		this.amount = amount;
		this.price = price;
	}

	public long getOrderDetailID() {
		return orderDetailID;
	}

	public void setOrderDetailID(long orderDetailID) {
		this.orderDetailID = orderDetailID;
	}

	public long getOrderID() {
		return orderID;
	}

	public void setOrderID(long orderID) {
		this.orderID = orderID;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public long getBook_id() {
		return book_id;
	}


	public void setBook_id(long book_id) {
		this.book_id = book_id;
	}
	
}
