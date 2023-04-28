package com.cos.blog.test;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BlogControllerTest {
	
	@GetMapping("/test")
	public String hello() {
		return "/test";
	}

}
