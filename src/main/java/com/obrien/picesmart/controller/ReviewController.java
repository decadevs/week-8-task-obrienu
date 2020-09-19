package com.obrien.picesmart.controller;


import com.obrien.picesmart.services.ReviewService;
import com.obrien.picesmart.utils.DTO.ReviewDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ReviewController {
    @Autowired
    private ReviewService reviewService;

    @PostMapping(path="/review/add/{productId}/{userId}")
    public String addReview(@PathVariable long productId, @PathVariable long userId, @ModelAttribute("review") ReviewDTO reviewDTO){
        reviewService.createReview(reviewDTO, productId, userId);
        return "redirect:/product/view/" + productId + "/1";
    }

    @PostMapping(path = "/review/delete/{reviewId}/{productId}")
    public String deleteReview(@PathVariable long reviewId, @PathVariable long productId ){
        reviewService.deleteReview(reviewId);
        return "redirect:/product/view/" + productId + "/1" ;
    }
}
