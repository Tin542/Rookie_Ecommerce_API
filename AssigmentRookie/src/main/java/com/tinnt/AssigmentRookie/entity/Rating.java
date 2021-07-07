package com.tinnt.AssigmentRookie.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "rating")
public class Rating {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long rate_id;
	
	@Column(name = "book_id")
	private long book_id;
	
	@Column(name = "email")
	private String email;
	
	@Column(name = "rate")
	private int rate;
	
	@Column(name = "feedback")
	private String feedback;

	public Rating() {
		super();
	}

	public Rating(long rate_id, long book_id, String email, int rate, String feedback) {
		super();
		this.rate_id = rate_id;
		this.book_id = book_id;
		this.email = email;
		this.rate = rate;
		this.feedback = feedback;
	}

	public long getRate_id() {
		return rate_id;
	}

	public void setRate_id(long rate_id) {
		this.rate_id = rate_id;
	}

	public long getBook_id() {
		return book_id;
	}

	public void setBook_id(long book_id) {
		this.book_id = book_id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getRate() {
		return rate;
	}

	public void setRate(int rate) {
		this.rate = rate;
	}

	public String getFeedback() {
		return feedback;
	}

	public void setFeedback(String feedback) {
		this.feedback = feedback;
	}
	
}
