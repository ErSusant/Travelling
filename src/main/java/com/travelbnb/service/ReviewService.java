package com.travelbnb.service;

import com.travelbnb.dto.AppUserDto;
import com.travelbnb.dto.ReviewsDto;
import com.travelbnb.entity.AppUser;
import com.travelbnb.entity.Property;
import com.travelbnb.entity.Reviews;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ReviewService {

    Reviews addReview(AppUser user, long propertyId, Reviews reviews);
    void deleteReviews(long reviewsId);
    Reviews updateReviews(long reviewsId,long userId,long propertyId,Reviews ereviews);
    List<ReviewsDto>getAllReviews();
    List<ReviewsDto>findByUserReviews(AppUser user);


}
