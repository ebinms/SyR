package com.syr.defaults;
/**
 * @author Ebin
 * @on 26-Sep-2018
 * @version 
 */
public enum SyrStatus {

	ACTIVE("A"), INACTIVE("I"), SUCCESS("S"), FAILURE("F"), PROCESSED("P"), CANCELLED("C"), REJECTED("R"), YES("Y"), NO(
			"N"),BLOCKED("B"),OPEN("O"),CLOSED("X");

	private String status;

	public String Status() {
		return this.status;
	}

	SyrStatus(String status) {
		this.status = status;
	}
}
