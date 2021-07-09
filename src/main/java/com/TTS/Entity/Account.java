package com.TTS.Entity;

import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.BatchSize;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "accounts")
public class Account extends SuperClass implements Serializable {
	/**
	 * @author huy giao su
	 * @login_with : email and password
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@NotBlank(message="Tên không được để trống")
	@NotNull(message = "Ful name đang là null")
	@Column(name = "fullname",length=80)
	private String fullName;
	@Size(min = 5, message = "độ dài passowrd từ 0-50 ký tự")
	@Column(name = "password_hash",  nullable = false)
	private String passwordHash;
	@Column(name = "passowrd_slat")
	private String passowrdSalt;
	@Column(name = "descrption", length = 200)
	@Size(min = 8, max = 15 , message = "số điện thoại từ 0-15 ký tự")
	private String description;
	@Column(name = "phone_number", length = 15)
	private String phoneNumber;
	/*
	 * status =0 là bình thường status = 1 là bị khóa status = 2 tài khoản Verry
	 * Impotant Peple
	 */
	@Column(name = "status")
	private Integer status = 0;
	@Column(name = "dob")
	private Instant DOB;
	@NotBlank(message = "email không được để trống")
	@NotNull(message = "email không được null")
	@Email(message = "email không đúng định dạng")
	@Column(name = "email", length = 50,unique = true)
	private String email;
//	@JsonIgnore
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "account_authrority", joinColumns = {
			@JoinColumn(name = "user_id", referencedColumnName = "id") }, inverseJoinColumns = {
					@JoinColumn(name = "authrority_name", referencedColumnName = "name") })
	@BatchSize(size = 10)
	private Set<Authrority> authrority = new HashSet<>();	
	@JsonIgnore
	@OneToMany(mappedBy = "account_od")
	private Set<Order> orders = new HashSet<>();
	@Override
	public String toString() {
		return "Account [id=" + id + ", fullName=" + fullName + ", passwordHash=" + passwordHash + ", passowrdSalt="
				+ passowrdSalt + ", description=" + description + ", phoneNumber=" + phoneNumber + ", status=" + status
				+ ", DOB=" + DOB + ", email=" + email + "]";
	}

}
