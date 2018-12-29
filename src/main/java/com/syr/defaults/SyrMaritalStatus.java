package com.syr.defaults;

/**
 * @author Ebin
 * @on 26-Sep-2018
 * @version 
 */
public enum SyrMaritalStatus {
	
	MARRIED("M"), SINGLE("S"), LIVEIN("L");

	private String status;

	public String Status() {
		return this.status;
	}

	SyrMaritalStatus(String status) {
		this.status = status;
	}
}
