package com.tinnt.AssigmentRookie.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "roles")
public class Role {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "roleId")
	private Long roleID;
	
	@Column(name = "roleName", length = 60)
	@Enumerated(EnumType.STRING)
	private ERole name;
	
	public Role() {
		super();
	}

	public Role(Long roleID, ERole name) {
		super();
		this.roleID = roleID;
		this.name = name;
	}

	public long getRoleID() {
		return roleID;
	}

	public ERole getName() {
		return name;
	}

	public void setName(ERole name) {
		this.name = name;
	}

	public void setRoleID(Long roleID) {
		this.roleID = roleID;
	}


}
