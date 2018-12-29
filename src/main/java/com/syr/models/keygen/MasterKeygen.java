/**
 * 
 */
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
@Table(name = "syr_master_keygen")
public class MasterKeygen {
	
	@Id
	@Column(name = "master_type",nullable = false,length = 4)
	private String masterType;
	
	@Column(name = "year",nullable = false)
	private int year;
	
	@Column(name = "key_value",nullable = false)
	private long keyValue;

	public String getMasterType() {
		return masterType;
	}

	public void setMasterType(String masterType) {
		this.masterType = masterType;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
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
		result = prime * result + ((masterType == null) ? 0 : masterType.hashCode());
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
		MasterKeygen other = (MasterKeygen) obj;
		if (keyValue != other.keyValue)
			return false;
		if (masterType == null) {
			if (other.masterType != null)
				return false;
		} else if (!masterType.equals(other.masterType))
			return false;
		if (year != other.year)
			return false;
		return true;
	}

}
