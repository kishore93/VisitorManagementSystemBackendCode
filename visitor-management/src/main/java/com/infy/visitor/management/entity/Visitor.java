package com.infy.visitor.management.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import io.swagger.annotations.ApiModelProperty;
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
public class Visitor {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@ApiModelProperty(hidden=true)
	private long id;
	private String visitorName;
	private long visitorMobile;
	private String idCardNumber;
	@ManyToOne
	private VisitorType visitorType;
	private long reffererId;
	private String approverEmail;
	private long locationId;
	private String qrCodeUrl;
	private boolean isPreApproved;
}
