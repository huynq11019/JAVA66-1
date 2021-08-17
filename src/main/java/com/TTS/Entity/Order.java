package com.TTS.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "orders")
public class Order extends SuperClass implements Serializable{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@NotNull(message = "không được để chống tên")
	@NotBlank(message = "địa chỉ không được để trống")
	@Column(name="ship_adress", length = 200)
	private String address;
//	@NotNull(message = "vui lòng chọn phương thức thanh toán")
	@NotBlank(message = "vui lòng chọn phương thức thanh toán")
	@Column(name="paymethod")
	private String paymethod;
	@Length(min = 5, message = "tên tối thiểu 5 ký tự")
	@NotBlank(message = "người nhận không được để trống")
	private String nguoinhan;
	@Length(min = 9, max = 15, message = "số điện thoại không hộ lệ")
	@NotNull
	private String sdt;
	@Min(value = 0)
	@Column(name="status")
	private Integer status = 0;
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "od_account")
	private Account accountod;
//	@JsonIgnore
	@OneToMany(mappedBy = "fromOd", fetch = FetchType.EAGER)
	private List<OrderDetail> orderDetail = new ArrayList<>();

//	public Double getTotalPrice(){
//		return orderDetail.stream().mapToDouble(i -> i.getPrice()).sum();
//	};
//	public void addDetail(OrderDetail odt){
//		orderDetail.add(odt);
//	}
	@Override
	public String toString() {
		return "Order{" +
				"id=" + id +
				", address='" + address + '\'' +
				", paymethod='" + paymethod + '\'' +
				", nguoinhan='" + nguoinhan + '\'' +
				", sdt='" + sdt + '\'' +
				", status=" + status +
				", accountod=" + accountod +
				", orderDetail=" + orderDetail.size() +
				'}';
	}
}
