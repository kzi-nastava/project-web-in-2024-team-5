package com.webshop.model;

import java.io.Serializable;
import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "reports")
public class Report implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 1000)
    private String reason;

    @Column
    private LocalDate datePosted;

    @Enumerated(EnumType.STRING)
    @Column
    private ReportStatus status;

    @ManyToOne
    private User whoReported;

    @ManyToOne
    private User whoIsReported;

    public Long getId() {
        return id;
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

    public void setReason(String reason) {
        this.reason = reason;
    }

    public LocalDate getDatePosted() {
        return datePosted;
    }

    public void setDatePosted(LocalDate datePosted) {
        this.datePosted = datePosted;
    }

    public ReportStatus getStatus() {
        return status;
    }

    public void setStatus(ReportStatus status) {
        this.status = status;
    }

}
