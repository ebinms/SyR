package com.syr.models.keygen;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Ebin
 * @Createdon 01/08/2018
 * @version 0.0
 */
@Entity
@Table(name = "syr_user_keygen")
public class UserKeygen {
	@Id
	@Column(name = "user_type",nullable = false,length = 4)
	private String userType;
	
	@Column(name = "key_value",nullable = false)
	private long keyValue;

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public long getKeyValue() {
		return keyValue;
	}

	public void setKeyValue(long keyValue) {
		this.keyValue = keyValue;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (keyValue ^ (keyValue >>> 32));
		result = prime * result + ((userType == null) ? 0 : userType.hashCode());
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
		UserKeygen other = (UserKeygen) obj;
		if (keyValue != other.keyValue)
			return false;
		if (userType == null) {
			if (other.userType != null)
				return false;
		} else if (!userType.equals(other.userType))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "UserKeygen [userType=" + userType + ", keyValue=" + keyValue + "]";
	}
}
