package com.TTS.DTO;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
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
	public boolean order;
	public int emlementOfpage;
}
