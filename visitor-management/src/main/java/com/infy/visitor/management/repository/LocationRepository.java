package com.infy.visitor.management.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.infy.visitor.management.entity.Location;

@Repository
public interface LocationRepository extends JpaRepository<Location, Long> {
	@Query("FROM Location location WHERE location.id = :id")
	Location findByLocationId(@Param("id") Long id);
}
