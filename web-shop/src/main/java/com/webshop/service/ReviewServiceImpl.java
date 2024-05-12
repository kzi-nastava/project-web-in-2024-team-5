package com.webshop.service;

import com.webshop.dto.ReviewDto;
import com.webshop.model.*;
import com.webshop.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
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
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ProductRepository productRepository;

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
                System.out.println(product.getName() + " " + product.isSold());
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
                System.out.println(product.getName() + " " + product.isSold());
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