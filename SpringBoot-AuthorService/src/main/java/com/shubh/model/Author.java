package com.shubh.model;


import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document
public class Author {

	@Id
	@NotNull(message = "Author Id is mandatory field")
	private Integer authorId;
	@NotNull(message = "Author Name is mandatory field")
	private String authorName;
	@NotNull(message = "Mobile number is mandatory field")
	private Long mobileNumber;
	@NotNull(message = "Author location is mandatory field")
	private String authorLoc;
}
