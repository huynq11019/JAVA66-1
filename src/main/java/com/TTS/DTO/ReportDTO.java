package com.TTS.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReportDTO {
    private Long totalImcome;

    private Long totalToday;

    private Long OrderToday;

    private Long SeleProducttoday;

}
