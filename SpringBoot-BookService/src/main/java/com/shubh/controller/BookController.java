package com.shubh.controller;

import java.util.List;
import java.util.Optional;

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

import com.shubh.consume.AuthorConsumer;
import com.shubh.exception.BookNotFoundException;
import com.shubh.model.Author;
import com.shubh.model.Book;
import com.shubh.repository.BookRepository;

@RestController
@RequestMapping("/book")
public class BookController {

	@Autowired
	private BookRepository repo;

	@Autowired
	private AuthorConsumer consumer;

	@PostMapping("/save")
	public ResponseEntity<String> registerBook(@Valid @RequestBody Book book) {
		Author author = consumer.getOneAuthor(book.getAuthor().getAuthorId());
		book.setAuthor(author);
		Integer bookId = repo.save(book).getBookId();

		return new ResponseEntity<String>("Book saved with bookId=>" + bookId, HttpStatus.CREATED);
	}

	@GetMapping("/getAllBook")
	public ResponseEntity<?> getAllBookDetails() {

		ResponseEntity<?> res = null;
		List<Book> allBook = repo.findAll();
		if (!allBook.isEmpty())
			res = new ResponseEntity<List<Book>>(allBook, HttpStatus.OK);

		else
			throw new BookNotFoundException("Books are not available");

		return res;
	}

	@GetMapping("/getOneBook/{bookId}")
	public ResponseEntity<?> getOneBook(@PathVariable Integer bookId) {

		ResponseEntity<?> res = null;
		Optional<Book> opt = repo.findById(bookId);
		if (opt.isPresent())
			res = new ResponseEntity<Book>(opt.get(), HttpStatus.OK);

		else
			throw new BookNotFoundException("Book not exist");

		return res;
	}

	@DeleteMapping("/delete/{bookId}")
	public ResponseEntity<?> deleteOneBook(@PathVariable Integer bookId) {
		ResponseEntity<?> res = null;
		Optional<Book> opt = repo.findById(bookId);
		if (opt.isPresent()) {
			repo.deleteById(bookId);
			res = new ResponseEntity<String>("Book deleted successfully with bookId=>" + bookId, HttpStatus.OK);
		} else
			throw new BookNotFoundException("Book not exist");

		return res;
	}
}
