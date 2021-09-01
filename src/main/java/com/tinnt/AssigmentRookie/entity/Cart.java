package com.tinnt.AssigmentRookie.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@Table(name = "cart",
		indexes = {
				@Index(name = "cart_idx", columnList = "cartId")
		})
public class Cart implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "cartId")
	private long id;

	@Column(name = "create_date")
	@CreatedDate
	private Date create_date;

	@Column(name = "update_date")
	@LastModifiedDate
	private Date update_date;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "account_id", referencedColumnName = "account_id")
	private Account acc;

	@OneToMany(mappedBy = "cart",fetch = FetchType.LAZY)
	private List<CartItem> listCartItem = new ArrayList<>();

}
