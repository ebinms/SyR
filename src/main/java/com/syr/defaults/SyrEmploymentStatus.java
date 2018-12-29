package com.syr.defaults;

/**
 * @author Ebin
 * @on 26-Sep-2018
 * @version 0.0
 */
public enum SyrEmploymentStatus {
	
	SALARIED("E"), UNEMPLOYED("U"), SELFEMPLOYED("I"), STUDENT("S");

	private String status;

	public String Status() {
		return this.status;
	}

	SyrEmploymentStatus(String status) {
		this.status = status;
	}
}
