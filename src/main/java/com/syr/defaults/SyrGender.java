package com.syr.defaults;

/**
 * @author Ebin
 * @on 02-Oct-2018
 * @version 0.0
 */
public enum SyrGender {

	MALE("M"), FEMALE("F"), TRANSGENDER("T"), ALL("A");

	private String gender;

	public String Get() {
		return this.gender;
	}

	SyrGender(String gender) {
		this.gender = gender;
	}
}
