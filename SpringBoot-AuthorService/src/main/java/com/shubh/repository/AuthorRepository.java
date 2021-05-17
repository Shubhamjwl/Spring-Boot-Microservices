package com.shubh.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.shubh.model.Author;

public interface AuthorRepository extends MongoRepository<Author, Integer> {

}
