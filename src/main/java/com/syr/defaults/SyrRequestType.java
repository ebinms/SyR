package com.syr.defaults;

/**
 * @author Ebin
 * @on 03-Oct-2018
 * @version 0.0
 */
public enum SyrRequestType {

	SEND("S"),RECEIVE("R"),BOTH("B"),GUEST("G"),HOST("H");
	
	private String type;

	public String Get() {
		return this.type;
	}

	SyrRequestType(String type) {
		this.type = type;
	}
}
