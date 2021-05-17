package com.shubh;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.shubh.model.Author;
import com.shubh.repository.AuthorRepository;
import com.shubh.service.AuthorService;

@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
class SpringBootAuthorServiceApplicationTests {

	@Autowired
	private AuthorService service;

	@MockBean
	private AuthorRepository repo;

	@Test
	@Order(1)
	public void registerAuthorTest() {
		Author author = new Author(101, "shubh", 9479605143l, "khandwa");
		Mockito.when(repo.save(author)).thenReturn(author);
		assertEquals(author.getAuthorId(), service.saveAuthor(author));
	}

	@Test
	@Order(2)
	public void getAllAuthorTest() {

		Mockito.when(repo.findAll()).thenReturn(Stream.of(new Author(101, "shubham", 9479605143l, "khandwa"),
				new Author(102, "vishal", 9826464636l, "Indore")).collect(Collectors.toList()));
		assertEquals(2, service.getAllAuthors().size());
	}

	@Test
	@Order(3)
	public void getOneAuthorTest() {
		Optional<Author> optional = Optional.of(new Author(101, "shubham", 9479605143l, "khandwa"));
		Mockito.when(repo.findById(101)).thenReturn(optional);
		assertEquals(optional.get(), service.getOneAuthor(101));
	}

	@Test
	@Order(4)
	public void deleteOneAuthorTest() {
		Optional<Author> optional = Optional
				.of(new Author(101, "shubham", 9479605143l, "khandwa"));
		Mockito.when(repo.findById(101)).thenReturn(optional);
		service.deleteOneAuthor(101);
		verify(repo,times(1)).deleteById(101);
	}
}
