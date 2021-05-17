package com.shubh.service;

import java.util.List;

import com.shubh.model.Author;

public interface AuthorService {
	
	public Integer saveAuthor(Author author);
	
	public Author getOneAuthor(Integer authId);
	
	public List<Author> getAllAuthors();
	
	public void deleteOneAuthor(Integer authId);

}
