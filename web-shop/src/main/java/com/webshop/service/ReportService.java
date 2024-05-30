package com.webshop.service;

import java.util.List;
import java.util.Map;

import com.webshop.model.Report;

public interface ReportService {
    boolean reportUser(Long whoReportedId, Long whoIsReportedId, String reason);

    boolean resolveReport(Long reportId, Map<String, String> resolution);

    List<Report> findAll();
}
