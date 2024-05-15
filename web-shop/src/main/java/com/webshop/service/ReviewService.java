package com.webshop.service;

import com.webshop.dto.ReviewDto;
import com.webshop.model.Review;

import java.util.List;
import java.util.Map;

public interface ReviewService {
    public boolean reviewSeller(Long buyerId, Long sellerId, int score, String comment);

    public boolean reviewBuyer(Long buyerId, Long sellerId, int score, String comment);

    public List<ReviewDto> findByReviewedUserId(Long id);

    public List<ReviewDto> requestReviews(Long requestingUserId, Long reviewedUserId);

    public double getAverageRating(Long sellerId);

    public Review editReview(Long userId, Long reviewId, Map<String, Object> update);

    public void deleteReview(Long id);
}
