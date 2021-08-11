package com.TTS.DTO;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RequestLogin {
	@NotNull(message = "email login không được isNUll")
	@NotBlank(message = "email login khôn được để trống")
	@Email(message = "email không đúng định dạng")
	private String emailLogin;
	@Size(min = 3,message = "mật khẩu tối thiểu 3 ký tự")
	@NotNull(message = "password không được null")
	@NotBlank(message = "mật khẩu không được để trống")
	private String password;

}
