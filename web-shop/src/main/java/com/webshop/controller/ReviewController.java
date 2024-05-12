package com.webshop.controller;
import com.webshop.dto.ReviewDto;
import com.webshop.model.Review;
import com.webshop.repository.ReviewRepository;
import com.webshop.service.ReviewService;
import com.webshop.service.ReviewServiceImpl;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    public ResponseEntity<String> addReviewBuyer(@RequestParam Long buyerId, @RequestParam Long sellerId, @RequestParam int score, @RequestParam String comment) {
        boolean success = reviewServiceImpl.reviewBuyer(buyerId, sellerId, score, comment);
        if(success) return ResponseEntity.ok("Uspesno dodat review");
        else
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Neuspesno dodat review");
    }
    @PostMapping("/seller")
    public ResponseEntity<String> addReviewSeller(@RequestParam Long buyerId, @RequestParam Long sellerId, @RequestParam int score, @RequestParam String comment) {
        boolean success = reviewServiceImpl.reviewSeller(buyerId, sellerId, score, comment);
        if(success) return ResponseEntity.ok("Uspesno dodat review");
        else
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Neuspesno dodat review");
    }
    @GetMapping("")
    List<ReviewDto> getAllReviews(Long id) {
        return reviewServiceImpl.findByReviewedUserId(id);
    }
}
