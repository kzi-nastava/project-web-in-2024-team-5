package com.webshop.dto;

import com.webshop.model.Report;
import com.webshop.model.ReportStatus;
import com.webshop.model.User;

import java.time.LocalDateTime;

public class ReportDto {
    private Long id;
    private String reason;
    private ReportStatus status;
    private User whoReported;
    private User whoIsReported;
    private LocalDateTime reportDate;
    public Long getId() {
        return id;
    }

    public ReportDto(Report report) {
        this.id = report.getId();
        this.reason = report.getReason();
        this.status = report.getStatus();
        this.whoReported = report.getWhoReported();
        this.whoIsReported = report.getWhoIsReported();
        this.reportDate = report.getDatePosted();
    }


    public User getWhoReported() {
        return whoReported;
    }

    public void setWhoReported(User whoReported) {
        this.whoReported = whoReported;
    }

    public User getWhoIsReported() {
        return whoIsReported;
    }

    public void setWhoIsReported(User whoIsReported) {
        this.whoIsReported = whoIsReported;
    }

    public String getReason() {
        return reason;
    }

}
