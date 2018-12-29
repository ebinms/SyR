package com.syr.defaults;

/**
 * @author Ebin
 * @on 07-Oct-2018
 * @version 0.0
 */
public enum SyrPaymentMode {

	CASH("CSH"), BANK("BNK"), WALLET("WLT"),ANY("ANY");

	private String mode;

	public String Get() {
		return this.mode;
	}

	SyrPaymentMode(String mode) {
		this.mode = mode;
	}
}
