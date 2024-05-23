package com.webshop.dto;

import java.time.LocalDateTime;

import com.webshop.model.Report;
import com.webshop.model.ReportStatus;
import com.webshop.model.User;

public class ReportDto {
    private Long id;
    private String reason;
    private ReportStatus status;
    private Long whoReported;
    private Long whoIsReported;
    private LocalDateTime reportDate;

    public ReportDto(Report report) {
        this.id = report.getId();
        this.reason = report.getReason();
        this.status = report.getStatus();
        this.whoReported = report.getWhoReported().getId();
        this.whoIsReported = report.getWhoIsReported().getId();
        this.reportDate = report.getDatePosted();
    }

    public Long getId() {
        return id;
    }

    public Long getWhoReported() {
        return whoReported;
    }

    public void setWhoReported(User whoReported) {
        this.whoReported = whoReported.getId();
    }

    public Long getWhoIsReported() {
        return whoIsReported;
    }

    public void setWhoIsReported(User whoIsReported) {
        this.whoIsReported = whoIsReported.getId();
    }

    public String getReason() {
        return reason;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public ReportStatus getStatus() {
        return status;
    }

    public void setStatus(ReportStatus status) {
        this.status = status;
    }

    public LocalDateTime getReportDate() {
        return reportDate;
    }

    public void setReportDate(LocalDateTime reportDate) {
        this.reportDate = reportDate;
    }

}
