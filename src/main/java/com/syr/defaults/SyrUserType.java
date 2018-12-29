package com.syr.defaults;

/**
 * @author Ebin
 * @on 26-Sep-2018
 * @version 0.0
 */
public enum SyrUserType {

	NORMAL("NU"), ADMIN("AD");

	private String type;

	public String Type() {
		return this.type;
	}

	SyrUserType(String type) {
		this.type = type;
	}
}
