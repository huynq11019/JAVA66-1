package com.TTS.Rest;

import com.TTS.DTO.ReportDTO;
import com.TTS.Repo.ReportReposistory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
@Slf4j
@CrossOrigin("*")
@RestController
public class RestReport {
    @Autowired
    private ReportReposistory reportReposistory;

    @GetMapping("/api/reporter")
    public ReportDTO getReportStore(@RequestParam int option) {
        // 1 load theo ngày
        // 2 load theo tháng
      log.info(String.valueOf(option));
//        Calendar startDate = Calendar.getInstance();
//        Calendar endDate = Calendar.getInstance();
        Date date = new Date();
        ReportDTO reportDTO =  reportReposistory.loadReport(date, date);
        return reportDTO;
    }
}
