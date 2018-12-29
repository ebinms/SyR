package com.syr.app_config;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import com.syr.defaults.SyrDefault;
import com.syr.utils.SyrResponse;

/**
 * @author Ebin
 * @on 27-Sep-2018
 * @version 
 */
public class ApiResponse {
	
	private int responseCode;
	private Object responseBody;
	private String message;
	private int statusCode;
	private LocalDateTime responseTime;
	private String language;
	
	public ApiResponse() {
	}

	public ApiResponse(int responseCode, Object responseBody, String message, int statusCode,
			 String language) {
		super();
		this.responseCode = responseCode;
		this.responseBody = responseBody;
		this.message = SyrResponse.getMessage(responseCode, message, language);
		this.statusCode = statusCode;
		this.responseTime = SyrDefault.CURRENT_DATE_TIME();
		this.language = language;
	}

	public int getResponseCode() {
		return responseCode;
	}

	public Object getResponseBody() {
		return responseBody;
	}

	public String getMessage() {
		return message;
	}

	public int getStatusCode() {
		return statusCode;
	}

	public LocalDateTime getResponseTime() {
		return responseTime;
	}

	@JsonProperty(access = Access.WRITE_ONLY)
	public String getLanguage() {
		return language;
	}

}
