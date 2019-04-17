package com.infy.visitor.management.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.infy.visitor.management.domin.Crendetial;
import com.infy.visitor.management.domin.EmployeeDetail;
import com.infy.visitor.management.entity.Employee;
import com.infy.visitor.management.exception.AuthFailedException;
import com.infy.visitor.management.repository.EmployeeRepository;
import com.infy.visitor.management.service.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;

	@Override
	public Employee findByEmployeeCode(Long employeeCode) {
		return employeeRepository.findByEmployeeCode(employeeCode);
	}

	@Override
	public EmployeeDetail auth(Crendetial crendetial) throws AuthFailedException{
		Employee employee=employeeRepository.auth(crendetial.getEmployeeCode(),crendetial.getPassword());
		if(employee!=null) {
			return fromEmployee(employee);
		}
		
		throw new AuthFailedException("Invalid Login");
		
	}
	
	private EmployeeDetail fromEmployee(Employee employee) {
		EmployeeDetail employeeDetail=new EmployeeDetail();
		employeeDetail.setEmployeeCode(employee.getEmployeeCode());
		employeeDetail.setFullName(employee.getFullName());
		employeeDetail.setRoles(employee.getRole());
		return employeeDetail;
	}

}
