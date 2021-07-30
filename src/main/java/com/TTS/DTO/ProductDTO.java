package com.TTS.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO extends BaseDTO {

    private Integer id;
    @NotNull
    @Size(min = 3, max = 90, message = "length name betwent  3-90 ")
    private String nameProduct;
    private String image;
    @Min(value = 0)
    private float realPrice;
    @Min(value = 0, message = "% giảm giá phải lớn hơn ")
    private float discount;
    ///1, còn hàng, 2 hết hàng, 3, Hàng sắp về, 4 hàng mới
    @Min(1)
    @Max(4)
    private Integer status = 0;
    @PositiveOrZero(message = "số lượng sản phẩm không được âm")
    private int quantity;

    private String description;

    private Integer category;

    private String cateGoryofPro;
//	private Set<String> orderDetails;
}
