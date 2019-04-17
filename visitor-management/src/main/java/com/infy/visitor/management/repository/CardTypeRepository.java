package com.infy.visitor.management.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.infy.visitor.management.entity.CardType;

@Repository
public interface CardTypeRepository extends JpaRepository<CardType, Long> {

	@Query("FROM CardType cardType WHERE cardType.id = :id")
	CardType findByCardId(@Param("id") Long id);
}