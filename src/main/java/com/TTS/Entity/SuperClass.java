package com.TTS.Entity;

import java.io.Serializable;
import java.time.Instant;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@MappedSuperclass
public abstract class SuperClass implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@CreatedDate
	@Column(name = "created_at", updatable = false)
	private Date createdAt = new Date();
	@Column(name = "created_by", updatable = false)
	private Integer createdBy;
	@LastModifiedDate
	@Column(name="last_updated")
	private Instant lastUpdated = Instant.now();
	@Column(name = "updated_by")
	private Integer updatedBy;
	@Column(name = "deleteAt")
	private Instant deleteAt ;
}
