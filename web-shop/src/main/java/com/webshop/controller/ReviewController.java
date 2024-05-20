package com.webshop.controller;
import com.webshop.dto.ReviewDto;
import com.webshop.model.Review;
import com.webshop.model.User;
import com.webshop.repository.ReviewRepository;
import com.webshop.service.ReviewService;
import com.webshop.service.ReviewServiceImpl;
import com.webshop.session.UserSession;
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
    @PostMapping("/{id}")
    public ResponseEntity<String> createReview(HttpSession session, @PathVariable Long id, @RequestBody Map<String, String> reviewInfo) {
        UserSession userSession = (UserSession) session.getAttribute("User");
        if (userSession == null) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        Long userId = userSession.getId();
        boolean reviewed = reviewServiceImpl.checkIfReviewed(userId, id);
        if(reviewed) {
            return new ResponseEntity<>("You already reviewed this user.", HttpStatus.BAD_REQUEST);
        }
        if(reviewInfo.containsKey("score") && reviewInfo.containsKey("comment")) {
            boolean success = reviewServiceImpl.reviewUser(userId, id, reviewInfo);
            return success ? new ResponseEntity<>("Review added successfully.", HttpStatus.CREATED) : new ResponseEntity<>("Something went wrong while trying to add review..", HttpStatus.INTERNAL_SERVER_ERROR);

        }
        return new ResponseEntity<>("Please provide score and comment for review.", HttpStatus.BAD_REQUEST);

    }
    @GetMapping("/request")
    public List<ReviewDto> getAllReviews(HttpSession session, @RequestParam Long reviewedUserId) {
        UserSession loggedUser = (UserSession) session.getAttribute("User");
        long requestingUserId = loggedUser.getId();
        return reviewServiceImpl.requestReviews(requestingUserId, reviewedUserId);
    }
    @GetMapping("/average")
    public String getAverageScoreByReviewedUserId(@RequestParam Long reviewedUserId) {
            double ocena = reviewServiceImpl.getAverageRating(reviewedUserId) > 0 ? reviewServiceImpl.getAverageRating(reviewedUserId) : 0;
        return "Prosecna ocena je : " + ocena;
    }
    @PatchMapping("/{id}")
    public ResponseEntity<ReviewDto> updateReview(HttpSession session, @PathVariable Long id, @RequestBody Map<String, Object> updates) {
        UserSession loggedUser = (UserSession) session.getAttribute("User");
        if(loggedUser.getRole().equals("admin")) {
            Review review = reviewServiceImpl.editReview(loggedUser.getId(), id, updates);
            ReviewDto revDto = new ReviewDto(review);
            return ResponseEntity.ok(revDto);
        }
        else return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteReview(HttpSession session,@PathVariable Long id) {
        UserSession loggedUser = (UserSession) session.getAttribute("User");
        if(loggedUser.getRole().equals("admin")) {
            reviewServiceImpl.deleteReview(id);
            return ResponseEntity.noContent().build();
        }
        else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Ne mozes, nisi admin.");
        }
    }
}
