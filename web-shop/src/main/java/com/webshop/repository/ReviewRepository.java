package com.webshop.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.webshop.model.Review;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {

    List<Review> findAllByReviewedUserId(Long id);

    List<Review> findAllByReviewingUserIdAndReviewedUserId(Long reviewerId, Long reviewedId);

    List<Review> findAllByReviewingUserId(Long id);
}
