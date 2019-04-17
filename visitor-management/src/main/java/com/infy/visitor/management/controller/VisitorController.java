package com.infy.visitor.management.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;

import com.infy.visitor.management.domin.VisitorDetail;

@RequestMapping("/visitors")
public interface VisitorController {

	ResponseEntity<?> getVisitor(Long refferby);

	ResponseEntity<?> addVisitor(List<VisitorDetail> entities);

	ResponseEntity<?> approveVisitor(Long id,boolean isPreApproved);

}
