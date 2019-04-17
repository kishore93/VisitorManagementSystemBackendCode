package com.infy.visitor.management.entity;

import java.io.Serializable;

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
public class VisitorDetail implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private long id;
	private String visitorName;
	private long visitorMobile;
	private long visitorTypeId;
	private String approverEmail;
	private int reffererId;
	private String locationName;
	private long locationId;

	private String qrCodeUrl;
	private String qrHtml;
	
	
}
