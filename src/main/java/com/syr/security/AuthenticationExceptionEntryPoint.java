package com.syr.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;
import org.springframework.security.oauth2.provider.error.AbstractOAuth2SecurityExceptionHandler;
import org.springframework.security.oauth2.provider.error.DefaultWebResponseExceptionTranslator;
import org.springframework.security.oauth2.provider.error.WebResponseExceptionTranslator;
import org.springframework.security.web.AuthenticationEntryPoint;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.syr.app_config.ApiResponse;

/**
 * @author Ebin
 * @on 30-Sep-2018
 * @version 0.0
 */
public class AuthenticationExceptionEntryPoint extends AbstractOAuth2SecurityExceptionHandler
		implements AuthenticationEntryPoint,WebResponseExceptionTranslator<ApiResponse>{
	
	/** The default WebResponseExceptionTranslator. */
    private final WebResponseExceptionTranslator<OAuth2Exception> defaultTranslator = new DefaultWebResponseExceptionTranslator();

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.springframework.security.web.AuthenticationEntryPoint#commence(javax.
	 * servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse,
	 * org.springframework.security.core.AuthenticationException)
	 */
	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException arg)
			throws IOException, ServletException {

		System.out.println("Auth Entry Point.....................................");

		ApiResponse apiResponse = new ApiResponse(-1, null, arg.getMessage(), HttpStatus.UNAUTHORIZED.value(), null);

		response.setContentType("application/json");
		response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

		final ObjectMapper mapper = new ObjectMapper();
		mapper.writeValue(response.getOutputStream(), apiResponse);

	}

	@Override
	protected ResponseEntity<ApiResponse> enhanceResponse(ResponseEntity<?> result, Exception authException) {

		System.out.println("Auth Entry Point enhancement.....................................");

		ApiResponse apiResponse = new ApiResponse(-1, null, authException.getMessage(), HttpStatus.UNAUTHORIZED.value(),
				null);
		return new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.UNAUTHORIZED);
	}

	/* (non-Javadoc)
	 * @see org.springframework.security.oauth2.provider.error.WebResponseExceptionTranslator#translate(java.lang.Exception)
	 */
	@Override
	public ResponseEntity<ApiResponse> translate(Exception e) throws Exception {
		
		// Translate the exception with the default translator
        ResponseEntity<OAuth2Exception> defaultResponse = defaultTranslator.translate(e);
        
        ApiResponse aResponse	= new ApiResponse(-1, null, defaultResponse.getBody().getMessage(), defaultResponse.getStatusCodeValue(), "en");
        // Use the same status code as the default OAuth2 error
        return new ResponseEntity<ApiResponse>(aResponse, defaultResponse.getStatusCode());
	}

}
