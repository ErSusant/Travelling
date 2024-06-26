package com.travelbnb.service;

import com.travelbnb.dto.AppUserDto;
import com.travelbnb.entity.AppUser;
import com.travelbnb.entity.Property;
import com.travelbnb.entity.Reviews;
import org.springframework.data.repository.query.Param;

public interface ReviewService {

    Reviews addReview(AppUser user, long propertyId, Reviews reviews);

}
