package com.webshop.model;

import java.io.Serializable;
import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "reviews")
public class Review implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private Integer score;

    @Column(length = 1000)
    private String comment;

    @Column
    private LocalDate reviewDate;

    @ManyToOne
    @JoinColumn(name = "reviewer_id")
    private User reviewingUser;

    @ManyToOne
    @JoinColumn(name = "reviewed_id")
    private User reviewedUser;

    public Long getId() {
        return id;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public LocalDate getReviewDate() {
        return reviewDate;
    }

    public void setReviewDate(LocalDate reviewDate) {
        this.reviewDate = reviewDate;
    }

    public User getReviewingUser() {
        return reviewingUser;
    }

    public void setReviewingUser(User reviewingUser) {
        this.reviewingUser = reviewingUser;
    }

    public User getReviewedUser() {
        return reviewedUser;
    }

    public void setReviewedUser(User reviewedUser) {
        this.reviewedUser = reviewedUser;
    }

}
