package com.TTS.DTO;

import javax.persistence.Column;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.sun.istack.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CategoryDTO {
	private Integer id;
	@NotNull
	@Size(min = 3, max = 20)
	private String name;
	private Integer parentCate;
	@Size(min = 5, max = 200)
	private String description;
}
