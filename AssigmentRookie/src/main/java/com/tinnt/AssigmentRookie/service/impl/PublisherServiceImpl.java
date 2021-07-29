package com.tinnt.AssigmentRookie.service.impl;

import com.tinnt.AssigmentRookie.constans.ErrorCode;
import com.tinnt.AssigmentRookie.entity.Category;
import com.tinnt.AssigmentRookie.entity.Publisher;
import com.tinnt.AssigmentRookie.exception.AddException;
import com.tinnt.AssigmentRookie.exception.NotFoundException;
import com.tinnt.AssigmentRookie.repository.PublisherRepository;
import com.tinnt.AssigmentRookie.service.PublisherService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PublisherServiceImpl implements PublisherService {

    private static final Logger LOGGER = LoggerFactory.getLogger(PublisherService.class);

    private PublisherRepository publisherRepository;

    @Autowired
    public PublisherServiceImpl(PublisherRepository publisherRepository) {
        this.publisherRepository = publisherRepository;
    }

    @Override
    public List<Publisher> getAllPublisher() throws NotFoundException {
        return publisherRepository.findAll();
    }

    @Override
    public Optional<Publisher> getPublisherByName(String name) throws NotFoundException{
        Optional<Publisher> optional = publisherRepository.findByName(name);
        if(optional.isEmpty()){
            LOGGER.info("Not exist Publisher with name: "+name);
            throw new NotFoundException("Cannot find Publisher with name: "+name);
        }
        return publisherRepository.findByName(name);
    }

    @Override
    public Optional<Publisher> getPublisherByID(long id) {
        Optional<Publisher> optional = publisherRepository.findById(id);
        if(optional.isEmpty()){
            LOGGER.info("Not exist Publisher with id: "+id);
            throw new NotFoundException("Cannot find Publisher with id: "+id);
        }
        return publisherRepository.findById(id);
    }

    @Override
    public Publisher addPublisher(Publisher publisher) {
        Optional<Publisher> optional = publisherRepository.findByName(publisher.getName());
        if(optional.isPresent()){
            LOGGER.info("Publisher is already existed !!");
            throw new AddException("Publisher is already existed !!");
        }
        return publisherRepository.save(publisher);
    }

    @Override
    public Publisher updatePublisher(Publisher publisher, long id) {
        Optional<Publisher> optional = publisherRepository.findById(id);
        if(optional.isEmpty()){
            LOGGER.info("Not exist Publisher with id: "+id);
            throw new NotFoundException("Cannot find Publisher with id: "+id);
        }
        Publisher publisherEntity = optional.get();
        publisherEntity.setId(id);
        publisherEntity.setCreate_date(publisherEntity.getCreate_date());
        publisherEntity.setDelete(publisher.isDelete());
        publisherEntity.setName(publisher.getName());
        return publisherRepository.save(publisherEntity);
    }

    @Override
    public Page<Publisher> searchPublisher(String name, Pageable pageable) {
        Page<Publisher> optional = publisherRepository.searchPublisher(name, pageable);
        if(optional.isEmpty()){
            LOGGER.info("Not exist Publisher with name: "+name);
            throw new NotFoundException("Not exist Publisher with name: "+name);
        }
        if(name.equals("")){
            return publisherRepository.getAllPublisher(pageable);
        }
        return publisherRepository.searchPublisher(name, pageable);
    }
}
