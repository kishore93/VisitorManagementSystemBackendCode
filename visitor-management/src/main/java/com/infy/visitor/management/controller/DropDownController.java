package com.infy.visitor.management.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/dropdwon")
public interface DropDownController {
	
	@GetMapping("/cardTypes")
	ResponseEntity<?> cardTypes();
	@GetMapping("/visitorTypes")
	ResponseEntity<?> visitorTypes();
	@GetMapping("/locations")
	ResponseEntity<?> locations();
	
	@GetMapping("/all")
	ResponseEntity<?> dropdown();
	

}
