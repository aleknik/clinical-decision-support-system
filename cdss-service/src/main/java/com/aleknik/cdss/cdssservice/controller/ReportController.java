package com.aleknik.cdss.cdssservice.controller;

import com.aleknik.cdss.cdssservice.service.ReportService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ReportController {

    private final ReportService reportService;

    public ReportController(ReportService reportService) {
        this.reportService = reportService;
    }

    @GetMapping("/reports/generate")
    ResponseEntity getreport() {
        return ResponseEntity.ok(reportService.getReport());
    }
}
