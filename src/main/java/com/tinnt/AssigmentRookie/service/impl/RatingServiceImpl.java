package com.tinnt.AssigmentRookie.service.impl;

import com.tinnt.AssigmentRookie.constans.ErrorCode;
import com.tinnt.AssigmentRookie.entity.Account;
import com.tinnt.AssigmentRookie.entity.Rating;
import com.tinnt.AssigmentRookie.exception.NotFoundException;
import com.tinnt.AssigmentRookie.repository.RatingRepository;
import com.tinnt.AssigmentRookie.service.AccountService;
import com.tinnt.AssigmentRookie.service.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RatingServiceImpl implements RatingService {

    private RatingRepository rateRepository;
    private AccountService accService;

    @Autowired
    public RatingServiceImpl(RatingRepository rateRepository, AccountService accService) {
        this.rateRepository = rateRepository;
        this.accService = accService;
    }

    @Override
    public Rating addRating(Rating rate) throws NotFoundException {
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
