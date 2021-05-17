package com.shubh.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.shubh.consume.AuthorConsumer;
import com.shubh.model.Author;
import com.shubh.model.Book;
import com.shubh.repository.BookRepository;

@WebMvcTest(BookController.class)
@TestMethodOrder(OrderAnnotation.class)
public class BookControllerTest {

	@Autowired
	private MockMvc mockMvc;
	@MockBean
	private BookRepository repo;

	@MockBean
	private AuthorConsumer consumer;

	ObjectMapper objMap = new ObjectMapper();
	List<Book> listBook = List.of(new Book(1, "java", 500.0, new Author(101, "shubham", 9479605143l, "khandwa")),
			new Book(2, "spring", 1000.0, new Author(102, "vishal", 9826464636l, "indore")));

	@Test
	@Order(1)
	public void registerBookTest() throws Exception {
		Author author = new Author(101, "shubham", 9479605143l, "khandwa");
		Mockito.when(consumer.getOneAuthor(author.getAuthorId())).thenReturn(author);
		Mockito.when(repo.save(listBook.get(0))).thenReturn(listBook.get(0));

		String payload = objMap.writeValueAsString(listBook.get(0));
		MvcResult result = mockMvc
				.perform(post("/book/save").content(payload).contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(status().isCreated()).andReturn();
		int status = result.getResponse().getStatus();
		assertEquals(201, status);
	}

	@Test
	@Order(2)
	public void getAllBookTest() throws Exception {
		List<Book> listEmp = listBook;
		String response = objMap.writeValueAsString(listEmp);
		Mockito.when(repo.findAll()).thenReturn(listEmp);
		MvcResult result = mockMvc.perform(get("/book/getAllBook").contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(status().isOk()).andReturn();
		String empRes = result.getResponse().getContentAsString();
		assertEquals(response, empRes);
		assertEquals(200, result.getResponse().getStatus());

	}

	@Test
	@Order(3)
	public void getOneBookTest() throws Exception {
		Optional<Book> optional = Optional
				.of(new Book(1, "java", 500.0, new Author(101, "shubham", 9479605143l, "khandwa")));
		String response = objMap.writeValueAsString(listBook.get(0));
		Mockito.when(repo.findById(1)).thenReturn(optional);
		MvcResult result = mockMvc.perform(get("/book/getOneBook/1").contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(status().isOk()).andReturn();
		assertEquals(response, result.getResponse().getContentAsString());
		assertEquals(200, result.getResponse().getStatus());

	}

	@Test
	@Order(4)
	public void deleteOneBookTest() throws Exception {
		Optional<Book> optional = Optional
				.of(new Book(1, "java", 500.0, new Author(101, "shubham", 9479605143l, "khandwa")));
		Mockito.when(repo.findById(1)).thenReturn(optional);
		mockMvc.perform(delete("/book/delete/1")).andExpect(status().isOk());
		Mockito.verify(repo, times(1)).deleteById(1);
	}

}
