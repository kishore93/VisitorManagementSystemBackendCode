package com.infy.visitor.management.config;

public enum RestTemplateConstants {

	HEADER_KEY("Content-Disposition", " "),
	HEADER_VALUE("attachment; filename=ProductData.xml", " "),
	ALLOW_ORIGIN("Access-Control-Allow-Origin","*"),
	ALLOW_METHODS("Access-Control-Allow-Methods", "POST, GET,PUT, OPTIONS, DELETE"),
	MAX_AGE("Access-Control-Max-Age","3600"),
	ALLOW_CREDENTIALS("Access-Control-Allow-Credentials", "true"),
	ALLOW_HEADERS("Access-Control-Allow-Headers", "content-type");

	private String name;
	private String value;

	RestTemplateConstants(final String name, final String value) {
		this.name = name;
		this.value = value;
	}

	public String getValue() {
		return value;
	}

	public String getName() {
		return name;
	}

	@Override
	public String toString() {
		return this.getValue();
	}

}