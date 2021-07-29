package com.tinnt.AssigmentRookie.service;

import com.tinnt.AssigmentRookie.entity.Publisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface PublisherService {

    public List<Publisher> getAllPublisher();

    public Optional<Publisher> getPublisherByName(String name);

    public Optional<Publisher> getPublisherByID(long id);

    public Publisher addPublisher(Publisher publisher);

    public Publisher updatePublisher(Publisher publisher, long id);

    public Page<Publisher> searchPublisher(String name, Pageable pageable);
}
