package com.syr.utils;

import java.util.Properties;

/**
 * @author Ebin
 * @on 27-Sep-2018
 * @version 0.0
 */
public class SyrResponse {

	/**
	 * @param responseCode
	 * @param message
	 * @param language
	 * @return
	 */
	public static String getMessage(int responseCode, String message, String language) {

		Properties prop = responseCode > 0 ? (SyrUtil
				.getProperty("response_msg_" + (language == null || language.trim().equals("") ? "en" : language)))
				: null;

		return responseCode <= 0 ? message
				: (prop == null ? message
						: prop.getProperty((responseCode <= 0) ? message : String.valueOf(responseCode),
								message));
	}

}
