package com.webshop.service;

import com.webshop.dto.ReviewDto;
import com.webshop.model.*;
import com.webshop.repository.*;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class ReviewServiceImpl implements ReviewService {
    @Autowired
    private ReviewRepository reviewRepository;
    @Autowired
    private BuyerRepository buyerRepository;
    @Autowired
    private SellerRepository sellerRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private AdminRepository adminRepository;

    public ReviewServiceImpl(ReviewRepository reviewRepository, SellerRepository sellerRepository) {
        this.reviewRepository = reviewRepository;
        this.sellerRepository = sellerRepository;
    }

    @Override
    public boolean reviewSeller(Long buyerId, Long sellerId, int score, String comment) {
        Optional<Buyer> buyer = buyerRepository.findById(buyerId);
        if(buyer.isPresent()) {
            List<Product> products = productRepository.findAllBySellerIdAndBuyerId(sellerId, buyerId);
            for (Product product : products) {
                if (product.isSold()) {
                    Buyer b = product.getBuyer();
                    Seller s = product.getSeller();
                    Review review = new Review();
                    review.setComment(comment);
                    review.setReviewingUser(b);
                    review.setScore(score);
                    review.setReviewDate(LocalDateTime.now());
                    review.setReviewedUser(s);
                    reviewRepository.save(review);
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public boolean reviewBuyer(Long buyerId, Long sellerId, int score, String comment) {
       Optional<Seller> seller = sellerRepository.findById(sellerId);
        if(seller.isPresent()) {
            List<Product> products = productRepository.findAllBySellerIdAndBuyerId(sellerId, buyerId);
            for (Product product : products) {

                if (product.isSold()) {
                    Buyer b = product.getBuyer();
                    Seller s = product.getSeller();
                    Review review = new Review();
                    review.setComment(comment);
                    review.setReviewingUser(s);
                    review.setScore(score);
                    review.setReviewDate(LocalDateTime.now());
                    review.setReviewedUser(b);
                    reviewRepository.save(review);
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public List<ReviewDto> findByReviewedUserId(Long reviewedUserId) {
        List<ReviewDto> reviewsdto = new ArrayList<>();
        List<Review> reviews = (List<Review>) reviewRepository.findAllByReviewedUserId(reviewedUserId);
        for(Review review : reviews) {
            ReviewDto rev = new ReviewDto(review);
            reviewsdto.add(rev);
        }

        return reviewsdto;
    }
    @Override
    public List<ReviewDto> requestReviews(Long requestingUserId, Long reviewedUserId) {
        List<ReviewDto> reviews = findByReviewedUserId(reviewedUserId);
        if(adminRepository.findById(requestingUserId).isPresent()) {
            return reviews;
        }
        else {
            for (ReviewDto review : reviews) {
                if (review.getReviewingUserId().equals(requestingUserId)) {
                    return reviews;
                }
            }
            return List.of();
        }
    }

    @Override
    public double getAverageRating(Long id) {
        List<ReviewDto> reviews = findByReviewedUserId(id);
        double sum = 0;
        double reviewCount = 0;
        for(ReviewDto review : reviews) {
            sum += review.getScore();
            reviewCount++;
        }
        return sum/reviewCount;
    }

    @Override
    public Review editReview(Long userId, Long reviewId, Map<String, Object> update)  {
        return reviewRepository.findById(reviewId).map(review -> {
            if(update.containsKey("score")) {
                review.setScore((Integer) update.get("score"));
            }
            if(update.containsKey("comment")) {
                review.setComment((String) update.get("comment"));
            }
            if(update.containsKey("date")) {
                review.setReviewDate((LocalDateTime) update.get("date"));
            }
            return reviewRepository.save(review);
        }).orElseThrow(() -> new EntityNotFoundException("Review not found"));
    }

    @Override
    public void deleteReview(Long id) {
        reviewRepository.deleteById(id);
    }
}