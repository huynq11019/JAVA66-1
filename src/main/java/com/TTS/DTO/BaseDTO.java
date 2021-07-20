package com.TTS.DTO;

import java.time.Instant;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BaseDTO {
	private Date createdAt = new Date();
	private Integer createdBy;
	private Instant lastUpdated = Instant.now();
	private Integer updatedBy;
	private Instant deleteAt ;
}
