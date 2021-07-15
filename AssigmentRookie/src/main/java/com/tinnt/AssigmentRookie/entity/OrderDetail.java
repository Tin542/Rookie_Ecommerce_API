package com.tinnt.AssigmentRookie.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name = "order_detail")
public class OrderDetail {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "orderDetailId")
	private long orderDetailID;
	
	@Column(name = "amount")
	private int amount;
	
	@Column(name = "price")
	private float price;
	
	public OrderDetail() {
		super();
	}
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "orderId")
	private Order order;
	
	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "bookId")
    private Book books; 
	
	public OrderDetail(long orderDetailID, int amount, float price) {
		super();
		this.orderDetailID = orderDetailID;
		this.amount = amount;
		this.price = price;
	}

	public long getOrderDetailID() {
		return orderDetailID;
	}

	public void setOrderDetailID(long orderDetailID) {
		this.orderDetailID = orderDetailID;
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

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public Book getBooks() {
		return books;
	}

	public void setBooks(Book books) {
		this.books = books;
	}

}
