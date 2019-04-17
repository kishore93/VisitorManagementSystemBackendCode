package com.infy.visitor.management.service;

import java.util.Map;

public interface VelocityService {
	String buildTemplate(Map<String, Object> model,String templateLocation) throws Exception;

}