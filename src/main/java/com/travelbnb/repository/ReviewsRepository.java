package com.travelbnb.repository;

import com.travelbnb.dto.ReviewsDto;
import com.travelbnb.entity.AppUser;
import com.travelbnb.entity.Property;
import com.travelbnb.entity.Reviews;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ReviewsRepository extends JpaRepository<Reviews, Long> {
    @Query("SELECT r FROM Reviews r WHERE r.property = :property AND r.appUser = :appUser")
    Reviews findReviewByUser(@Param("property") Property property, @Param("appUser") AppUser appUser);

    @Query("SELECT r FROM Reviews r WHERE r.appUser=:user")
    List<Reviews>findByUserReviews(@Param("user")AppUser user);

}