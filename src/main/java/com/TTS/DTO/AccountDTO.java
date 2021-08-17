package com.TTS.DTO;

import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

//import com.TTS.Entity.Order;
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
//@JsonInclude(JsonInclude.Include.NON_NULL)
public class AccountDTO {
	private Integer id;
	@NotBlank(message = "Tên không được để trống")
	@NotNull(message = "fullname đang là null")
	@Size(min = 5, max = 50, message = "tên chỉ trong khoản 5-50 ký tự")
	private String fullName;
	@Size(min = 5,  message = "độ dài passowrd từ 0-200 ký tự from dto")
	private String password;
//	private String passowrdSalt;
	@Size(max = 200, message = "Mô tả phải ít hơn 200 ký tự")
	private String description;
	@Size(min = 8, max = 15, message = "số điện thoại từ 8-15 ký tự")
	private String phoneNumber;
	/*
	 * status =0 là bình thường status = 1 là bị khóa status = 2 tài khoản Verry
	 * Impotant Peple
	 */
	private Integer status = 0;
	private String dob;
	private Date createdAt ;
//	private Integer createdBy;
//	private Instant lastUpdated ;
//	private Integer updatedBy;

	@NotBlank(message = "email không được để trống")
	@NotNull(message = "email không được null")
	@Email(message = "email không đúng định dạng")
	private String email;
	private Set<String> authrority = new HashSet<>();
	private Set<String> orderString = new HashSet<>();
//	public Set<String> getAuthrorityString() {
//		// TODO Auto-generated method stub
//		return authrorityString;
//	}
//	public void setAuthrority(Set<Sting> value) {
//		auth
//	}
}
