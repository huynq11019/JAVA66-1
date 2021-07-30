package com.TTS.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.Set;

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
	@Min(1)
	@Max(4)
	private Integer status=0;
	@PositiveOrZero(message = "số lượng sản phẩm không được âm")
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

	@Override
	public String toString() {
		return "Product{" +
				"id=" + id +
				", nameProduct='" + nameProduct + '\'' +
				", image='" + image + '\'' +
				", realPrice=" + realPrice +
				", discount=" + discount +
				", status=" + status +
				", quantity=" + quantity +
				", description='" + description + '\'' +
				", category=" + category +
				'}';
	}

	public boolean isNewProduct(){
//		Date today =  this.getCreatedAt();
// 15 ngày trước
//		LocalDate _15ngayTruoc = LocalDate.now().minusDays(15);
		Calendar _15ngayTruoc = Calendar.getInstance();
		_15ngayTruoc.roll(Calendar.DATE, -15);
		Date  dater_point = Date.from(_15ngayTruoc.toInstant());
		if(this.getCreatedAt().after(dater_point)){
			return false;
		}
		return true;
	}
}
