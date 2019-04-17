package com.infy.visitor.management.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;

import com.infy.visitor.management.domin.Crendetial;
import com.infy.visitor.management.exception.AuthFailedException;

@RequestMapping("/login")
public interface AuthenticationController {

	ResponseEntity<?> login(Crendetial Crendetial)throws AuthFailedException;
	
	
	
}

