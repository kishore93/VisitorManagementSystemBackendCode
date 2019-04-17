package com.infy.visitor.management.domin;

import java.io.Serializable;

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
public class VisitorDetail implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@ApiModelProperty(hidden=true)
	private long id;
	private String visitorName;
	private long visitorMobile;
	private long visitorTypeId;
	private String approverEmail;
	private long reffererId;
	@ApiModelProperty(hidden=true)
	private String locationName;
	private long locationId;
	@ApiModelProperty(hidden=true)
	private String qrCodeUrl;
	@ApiModelProperty(hidden=true)
	private String qrHtml;
	@ApiModelProperty(hidden=true)
	private boolean isPreApproved;
	
	
}
