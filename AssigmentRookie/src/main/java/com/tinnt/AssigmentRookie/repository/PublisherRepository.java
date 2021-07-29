package com.tinnt.AssigmentRookie.repository;

import com.tinnt.AssigmentRookie.entity.Publisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PublisherRepository extends JpaRepository<Publisher, Long> {
    @Query(value = "select * from publisher p where p.publisher_name = ?1", nativeQuery = true)
    Optional<Publisher> findByName(String name);

    @Query(value = "select * from publisher p where p.publisher_name like %?1%", nativeQuery = true)
    Page<Publisher> searchPublisher(String name, Pageable pageable);

    @Query(value = "select * from publisher ", nativeQuery = true)
    Page<Publisher> getAllPublisher(Pageable pageable);
}
