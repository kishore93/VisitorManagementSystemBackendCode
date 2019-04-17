package com.infy.visitor.management.controller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.infy.visitor.management.controller.AuthenticationController;
import com.infy.visitor.management.domin.Crendetial;
import com.infy.visitor.management.domin.EmployeeDetail;
import com.infy.visitor.management.exception.AuthFailedException;
import com.infy.visitor.management.service.EmployeeService;

@RestController
public class AuthenticationControllerImpl implements AuthenticationController {

	@Autowired
	private EmployeeService employeeService;

	@Override
	@PostMapping
	public ResponseEntity<?> login(@RequestBody Crendetial crendetial) throws AuthFailedException {
		return new ResponseEntity<EmployeeDetail>(employeeService.auth(crendetial), HttpStatus.OK);

	}

}
