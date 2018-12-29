package com.syr.embeddables;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.syr.models.User;

/**
 * @author Ebin
 * @on 27-Sep-2018
 * @version 0.0
 */
@Embeddable
public class UserVehicleId implements Serializable{

	private static final long serialVersionUID = -8814578583889734236L;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	private User user;
	
	@Column(name = "registration_no",nullable = false,length = 100)
	private String registrationNo;
	
	/**
	 * 
	 */
	public UserVehicleId() {
		// TODO Auto-generated constructor stub
	}

	public UserVehicleId(User user, String registrationNo) {
		super();
		this.user = user;
		this.registrationNo = registrationNo;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((registrationNo == null) ? 0 : registrationNo.hashCode());
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
		UserVehicleId other = (UserVehicleId) obj;
		if (registrationNo == null) {
			if (other.registrationNo != null)
				return false;
		} else if (!registrationNo.equals(other.registrationNo))
			return false;
		if (user == null) {
			if (other.user != null)
				return false;
		} else if (!user.equals(other.user))
			return false;
		return true;
	}
}
