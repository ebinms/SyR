package com.syr.models.keygen;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.syr.embeddables.RideKeygenId;

/**
 * @author Ebin
 * @on 03-Oct-2018
 * @version 0.0
 */
@Entity
@Table(name = "syr_ride_keygen")
public class RideKeygen {

	@EmbeddedId
	private RideKeygenId id;
	@Column(name = "key_value",nullable = false)
	private long keyValue;
	
	public RideKeygenId getId() {
		return id;
	}
	public void setId(RideKeygenId id) {
		this.id = id;
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
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + (int) (keyValue ^ (keyValue >>> 32));
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
		RideKeygen other = (RideKeygen) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (keyValue != other.keyValue)
			return false;
		return true;
	}
}
