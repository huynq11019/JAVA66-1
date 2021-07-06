package com.TTS.Entity;

import java.io.Serializable;
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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="products")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product extends SuperClass implements Serializable {
	/**@author huy giao su
	 * @since 7/5
	 * 
		 * 
		 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@NotNull
	@Size(min = 3,max = 90,message = "length name betwent  3-90 ")
	@Column(name="name_product", length=90, nullable = false, unique = true)
	private String nameProduct;
	@Column(name="image_product")
	private String image;
	@Min(value = 0)
	@Column(name = "real_price")
	private float realPrice;
	@Min(value = 0,message = "% giảm giá phải lớn hơn ")
	@Column(name="discount")
	private float discount;
///1, còn hàng, 2 hết hàng, 3, Hàng sắp về, 4 hàng mới
	private int quantity;
	@Column(name = "description", length = 200)
	private String description;
	@JsonIgnore
	@ManyToOne()
	@JoinColumn(name = "product_category")
	private Category category;
	@JsonIgnore
	@OneToMany(mappedBy = "fromProduct")
	private Set<OrderDetail> orderDetails;

}
