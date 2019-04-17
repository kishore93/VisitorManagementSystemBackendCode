package com.infy.visitor.management.domin;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class EmployeeDetail {
	private String fullName;
	public Long employeeCode;
	public String roles;
}