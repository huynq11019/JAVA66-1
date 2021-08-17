package com.TTS.Rest;

import com.TTS.DTO.CateReportDTO;
import com.TTS.DTO.ProductReportDTO;
import com.TTS.Repo.ReportReposistory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@CrossOrigin("*")
@RestController
public class RestReport {
    @Autowired
    private ReportReposistory reportReposistory;


    @GetMapping("/api/productreport")
    public List<ProductReportDTO> getProductReportDTO(@RequestParam(name = "page", defaultValue = "1") int page, @RequestParam(name = "limit", defaultValue = "5") int limit){
        Pageable pageable = PageRequest.of(page, limit);
        return reportReposistory.getProductReport(pageable);
    }
    @GetMapping("/api/catereport")
    public List<CateReportDTO> getCateReport(@RequestParam( name = "page", defaultValue = "1") int page, @RequestParam(name = "limit", defaultValue = "5") int limit){
        Pageable pageable = PageRequest.of(page, limit);

        return reportReposistory.getCateReport(pageable);

    }
}
