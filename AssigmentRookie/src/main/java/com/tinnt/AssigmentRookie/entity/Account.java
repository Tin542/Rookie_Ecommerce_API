package com.tinnt.AssigmentRookie.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "accounts")
public class Account {
	@Id
	@Column(name = "email")
	private String email;
	
	@Column(name = "password")
	private String password;
	
	@Column(name = "full_name")
	private String fullname;
	
	@Column(name = "address")
	private String address;
	
	@Column(name = "phone")
	private String phone;
	
	@Column(name = "create_date")
	private Date createDate;
	

	@ManyToMany
	@JoinTable (name = "accounts_roles",
				joinColumns = @JoinColumn(name = "email"),
				inverseJoinColumns =  @JoinColumn(name = "role_id"))
	private List<Role> listRole = new ArrayList<>();
	
	@OneToMany(mappedBy = "account")
	private List<Order> listOrder = new ArrayList<>();
	
	@OneToMany(mappedBy = "accountRate")
	private List<Rating> listRating = new ArrayList<>();
	
	public Account() {
		super();
	}

	public Account(String email, String password, String fullname, String address, String phone, Date createDate) {
		super();
		this.email = email;
		this.password = password;
		this.fullname = fullname;
		this.address = address;
		this.phone = phone;
		this.createDate = createDate;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
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

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	
	public List<Role> getListRole() {
		return listRole;
	}

	public void setListRole(List<Role> listRole) {
		this.listRole = listRole;
	}

	public List<Order> getListOrder() {
		return listOrder;
	}

	public void setListOrder(List<Order> listOrder) {
		this.listOrder = listOrder;
	}

	public List<Rating> getListRating() {
		return listRating;
	}

	public void setListRating(List<Rating> listRating) {
		this.listRating = listRating;
	}
	
}
