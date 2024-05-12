package com.webshop.controller;
import com.webshop.dto.ReviewDto;
import com.webshop.model.Review;
import com.webshop.repository.ReviewRepository;
import com.webshop.service.ReviewService;
import com.webshop.service.ReviewServiceImpl;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping("/api/v1/reviews")
public class ReviewController {
    @Autowired
    private ReviewServiceImpl reviewServiceImpl;
    @Autowired
    private ReviewRepository reviewRepository;

    @PostMapping("/buyer")
    public ResponseEntity<String> addReview(@RequestParam Long buyerId, @RequestParam Long sellerId, @RequestParam int score, @RequestParam String comment) {
        reviewServiceImpl.reviewBuyer(buyerId, sellerId, score, comment);
        return ResponseEntity.ok("Uspesno dodat review");
    }
    @GetMapping("")
    List<ReviewDto> getAllReviews(Long id) {
        return reviewServiceImpl.findByReviewedUserId(id);
    }
}
