package com.webshop.service;

import com.webshop.dto.ReviewDto;
import com.webshop.model.Review;

import java.util.List;
import java.util.Map;

public interface ReviewService {
    boolean reviewSeller(Long buyerId, Long sellerId, int score, String comment);

    boolean reviewBuyer(Long buyerId, Long sellerId, int score, String comment);

    List<ReviewDto> findByReviewedUserId(Long id);

    List<ReviewDto> requestReviews(Long requestingUserId, Long reviewedUserId);

    double getAverageRating(Long sellerId);

    Review editReview(Long userId, Long reviewId, Map<String, Object> update);

    void deleteReview(Long id);

    void updateAverageRating(Long reviewId);
}
