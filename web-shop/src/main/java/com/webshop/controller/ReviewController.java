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
import java.util.Map;
import java.util.Objects;

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
    @GetMapping("/request")
    public List<ReviewDto> getAllReviews(@RequestParam Long requestingUserId, @RequestParam Long reviewedUserId) {
        return reviewServiceImpl.requestReviews(requestingUserId, reviewedUserId);
    }
    @GetMapping("/average")
    public String getAverageScoreByReviewedUserId(@RequestParam Long reviewedUserId) {
            double ocena = reviewServiceImpl.getAverageRating(reviewedUserId) > 0 ? reviewServiceImpl.getAverageRating(reviewedUserId) : 0;
        return "Prosecna ocena je : " + ocena;
    }
    @PatchMapping("/{id}")
    public ResponseEntity<Review> updateReview(@PathVariable Long id, @RequestBody Map<String, Object> updates) {
        Review review = reviewServiceImpl.editReview(1L, id , updates);
        return ResponseEntity.ok(review);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReview(@PathVariable Long id) {
        reviewServiceImpl.deleteReview(id);
        return ResponseEntity.noContent().build();
    }
}
