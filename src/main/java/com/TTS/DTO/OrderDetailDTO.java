package com.TTS.DTO;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OrderDetailDTO extends BaseDTO {
	private Integer id;
	@Min(value = 0)
	private Float price;
	@Min(value = 0)
	private Integer quantity;
	private Integer fromOdId;
	private Integer fromProductId;

}
