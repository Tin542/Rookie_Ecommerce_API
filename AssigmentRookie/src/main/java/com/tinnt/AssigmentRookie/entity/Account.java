package com.tinnt.AssigmentRookie.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name = "accounts", uniqueConstraints = {@UniqueConstraint(columnNames = "username")})
@EntityListeners(AuditingEntityListener.class)
public class Account {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "accountId")
	private long accountID;
	
	@Column(name = "username")
	private String username;
	
	@Column(name = "password")
	private String password;
	
	@Column(name = "full_name")
	private String fullname;
	
	@Column(name = "create_date")
	@CreatedDate
	private Date createDate;
	

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable (name = "accounts_roles",
				joinColumns = @JoinColumn(name = "accountId"),
				inverseJoinColumns =  @JoinColumn(name = "roleId"))
	private Set<Role> roles = new HashSet<>();
	
	@OneToMany(mappedBy = "account")
	private List<Order> listOrder = new ArrayList<>();
	
	@OneToMany(mappedBy = "accountRate")
	private List<Rating> listRating = new ArrayList<>();
	
	public Account() {
		super();
	}

	public Account(String username, String password, String fullname) {
		super();
		this.username = username;
		this.password = password;
		this.fullname = fullname;
	}

	public long getAccountID() {
		return accountID;
	}

	public void setAccountID(long accountID) {
		this.accountID = accountID;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
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

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
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

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(accountID);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Account other = (Account) obj;
		return Objects.equals(accountID, other.accountID);
	}

	@Override
	public String toString() {
		return "Account [accountID=" + accountID + ", username=" + username + ", password=" + password + ", fullname="
				+ fullname + ", createDate=" + createDate + ", roles=" + roles + ", listOrder=" + listOrder
				+ ", listRating=" + listRating + "]";
	}

}
