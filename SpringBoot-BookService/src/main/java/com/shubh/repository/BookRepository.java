package com.shubh.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.shubh.model.Book;

public interface BookRepository extends MongoRepository<Book, Integer> {

}
