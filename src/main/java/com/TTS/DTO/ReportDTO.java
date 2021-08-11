package com.TTS.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
//@JsonInclude(JsonInclude.Include.NON_NULL)
public class ReportDTO {
    private Long money;
    private Long saleTotal;
    private Long countTotalOrder;
}
