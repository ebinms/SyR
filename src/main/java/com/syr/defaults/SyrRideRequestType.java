package com.syr.defaults;

/**
 * @author Ebin
 * @on 02-Oct-2018
 * @version 0.0
 */
public enum SyrRideRequestType {
	INVITATION("I"), JOINREQUEST("N"),BOTH("B");

	private String rideType;

	public String Get() {
		return this.rideType;
	}

	SyrRideRequestType(String rideType) {
		this.rideType = rideType;
	}
}
