package com.example.onlineshopping.service;

import com.example.onlineshopping.domain.Review;

import java.util.List;


public interface ReviewService {

    List<Review> getAllReviews();

    Review getReviewById(long id);

    Review approveReview(long id);
}
