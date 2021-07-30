package com.TTS.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Min;
import java.io.Serializable;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="orderdetails")
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
