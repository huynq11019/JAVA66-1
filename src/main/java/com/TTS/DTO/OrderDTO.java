package com.TTS.DTO;

import com.TTS.Entity.OrderDetail;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OrderDTO extends BaseDTO {
	private Integer id;
	@NotNull(message = "không được để chống tên")
	@NotBlank(message = "địa chỉ không được để trống")
	private String address;
	@NotBlank(message = "vui lòng chọn phương thức thanh toán")
	private String paymethod;
	@Length(min = 5, message = "tên tối thiểu 5 ký tự")
	@NotBlank(message = "người nhận không được để trống")
	private String nguoinhan;
	@Length(min = 9, max = 15, message = "số điện thoại không hộ lệ")
	@NotNull
	private String sdt;
	@Min(value = 0)
	private Integer status = 0;
	private Integer  accIdString;
	private String accNameString;
	private List<OrderDetail> orderDetail ;

}
