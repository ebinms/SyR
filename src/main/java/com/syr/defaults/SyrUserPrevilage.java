package com.syr.defaults;

/**
 * @author Ebin
 * @on 26-Sep-2018
 * @version 0.0
 */
public enum SyrUserPrevilage {

	NORMAL("NU"), SILVER("SU"), GOLD("GU"), PREMIUM("PU");

	private String type;

	public String Type() {
		return this.type;
	}

	SyrUserPrevilage(String type) {
		this.type = type;
	}
}
