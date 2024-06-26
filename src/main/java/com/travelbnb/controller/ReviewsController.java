package com.travelbnb.controller;

import com.travelbnb.dto.AppUserDto;
import com.travelbnb.dto.ReviewsDto;
import com.travelbnb.entity.AppUser;
import com.travelbnb.entity.Property;
import com.travelbnb.entity.Reviews;
import com.travelbnb.service.ReviewService;
import com.travelbnb.service.ReviewServiceIMPL;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/review")
public class ReviewsController {
    private ReviewServiceIMPL reviewServiceIMPL;
    private ReviewService reviewService ;

    public ReviewsController(ReviewServiceIMPL reviewServiceIMPL, ReviewService reviewService) {
        this.reviewServiceIMPL = reviewServiceIMPL;
        this.reviewService = reviewService;
    }
    //http://localhpost:8080/api/v1/review/addReview

    @PostMapping("/addReview")
    public  ResponseEntity<?>addReview(
        @AuthenticationPrincipal AppUser user,@RequestParam long propertyId, @RequestBody Reviews reviews){
        Reviews reviews1 = reviewService.addReview(user, propertyId, reviews);

          return new ResponseEntity<>("Thank You for Reviewing",HttpStatus.CREATED);
    }
}
