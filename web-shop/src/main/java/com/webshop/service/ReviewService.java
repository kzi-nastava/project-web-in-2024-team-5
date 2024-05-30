package com.webshop.service;

import java.util.List;
import java.util.Map;

import com.webshop.dto.ReviewDto;
import com.webshop.model.Review;

public interface ReviewService {

    boolean reviewUser(Long requesterId, Long reviewedId, Map<String, String> reviewInfo);


    List<ReviewDto> requestReceivedReviews(Long requestingUserId, Long reviewedUserId);

    List<ReviewDto> getReviewedByMe(Long requestingUserId);

    double getAverageRating(Long sellerId);

    Review editReview(Long userId, Long reviewId, Map<String, Object> update);

    void deleteReview(Long id);

    void updateAverageRating(Long reviewId);

    List<ReviewDto> getWhoReviewedMe(Long requestingUserId);
}
