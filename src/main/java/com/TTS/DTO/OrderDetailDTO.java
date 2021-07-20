package com.TTS.DTO;

import javax.validation.constraints.Min;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDetailDTO extends BaseDTO {
	private Integer id;
	@Min(value = 0)
	private Float price;
	@Min(value = 0)
	private Integer quantity;
	private Integer fromOdId;
	private Integer fromProductId;

}
