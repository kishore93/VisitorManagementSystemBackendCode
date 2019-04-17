package com.infy.visitor.management.event;

import lombok.*;
import lombok.experimental.Accessors;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
//@Data
@Setter
@Getter
@Builder
@Accessors(chain = true)
@ToString
public class EmailNotificationEvent {

	private List<String> toList;

	private String fromAddress;

	private List<String> ccList;
	
	private String subject;

	private String body;

	private String filePath;

	private Boolean isCCList;

}