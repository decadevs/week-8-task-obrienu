package com.obrien.picesmart.services.impl;


import com.obrien.picesmart.model.Product;
import com.obrien.picesmart.model.Review;
import com.obrien.picesmart.model.User;
import com.obrien.picesmart.repository.ReviewRepository;
import com.obrien.picesmart.services.ProductService;
import com.obrien.picesmart.services.ReviewService;
import com.obrien.picesmart.services.UserService;
import com.obrien.picesmart.utils.DTO.ReviewDTO;
import com.obrien.picesmart.utils.DTO.UserSendingDTO;
import com.obrien.picesmart.utils.builders.UserSenderBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ReviewServiceImpl implements ReviewService {
    @Autowired
    private UserService userService;

    @Autowired
    private ProductService productService;

    @Autowired
    private ReviewRepository reviewRepository;


    @Override
    public void createReview(ReviewDTO reviewDTO, long productId, long userId) {
        User user = userService.getUserById(userId);
        Product product = productService.getProduct(productId);
        if(user == null || product == null) return;
        Review review = new Review();
        review.setProduct(product);
        review.setUser(user);
        review.setText(reviewDTO.getText());
        review.setSubject(reviewDTO.getSubject());
        review.setTimestamp(LocalDateTime.now());
        review.setRating(reviewDTO.getRating());
        review.setText(reviewDTO.getText());
        reviewRepository.save(review);
    }


    @Override
    public void updateReview(ReviewDTO reviewDTO, long reviewId) {
        Review review = getReview(reviewId);
        if(review == null) return;
        if(reviewDTO.getText() != null) review.setText(reviewDTO.getText());
        reviewRepository.save(review);
    }

    @Override
    public void deleteReview(long reviewId) {
        reviewRepository.deleteById(reviewId);
    }

    @Override
    public ReviewDTO getReviewDTO(long reviewId) {
        Review review = getReview(reviewId);
        if(review == null) return null;
        UserSendingDTO user = new UserSenderBuilder().setUser(review.getUser()).build();
        ReviewDTO reviewDTO = new ReviewDTO();
        reviewDTO.setId(review.getId());
        reviewDTO.setProduct(review.getProduct());
        reviewDTO.setText(review.getText());
        reviewDTO.setUser(user);
        return reviewDTO;
    }

    @Override
    public Review getReview(long reviewId) {
        Optional<Review> review = reviewRepository.findById(reviewId);
        if(review == null){
            return null;
        }
        return  review.get();
    }

    @Override
    public List<Review> getReviews(long productId, int page, int size) {
        int offset = (page - 1) * size ;
        List<Review> reviews = reviewRepository.findAllReviews(productId,size, offset );
        return reviews;
     }

     @Override
     public long getTotalProductReviews(long productId){
        return reviewRepository.countByProductId(productId);
     }
}
