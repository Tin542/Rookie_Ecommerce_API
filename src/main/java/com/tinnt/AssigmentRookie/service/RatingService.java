package com.tinnt.AssigmentRookie.service;

import com.tinnt.AssigmentRookie.entity.Rating;

import java.util.List;
import java.util.Optional;

public interface RatingService {

    public Rating addRating(Rating rate);

    public void deleteRating(long rateID);

    public Rating editRating(Rating rate);

    public Optional<Rating> getRatingById(long rateID);

    public List<Rating> getRatingByBookID(Long bookid);
}
