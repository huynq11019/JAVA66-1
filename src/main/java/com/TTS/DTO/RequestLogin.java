package com.TTS.DTO;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestLogin {
	@NotNull(message = "email login không được isNUll")
	@NotBlank(message = "email login khôn được để trống")
	@Email(message = "email không đúng định dạng")
	private String emailLoign;
	@NotNull(message = "password không được null")
	@NotBlank
	private String password;

}
