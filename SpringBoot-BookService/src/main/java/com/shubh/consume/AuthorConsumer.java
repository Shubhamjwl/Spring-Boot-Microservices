package com.shubh.consume;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.shubh.model.Author;

@FeignClient("AUTHOR-SERVICE")
public interface AuthorConsumer {
	
	@GetMapping("/author/getOneAuthor/{authId}")
	public Author getOneAuthor(@PathVariable Integer authId);

}
