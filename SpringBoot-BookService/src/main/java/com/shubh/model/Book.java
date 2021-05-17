package com.shubh.model;

import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document
public class Book {
	
	@Id
	@NotNull(message = "Book id is mandatory field")
	private Integer bookId;
	@NotNull(message = "Book name is mandatory field")
	private String bookName;
	@NotNull(message = "Price is mandatory field")
	private Double price;
	@NotNull(message = "Author detail is mandatory field")
	private Author author;

}
