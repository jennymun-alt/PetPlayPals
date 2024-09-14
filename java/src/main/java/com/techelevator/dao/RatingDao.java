package com.techelevator.dao;

import com.techelevator.model.Rating;

import java.util.List;

public interface RatingDao {

    public List<Rating> getPetsRatings(long petId);
    public void addRating(Rating rating);

}
