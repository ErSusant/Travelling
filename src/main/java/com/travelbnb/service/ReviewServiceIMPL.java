package com.travelbnb.service;

import com.travelbnb.dto.ReviewsDto;
import com.travelbnb.entity.AppUser;
import com.travelbnb.entity.Property;
import com.travelbnb.entity.Reviews;
import com.travelbnb.exception.ResourceNotFound;
import com.travelbnb.repository.AppUserRepository;
import com.travelbnb.repository.PropertyRepository;
import com.travelbnb.repository.ReviewsRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReviewServiceIMPL implements ReviewService {


    private ReviewsRepository reviewsRepository;
    private PropertyRepository propertyRepository;

    public ReviewServiceIMPL(ReviewsRepository reviewsRepository, PropertyRepository propertyRepository) {
        this.reviewsRepository = reviewsRepository;
        this.propertyRepository = propertyRepository;
    }
    public Reviews dtoToEntity(ReviewsDto dto){
        Reviews review=new Reviews();
        review.setAppUser(dto.getAppUser());
        review.setDescription(dto.getDescription());
        review.setRatings(dto.getRatings());
        review.setProperty(dto.getProperty());
        return review;
    }
    public ReviewsDto entityToDto(Reviews reviews){
        ReviewsDto reviewsDto=new ReviewsDto();
        reviewsDto.setId(reviews.getId());
        reviewsDto.setRatings(reviews.getRatings());
        reviewsDto.setProperty(reviews.getProperty());
        reviewsDto.setDescription(reviews.getDescription());
        reviewsDto.setAppUser(reviews.getAppUser());
        return reviewsDto;
    }


    @Override
    public Reviews addReview(AppUser user, long propertyId, Reviews reviews) {
        Optional<Property> OpProperty = propertyRepository.findById(propertyId);
          if(OpProperty.isPresent()){
              Property property = OpProperty.get();
              if(reviewsRepository.findReviewByUser(property,user)!=null){
                  throw new IllegalStateException("Review Exists");

              }
              else{
                  Integer ratings = reviews.getRatings();
                  if (ratings < 1 || ratings > 5) {
                      throw new IllegalArgumentException("Rating should be between 1 and 5");
                  }
                  reviews.setAppUser(user);
                  reviews.setProperty(property);
                  Reviews save = reviewsRepository.save(reviews);
                  return save;
              }
          }else{
              throw new ResourceNotFound("PropertyId Not Found Id: "+propertyId);
          }
    }
        @Override
        public List<Reviews> findByUserReviews(AppUser user) {
            List<Reviews> byUserReviews = reviewsRepository.findByUserReviews(user);
            return byUserReviews;
        }
    }