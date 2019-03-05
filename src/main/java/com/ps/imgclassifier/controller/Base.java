package com.ps.imgclassifier.controller;

import java.time.LocalDateTime;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Base {

	@GetMapping("/")
	public String indexPage() {
		return "<h1>Initial page, actual date 'n' time: " + LocalDateTime.now() + "</h1>";
	}
}
