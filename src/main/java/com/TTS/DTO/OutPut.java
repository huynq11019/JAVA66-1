package com.TTS.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OutPut<T> {
	public List<T> content;
	public boolean frist;
	public boolean last;
	public int totalPage;
	public int Page;
	public int limit;
	public String orderBy;
	public String order;
	public int emlementOfpage;
}
