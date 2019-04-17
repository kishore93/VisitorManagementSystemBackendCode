package com.infy.visitor.management.service;

import com.infy.visitor.management.domin.Crendetial;
import com.infy.visitor.management.domin.EmployeeDetail;
import com.infy.visitor.management.entity.Employee;
import com.infy.visitor.management.exception.AuthFailedException;

public interface EmployeeService {
	Employee findByEmployeeCode(Long employeeCode);
	EmployeeDetail auth(Crendetial crendetial) throws AuthFailedException;
	

}
