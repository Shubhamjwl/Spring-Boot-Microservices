package com.shubh.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shubh.exception.AuthorNotFoundException;
import com.shubh.model.Author;
import com.shubh.service.AuthorService;

@RestController
@RequestMapping("/author")
public class AuthorController {

	@Autowired
	private AuthorService service;

	@PostMapping("/save")
	public ResponseEntity<String> registerAuthor(@Valid @RequestBody Author author) {
		ResponseEntity<String> res = null;
		try {
			Integer authId = service.saveAuthor(author);

			res = new ResponseEntity<String>("Author register with authId=>" + authId, HttpStatus.OK);
		} catch (Exception e) {
			res = new ResponseEntity<String>("Unable to save author data", HttpStatus.BAD_REQUEST);
		}
		return res;
	}

	@GetMapping("/getOneAuthor/{authId}")
	public ResponseEntity<?> getOneAuthor(@PathVariable Integer authId) {
		ResponseEntity<?> res = null;
		try {
			Author oneAuthor = service.getOneAuthor(authId);

			res = new ResponseEntity<Author>(oneAuthor, HttpStatus.OK);
		} catch (AuthorNotFoundException auth) {
			throw auth;
		} catch (Exception e) {
			res = new ResponseEntity<String>("Unable to get author", HttpStatus.BAD_REQUEST);
		}
		return res;
	}

	@GetMapping("/getAllAuthor")
	public ResponseEntity<?> getAllAuthor() {

		ResponseEntity<?> res = null;
		try {
			List<Author> listAuthor = service.getAllAuthors();

			res = new ResponseEntity<List<Author>>(listAuthor, HttpStatus.OK);
		} catch (AuthorNotFoundException auth) {
			throw auth;
		} catch (Exception e) {
			res = new ResponseEntity<String>("Unable to get author", HttpStatus.BAD_REQUEST);
		}
		return res;
	}

	@DeleteMapping("/delete/{authId}")
	public ResponseEntity<?> deleteOneAuthor(@PathVariable Integer authId) {
		ResponseEntity<?> res = null;
		try {
			service.deleteOneAuthor(authId);

			res = new ResponseEntity<String>("Author deleted successfully with authorId=>"+authId, HttpStatus.OK);
		} catch (AuthorNotFoundException auth) {
			throw auth;
		} catch (Exception e) {
			res = new ResponseEntity<String>("Unable to get author", HttpStatus.BAD_REQUEST);
		}
		return res;
	}
}
