package com.obrien.picesmart.services;


import com.obrien.picesmart.model.Review;
import com.obrien.picesmart.utils.DTO.ReviewDTO;

import java.util.List;

public interface ReviewService {
    void createReview(ReviewDTO reviewDTO, long productId, long userId);
    void updateReview(ReviewDTO reviewDTO,long reviewI);
    void deleteReview(long reviewId);
    ReviewDTO getReviewDTO(long reviewId);
    Review getReview(long reviewId);
    List<Review> getReviews(long productId, int pageable, int size);
    long getTotalProductReviews(long productId);
}

