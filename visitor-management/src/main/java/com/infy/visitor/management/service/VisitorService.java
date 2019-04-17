package com.infy.visitor.management.service;

import java.util.List;

import com.infy.visitor.management.domin.VisitorDetail;

public interface VisitorService {

	List<VisitorDetail> addVisitor(List<VisitorDetail> entities);

	List<VisitorDetail> visitorsRefferdBy(long employeeCode);

	boolean verifyVisitor(Long id, boolean isPreApproved);

	List<VisitorDetail> findAll();

}
