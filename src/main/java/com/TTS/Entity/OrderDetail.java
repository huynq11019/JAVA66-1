package com.TTS.Entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Min;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="oderdetails")
public class OrderDetail extends SuperClass implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Min(value = 0)
	@Column(name = "price")
	private Float price;
	@Min(value = 0)
	@Column(name = "quantity")
	private Integer quantity;

	@ManyToOne()
	@JoinColumn(name = "form_od")
	private Order fromOd;
	@ManyToOne()
	@JoinColumn(name="form_Product")
	private Product fromProduct;
}
