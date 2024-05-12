package com.webshop.service;

import com.webshop.dto.ReviewDto;
import com.webshop.model.*;
import com.webshop.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ReviewServiceImpl implements ReviewService {
    @Autowired
    private ReviewRepository reviewRepository;
    @Autowired
    private BuyerRepository buyerRepository;
    @Autowired
    private SellerRepository sellerRepository;

    public ReviewServiceImpl(ReviewRepository reviewRepository, SellerRepository sellerRepository) {
        this.reviewRepository = reviewRepository;
        this.sellerRepository = sellerRepository;
    }

    @Override
    public void reviewSeller(Long buyerId, Long sellerId, int score, String comment) {
        Optional<Buyer> buyer = buyerRepository.findById(buyerId);
        if(buyer.isPresent()) {
            Buyer b = buyer.get();
            for(Product product : buyer.get().getProducts()) {
                if (product.getSeller().getId().equals(sellerId)) {

                    Seller seller = sellerRepository.findById(sellerId).get();
                    Review review = new Review();
                    review.setComment(comment);
                    review.setReviewedUser(seller);
                    review.setScore(score);
                    review.setReviewingUser(b);
                    reviewRepository.save(review);
                    return;
                }
            }
        }
    }

    @Override
    public void reviewBuyer(Long buyerId, Long sellerId, int score, String comment) {
        Optional<Seller> seller = sellerRepository.findById(buyerId);
        if(seller.isPresent()) {
            Seller s = seller.get();
            for(Product product : seller.get().getProducts()) {
                if (product.getSeller().getId().equals(sellerId)) {

                    Buyer buyer = buyerRepository.findById(sellerId).get();
                    Review review = new Review();
                    review.setComment(comment);
                    review.setReviewingUser(s);
                    review.setScore(score);
                    review.setReviewedUser(buyer);
                    reviewRepository.save(review);
                    return;
                }
            }
        }
    }

    @Override
    public List<ReviewDto> findByReviewedUserId(Long id) {
        List<ReviewDto> reviewsdto = new ArrayList<>();
        List<Review> reviews = (List<Review>) reviewRepository.findAllByReviewedUserId(id);
        for(Review review : reviews) {
            ReviewDto rev = new ReviewDto(review);
            reviewsdto.add(rev);
        }

        return reviewsdto;
    }


    @Override
    public double getAverageRatingSeller(Long sellerId) {
        return 0;
    }

    @Override
    public double getAverageRatingBuyer(Long buyerId) {
        return 0;
    }
}