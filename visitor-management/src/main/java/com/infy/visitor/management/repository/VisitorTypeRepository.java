package com.infy.visitor.management.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.infy.visitor.management.entity.VisitorType;
@Repository
public interface VisitorTypeRepository extends JpaRepository<VisitorType, Long> {
}
