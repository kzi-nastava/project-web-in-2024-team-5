package com.webshop.dto;

import java.time.LocalDateTime;

import com.webshop.model.Review;

public class ReviewDto {
    private Long id;

    private Integer score;

    private String comment;

    private LocalDateTime reviewDate;

    private Long reviewedUserId;

    private Long reviewingUserId;

    public ReviewDto() {

    }

    public ReviewDto(Long id, Integer score, String comment, Long reviewedUser, Long reviewingUser) {
        this.id = id;
        this.score = score;
        this.comment = comment;
        this.reviewingUserId = reviewingUser;
        this.reviewedUserId = reviewingUser;
        this.reviewDate = LocalDateTime.now();
    }

    public ReviewDto(Review review) {
        this.id = review.getId();
        this.score = review.getScore();
        this.comment = review.getComment();
        this.reviewDate = review.getReviewDate();
        this.reviewedUserId = review.getReviewedUser().getId();
        this.reviewingUserId = review.getReviewingUser().getId();

    }

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

    public LocalDateTime getReviewDate() {
        return reviewDate;
    }

    public void setReviewDate(LocalDateTime reviewDate) {
        this.reviewDate = reviewDate;
    }

    public Long getReviewedUserId() {
        return reviewedUserId;
    }

    public void setReviewedUserId(Long user) {
        this.reviewedUserId = user;
    }

    public Long getReviewingUserId() {
        return reviewingUserId;
    }

    public void setReviewingUserId(Long user) {
        this.reviewingUserId = user;
    }

}
