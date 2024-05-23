package com.webshop.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.webshop.dto.ReviewDto;
import com.webshop.model.Review;
import com.webshop.service.ReviewServiceImpl;
import com.webshop.session.UserSession;

import jakarta.servlet.http.HttpSession;

@RestController()
@RequestMapping("/api/v1/reviews")
public class ReviewController {
    @Autowired
    private ReviewServiceImpl reviewServiceImpl;

    /**
     * Postavljanje recenzija useru sa idom /{id}.
     * @param session
     * @param id
     * @param reviewInfo
     * @return
     */
    @PostMapping("/{id}")
    public ResponseEntity<String> createReview(HttpSession session, @PathVariable Long id,
            @RequestBody Map<String, String> reviewInfo) {
        UserSession userSession = (UserSession) session.getAttribute("User");
        if (userSession == null) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        Long userId = userSession.getId();
        boolean reviewed = reviewServiceImpl.checkIfReviewed(userId, id);
        if (reviewed) {
            return new ResponseEntity<>("You already reviewed this user.", HttpStatus.BAD_REQUEST);
        }
        if (reviewInfo.containsKey("score") && reviewInfo.containsKey("comment")) {
            boolean success = reviewServiceImpl.reviewUser(userId, id, reviewInfo);
            return success ? new ResponseEntity<>("Review added successfully.", HttpStatus.CREATED)
                    : new ResponseEntity<>("Something went wrong while trying to add review..",
                            HttpStatus.INTERNAL_SERVER_ERROR);

        }
        return new ResponseEntity<>("Please provide score and comment for review.", HttpStatus.BAD_REQUEST);

    }

    /**
     * Funkcionalnosti 2.2 i 3.2, ako je userId prodavac, dobicemo sve recenzije koje je on dobio od kupaca i obrnuto.
     * @param session
     * @param userId
     * @return
     */
    @GetMapping("/request/user/{userId}/received")
    public ResponseEntity<List<ReviewDto>> getAllReviews(HttpSession session, @PathVariable Long userId) {
        UserSession loggedUser = (UserSession) session.getAttribute("User");
        long requestingUserId = loggedUser.getId();
        return ResponseEntity.ok(reviewServiceImpl.requestReceivedReviews(requestingUserId, userId));
    }

    /**
     * Funkcionalnosti 2.5 i 3.7 prvi deo, SAMO ako sam dao review useru,
     * dobicu sve recenzije koje je on dao drugima!
     * @param session
     * @param userId
     * @return
     */
    @GetMapping("/request/user/{userId}/posted")
    public ResponseEntity<List<ReviewDto>> getAllPostedReviews(HttpSession session, @PathVariable Long userId) {
        UserSession loggedUser = (UserSession) session.getAttribute("User");
        if(loggedUser == null) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        if(loggedUser.getRole().equals("admin")) {
            return ResponseEntity.ok(reviewServiceImpl.findByReviewingUserId(userId));
        }
        return ResponseEntity.ok(reviewServiceImpl.requestGivenReviews(loggedUser.getId(), userId));
    }

    /**
     * Omogoucava da vidis recenzije koje si ti kao korisnik dobio.
     * @param session
     * @return
     */
    @GetMapping("/request/received")
    public ResponseEntity<List<ReviewDto>> getReceivedReviews(HttpSession session) {
        UserSession loggedUser = (UserSession) session.getAttribute("User");
        if(loggedUser == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        return ResponseEntity.ok(reviewServiceImpl.getWhoReviewedMe(loggedUser.getId()));
    }

    /**
     * Funkcionalonost 2.5 i 3.7 pod b), vraca ti recenzije koje si ti dao drugima.
     * @param session
     * @return
     */
    @GetMapping("/request/posted")
    public ResponseEntity<List<ReviewDto>> getPostedReviews(HttpSession session) {
        UserSession loggedUser = (UserSession) session.getAttribute("User");
        if(loggedUser == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        return ResponseEntity.ok(reviewServiceImpl.getReviewedByMe(loggedUser.getId()));
    }

    /**
     * Dobijanje prosecne ocene od korisnika.
     * @param reviewedUserId
     * @return
     */
    @GetMapping("/average")
    public String getAverageScoreByReviewedUserId(@RequestParam Long reviewedUserId) {
        double ocena = reviewServiceImpl.getAverageRating(reviewedUserId) > 0
                ? reviewServiceImpl.getAverageRating(reviewedUserId)
                : 0;
        return "Prosecna ocena je : " + ocena;
    }

    /** Updatovanje reviewa sa idom odredjenim.
     *
     * @param session
     * @param id
     * @param updates
     * @return
     */
    @PatchMapping("/{id}")
    public ResponseEntity<ReviewDto> updateReview(HttpSession session, @PathVariable Long id,
            @RequestBody Map<String, Object> updates) {
        UserSession loggedUser = (UserSession) session.getAttribute("User");
        if (loggedUser.getRole().equals("admin")) {
            Review review = reviewServiceImpl.editReview(loggedUser.getId(), id, updates);
            ReviewDto revDto = new ReviewDto(review);
            return ResponseEntity.ok(revDto);
        } else
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
    }

    /**
     * Brisanje reviewa sa idom /{id}
     * @param session
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteReview(HttpSession session, @PathVariable Long id) {
        UserSession loggedUser = (UserSession) session.getAttribute("User");
        if (loggedUser.getRole().equals("admin")) {
            reviewServiceImpl.deleteReview(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Ne mozes, nisi admin.");
        }
    }
}
