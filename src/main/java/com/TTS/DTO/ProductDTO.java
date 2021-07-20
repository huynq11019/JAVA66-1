package com.TTS.DTO;

import java.util.Set;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.TTS.Entity.Category;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO  extends  BaseDTO{
	
	private Integer id;
	@NotNull
	@Size(min = 3,max = 90,message = "length name betwent  3-90 ")
	private String nameProduct;
	private String image;
	@Min(value = 0)
	private float realPrice;
	@Min(value = 0,message = "% giảm giá phải lớn hơn ")
	private float discount;
///1, còn hàng, 2 hết hàng, 3, Hàng sắp về, 4 hàng mới
	private int quantity;
	
	private String description;
	
	private Integer category;

	private String cateGoryofPro;
//	private Set<String> orderDetails;
}
