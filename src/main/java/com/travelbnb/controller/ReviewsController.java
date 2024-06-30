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

import java.util.List;

@RestController
@RequestMapping("/api/v1/review")
public class ReviewsController {
    private ReviewServiceIMPL reviewServiceIMPL;
    private ReviewService reviewService ;

    public ReviewsController(ReviewServiceIMPL reviewServiceIMPL, ReviewService reviewService) {
        this.reviewServiceIMPL = reviewServiceIMPL;
        this.reviewService = reviewService;
    }
    //http://localhost:8080/api/v1/review/addReview
    @PostMapping("/addReview")
    public  ResponseEntity<?>addReview(
        @AuthenticationPrincipal AppUser user,@RequestParam long propertyId, @RequestBody Reviews reviews){
        Reviews reviews1 = reviewService.addReview(user, propertyId, reviews);

        return new ResponseEntity<>("Thank You for Reviewing",HttpStatus.CREATED);
    }
    //http://localhost:8080/api/v1/review?reviewsId=1
    @DeleteMapping
    public ResponseEntity<String>deleteReviews(@RequestParam long reviewsId){
        reviewServiceIMPL.deleteReviews(reviewsId);
    return new ResponseEntity<>("Record Deleted",HttpStatus.OK);
    }
    //http://localhost:8080/api/v1/review/1/1/1
    @PutMapping("/{reviewsId}/{userId}/{propertyId}")
    public ResponseEntity<Reviews>updateReviews(
            @PathVariable long reviewsId,
            @PathVariable long userId,
            @PathVariable long propertyId,
            @RequestBody Reviews reviews
     ){
        Reviews reviews1 = reviewServiceIMPL.updateReviews(reviewsId, userId,propertyId,reviews);
        return new ResponseEntity<>(reviews1,HttpStatus.OK);
    }
    //http://localhost:8080/api/v1/review
    @GetMapping
    public ResponseEntity<List<ReviewsDto>>getAllReviews(){
        List<ReviewsDto> allReviews = reviewServiceIMPL.getAllReviews();
        return new ResponseEntity<>(allReviews,HttpStatus.OK);
    }
    //http://localhost:8080/api/v1/review/getReviewsByUser
    @GetMapping("/getReviewsByUser")
    public ResponseEntity<List<ReviewsDto>>findByUserReviews(@AuthenticationPrincipal AppUser user){
        List<ReviewsDto> byUserReviews = reviewServiceIMPL.findByUserReviews(user);
        return new ResponseEntity<>(byUserReviews,HttpStatus.OK);
    }
}
