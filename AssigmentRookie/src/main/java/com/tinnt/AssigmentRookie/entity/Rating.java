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
	@Column(name = "ratingId")
	private long rateID;
	
	@Column(name = "rate")
	private int rate;
	
	@Column(name = "feedback")
	private String feedback;

	@ManyToOne
	@JoinColumn(name = "accountId")
	private Account accountRate;
	
	@ManyToOne
	@JoinColumn(name = "bookId")
	private Book book;

	public Rating() {
		super();
	}


	public Rating(long rateID, int rate, String feedback) {
		super();
		this.rateID = rateID;
		this.rate = rate;
		this.feedback = feedback;
	}


	public long getRateID() {
		return rateID;
	}


	public void setRateID(long rateID) {
		this.rateID = rateID;
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


	public Book getBook() {
		return book;
	}


	public void setBook(Book book) {
		this.book = book;
	}

}
