package com.infy.visitor.management.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@Accessors(chain = true)
@ToString
@EqualsAndHashCode
@Entity
public class CardType {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String card;
	public CardType(String card) {
		super();
		this.card = card;
	}

}
