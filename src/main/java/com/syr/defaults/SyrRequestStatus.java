package com.syr.defaults;

/**
 * @author Ebin
 * @on 07-Oct-2018
 * @version 0.0
 */
public enum SyrRequestStatus {

	REQUESTED("Q"),ACCEPTED("A"),REJECTED("R");
	
	private String type;

	public String Get() {
		return this.type;
	}

	SyrRequestStatus(String type) {
		this.type = type;
	}
}
