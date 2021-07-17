package com.tinnt.AssigmentRookie.repository;

import com.tinnt.AssigmentRookie.entity.Rating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RatingRepository extends JpaRepository<Rating, Long> {

    @Query(value = "SELECT * FROM rating where book_id = ?1", nativeQuery = true)
    public List<Rating> getByBookID(Long bookid);
}
