package com.webshop.service;

import java.util.Map;

public interface ReportService {
    boolean reportUser(Long whoReportedId, Long whoIsReportedId, String reason);
    boolean resolveReport(Long reportId, String resolution);
}
