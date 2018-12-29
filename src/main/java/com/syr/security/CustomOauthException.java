package com.syr.security;

import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 * @author Ebin
 * @on 29-Sep-2018
 * @version 0.0
 */
@JsonSerialize(using = CustomOauthExceptionSerializer.class)
public class CustomOauthException extends OAuth2Exception{
	private static final long serialVersionUID = -4196786369620846694L;

	public CustomOauthException(String msg) {
		super(msg);
	}
}
