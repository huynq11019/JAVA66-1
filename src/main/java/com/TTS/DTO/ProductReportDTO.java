package com.TTS.DTO;

import com.TTS.Entity.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductReportDTO {
    private Product product;
    private Long soDonhang;
    private Double totalSaleMoney;


}
