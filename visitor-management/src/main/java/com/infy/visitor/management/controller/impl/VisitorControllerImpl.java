package com.infy.visitor.management.controller.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.infy.visitor.management.controller.VisitorController;
import com.infy.visitor.management.domin.VisitorDetail;
import com.infy.visitor.management.entity.Employee;
import com.infy.visitor.management.service.EmployeeService;
import com.infy.visitor.management.service.VisitorService;

@RestController
public class VisitorControllerImpl implements VisitorController {

	@Autowired
	private VisitorService visitorService;
	
	@Autowired
	private EmployeeService employeeService;

	@Override
	@GetMapping("/refferBy/{employeeCode}")
	public ResponseEntity<?> getVisitor(@PathVariable(name = "employeeCode") Long employeeCode) {
		Employee employee=employeeService.findByEmployeeCode(employeeCode);
		if(employee!=null) {
			if(employee.getRole().equalsIgnoreCase("SECURITY")) {
				return new ResponseEntity<>(visitorService.findAll(), HttpStatus.OK);
			}
			if(employee.getRole().equalsIgnoreCase("EMP")) {
				return new ResponseEntity<>(visitorService.visitorsRefferdBy(employeeCode), HttpStatus.OK);
			}
		}
		
		return new ResponseEntity<>(null, HttpStatus.OK);
	}

	@Override
	@PostMapping
	public ResponseEntity<?> addVisitor(@RequestBody List<VisitorDetail> entities) {
		return new ResponseEntity<>(visitorService.addVisitor(entities), HttpStatus.ACCEPTED);
	}

	@Override
	@PutMapping("/approve/{id}/{isPreApproved}")
	public ResponseEntity<?> approveVisitor(@PathVariable(name = "id") Long id,@PathVariable(name = "isPreApproved") boolean isPreApproved) {
		return new ResponseEntity<Boolean>(visitorService.verifyVisitor(id,isPreApproved), HttpStatus.OK);
	}


}
