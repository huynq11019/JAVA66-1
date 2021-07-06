package com.TTS.Entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "orders")
public class Order extends SuperClass implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@NotNull(message = "không được để chống tên")
	@NotBlank
	@Column(name="ship_adress", length = 200)
	private String address;
	@Column(name="paymethod")
	private String paymethod;
	@Min(value = 0)
	@Column(name="status")
	private Integer status;
	@JsonIgnore
	@ManyToOne()
	@JoinColumn(name = "od_account")
	private Account account_od;
	@JsonIgnore
	@OneToMany(mappedBy = "fromOd")
	private Set<OrderDetail> orderDetail = new HashSet<>();

}
