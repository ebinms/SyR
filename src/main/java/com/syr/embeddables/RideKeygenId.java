package com.syr.embeddables;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * @author Ebin
 * @on 03-Oct-2018
 * @version 0.0
 */
@Embeddable
public class RideKeygenId implements Serializable{

	private static final long serialVersionUID = 5618871670136145752L;

	@Column(name = "ride_req_type",nullable = false,length = 4)
	private String rideRequestType;
	
	@Column(name = "year",nullable = false)
	private int year;
	
	public RideKeygenId() {
	}

	public RideKeygenId(String rideRequestType, int year) {
		super();
		this.rideRequestType = rideRequestType;
		this.year = year;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((rideRequestType == null) ? 0 : rideRequestType.hashCode());
		result = prime * result + year;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RideKeygenId other = (RideKeygenId) obj;
		if (rideRequestType == null) {
			if (other.rideRequestType != null)
				return false;
		} else if (!rideRequestType.equals(other.rideRequestType))
			return false;
		if (year != other.year)
			return false;
		return true;
	}
	
	
}
