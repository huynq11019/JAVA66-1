package com.TTS.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "caregories")
@Entity
public class Category extends SuperClass {
	/**
		 * 
		 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@NotNull
	@Size(min = 3, max = 20)
	@Column(name = "name", length = 21)
	private String name;
	@Column(name = "parent_cate")
	private Integer parentCate;
	@Size(min = 5, max = 200)
	@Column(name = "descripion", length = 200)
	private String description;
	@JsonIgnore
	@OneToMany(mappedBy = "category")
	Set<Product> ProductInCate;

	@Override
	public String toString() {
		return "Category{" +
				"id=" + id +
				", name='" + name + '\'' +
				", parentCate=" + parentCate +
				", description='" + description + '\'' +
				'}';
	}
}
