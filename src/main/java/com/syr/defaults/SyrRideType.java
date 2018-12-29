package com.syr.defaults;

/**
 * @author Ebin
 * @on 02-Oct-2018
 * @version 0.0
 */
public enum SyrRideType {

	FREE("F"), PAID("P");

	private String status;

	public String Status() {
		return this.status;
	}

	SyrRideType(String status) {
		this.status = status;
	}
}
