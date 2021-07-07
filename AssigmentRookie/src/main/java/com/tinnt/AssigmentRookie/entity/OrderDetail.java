package com.tinnt.AssigmentRookie.entity;

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
@Table(name = "order_detail")
public class OrderDetail {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "orider_detail_id")
	private long orderDetailID;
	
	@Column(name = "amount")
	private int amount;
	
	@Column(name = "price")
	private float price;
	
	public OrderDetail() {
		super();
	}
	
	@OneToOne
	@JoinColumn(name = "order_id")
	private Order order;
	
	@ManyToOne
    @JoinColumn(name = "book_id")
    private Book book; 
	
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

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}
	
}
