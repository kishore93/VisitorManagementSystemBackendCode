package com.infy.visitor.management.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.infy.visitor.management.entity.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

	@Query("SELECT u FROM Employee u WHERE LOWER(u.employeeCode) = LOWER(:employeeCode)")
	Employee findByEmployeeCode(@Param("employeeCode") Long employeeCode);

	@Query("SELECT u FROM Employee u WHERE u.employeeCode = :employeeCode and  u.password = :password")
	Employee auth(@Param("employeeCode") Long employeeCode, @Param("password") String password);

}