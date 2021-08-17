package com.TTS.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BaseDTO {
    private Date createdAt = new Date();
    private Integer createdBy;
    private String createByName;
    private Instant lastUpdated = Instant.now();
    private Integer updatedBy;
    private Instant deleteAt;
}
