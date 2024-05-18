package com.webshop.controller;
import com.webshop.dto.ReviewDto;
import com.webshop.model.Review;
import com.webshop.model.User;
import com.webshop.repository.ReviewRepository;
import com.webshop.service.ReviewService;
import com.webshop.service.ReviewServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
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
    public ResponseEntity<String> addReviewBuyer(HttpSession session, @RequestParam Long buyerId, @RequestParam int score, @RequestParam String comment) {
        User loggedUser = (User) session.getAttribute("User");
        long sellerId = loggedUser.getId();
        boolean success = reviewServiceImpl.reviewBuyer(buyerId, sellerId, score, comment);
        if(success) return ResponseEntity.ok("Uspesno dodat review");
        else
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Neuspesno dodat review");
    }
    @PostMapping("/seller")
    public ResponseEntity<String> addReviewSeller(HttpSession session, @RequestParam Long sellerId, @RequestParam int score, @RequestParam String comment) {
        User loggedUser = (User) session.getAttribute("User");
        long buyerId = loggedUser.getId();
        boolean success = reviewServiceImpl.reviewSeller(buyerId, sellerId, score, comment);
        if(success) return ResponseEntity.ok("Uspesno dodat review");
        else
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Neuspesno dodat review");
    }
    @GetMapping("/request")
    public List<ReviewDto> getAllReviews(HttpSession session, @RequestParam Long reviewedUserId) {
        User loggedUser = (User) session.getAttribute("User");
        long requestingUserId = loggedUser.getId();
        return reviewServiceImpl.requestReviews(requestingUserId, reviewedUserId);
    }
    @GetMapping("/average")
    public String getAverageScoreByReviewedUserId(@RequestParam Long reviewedUserId) {
            double ocena = reviewServiceImpl.getAverageRating(reviewedUserId) > 0 ? reviewServiceImpl.getAverageRating(reviewedUserId) : 0;
        return "Prosecna ocena je : " + ocena;
    }
    @PatchMapping("/update/{id}")
    public ResponseEntity<ReviewDto> updateReview(HttpSession session, @PathVariable Long id, @RequestBody Map<String, Object> updates) {
        User loggedUser = (User) session.getAttribute("User");
        if(loggedUser.getUserRole().equals("admin")) {
            Review review = reviewServiceImpl.editReview(loggedUser.getId(), id, updates);
            ReviewDto revDto = new ReviewDto(review);
            return ResponseEntity.ok(revDto);
        }
        else return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteReview(HttpSession session,@PathVariable Long id) {
        User loggedUser = (User) session.getAttribute("User");
        if(loggedUser.getUserRole().equals("admin")) {
            reviewServiceImpl.deleteReview(id);
            return ResponseEntity.noContent().build();
        }
        else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Ne mozes, nisi admin.");
        }
    }
}
