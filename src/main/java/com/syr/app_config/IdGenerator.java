package com.syr.app_config;

import java.util.UUID;

/**
 * @author Ebin
 *
 */
public class IdGenerator {

	/**
	 * @return
	 */
	public static String createId() {
		UUID uuid	=  UUID.randomUUID();
		return uuid.toString();
	}
}
