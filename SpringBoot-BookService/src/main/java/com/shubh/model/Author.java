package com.shubh.model;

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
	private Integer authorId;
	private String authorName;
	private Long mobileNumber;
	private String authorLoc;
}
