package com.tinnt.AssigmentRookie.service.impl;

import com.tinnt.AssigmentRookie.entity.Rating;
import com.tinnt.AssigmentRookie.repository.RatingRepository;
import com.tinnt.AssigmentRookie.service.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RatingServiceImpl implements RatingService {

    private RatingRepository rateRepository;

    @Autowired
    public RatingServiceImpl(RatingRepository rateRepository) {
        this.rateRepository = rateRepository;
    }

    @Override
    public Rating addRating(Rating rate) {
        return rateRepository.save(rate);
    }

    @Override
    public void deleteRating(long rateID) {
        rateRepository.deleteById(rateID);
    }

    @Override
    public Rating editRating(Rating rate) {
        return rateRepository.save(rate);
    }

    @Override
    public Optional<Rating> getRatingById(long rateID) {
        return rateRepository.findById(rateID);
    }

    @Override
    public List<Rating> getRatingByBookID(Long bookid) {
        return rateRepository.getByBookID(bookid);
    }
}
