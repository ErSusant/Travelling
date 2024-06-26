package com.travelbnb.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.travelbnb.entity.AppUser;
import com.travelbnb.entity.Property;
import jakarta.validation.constraints.Size;


public class ReviewsDto {
    private Long id;

@Size(min=0,max=5,message = "rating should be below 5")
    private String ratings;

    private String description;

    private Property property;

    private AppUser appUser;

    public AppUser getAppUser() {
        return appUser;
    }

    public void setAppUser(AppUser appUser) {
        this.appUser = appUser;
    }

    public Property getProperty() {
        return property;
    }

    public void setProperty(Property property) {
        this.property = property;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    public String getRatings() {
        return ratings;
    }
    public void setRatings(String ratings) {
        this.ratings = ratings;
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
