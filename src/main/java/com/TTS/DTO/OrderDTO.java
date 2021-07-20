package com.TTS.DTO;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.TTS.Entity.Account;
import com.TTS.Entity.OrderDetail;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDTO extends BaseDTO {
	private Integer id;
	@NotNull(message = "không được để chống tên")
	@NotBlank
	private String address;
	@Column(name="paymethod")
	private String paymethod;
	@Min(value = 0)
	private Integer status;	
	private Integer account_od;
	private Set<OrderDetail> orderDetail = new HashSet<>();

}
