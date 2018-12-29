package com.syr.embeddables;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.syr.models.Ride;
import com.syr.models.User;

/**
 * @author Ebin
 * @on 02-Oct-2018
 * @version 0.0
 */
@Embeddable
public class CoRiderEmbeddedId implements Serializable{

	private static final long serialVersionUID = 4402100096175698065L;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ride_no")
	private Ride ride;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	private User user;
	
	public CoRiderEmbeddedId() {
	}

	public CoRiderEmbeddedId(Ride ride, User user) {
		super();
		this.ride = ride;
		this.user = user;
	}

	public Ride getRide() {
		return ride;
	}

	public User getUser() {
		return user;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((ride == null) ? 0 : ride.hashCode());
		result = prime * result + ((user == null) ? 0 : user.hashCode());
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
		CoRiderEmbeddedId other = (CoRiderEmbeddedId) obj;
		if (ride == null) {
			if (other.ride != null)
				return false;
		} else if (!ride.equals(other.ride))
			return false;
		if (user == null) {
			if (other.user != null)
				return false;
		} else if (!user.equals(other.user))
			return false;
		return true;
	}
}
