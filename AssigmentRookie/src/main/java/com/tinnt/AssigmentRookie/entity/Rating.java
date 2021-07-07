package com.tinnt.AssigmentRookie.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "rating")
public class Rating {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "rating_id")
	private long rate_id;
	
	@Column(name = "rate")
	private int rate;
	
	@Column(name = "feedback")
	private String feedback;

	@ManyToOne
	@JoinColumn(name = "email")
	private Account accountRate;
	
	@ManyToOne
	@JoinColumn(name = "book_id")
	private Book books;
	
	public Rating() {
		super();
	}

	public Rating(long rate_id, int rate, String feedback) {
		super();
		this.rate_id = rate_id;
		this.rate = rate;
		this.feedback = feedback;
	}

	public long getRate_id() {
		return rate_id;
	}

	public void setRate_id(long rate_id) {
		this.rate_id = rate_id;
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

	public Account getAccountRate() {
		return accountRate;
	}

	public void setAccountRate(Account accountRate) {
		this.accountRate = accountRate;
	}
	
}
