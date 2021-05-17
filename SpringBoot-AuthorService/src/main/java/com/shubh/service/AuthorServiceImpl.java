package com.shubh.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.shubh.exception.AuthorNotFoundException;
import com.shubh.model.Author;
import com.shubh.repository.AuthorRepository;

@Service
public class AuthorServiceImpl implements AuthorService {

	@Autowired
	private AuthorRepository authRepo;

	@Override
	public Integer saveAuthor(Author author) {
       
		return authRepo.save(author).getAuthorId();

	}

	@Override
	public Author getOneAuthor(Integer authId) {

		Optional<Author> opt = authRepo.findById(authId);
		if (opt.isPresent())
			return opt.get();
		else
			throw new AuthorNotFoundException("Author not exist");
	}

	public List<Author> getAllAuthors() {

		List<Author> authorList = authRepo.findAll();

		if (authorList.isEmpty())
			throw new AuthorNotFoundException("Authors are not available");
		else
			return authorList;
	}

	@Override
	public void deleteOneAuthor(Integer authId) {
		Optional<Author> oneAuthor = authRepo.findById(authId);
		if (oneAuthor.isPresent())
			authRepo.deleteById(oneAuthor.get().getAuthorId());
		else
			throw new AuthorNotFoundException("Author not exist");
	}

	
}
