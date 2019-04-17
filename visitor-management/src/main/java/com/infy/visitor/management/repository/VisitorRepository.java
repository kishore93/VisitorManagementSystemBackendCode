package com.infy.visitor.management.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.infy.visitor.management.entity.Visitor;
@Repository
public interface VisitorRepository extends JpaRepository<Visitor, Long> {

	@Query("FROM Visitor visitor WHERE visitor.reffererId = :reffererId")
	List<Visitor> refferBy(@Param("reffererId") long reffererId);

}