package com.syr.app_config;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Ebin
 * @on 28-Sep-2018
 * @version 0.0
 */
@CrossOrigin("*")
@ControllerAdvice
@RestController
public class ExceptionControllerAdvice implements ErrorController {

	private static final String PATH = "/error";
	
	@Autowired
	HttpSession session;
	
	@ExceptionHandler(Throwable.class)
	public ApiResponse exceptionHandler(HttpServletRequest request,Throwable e) {
		
		ApiResponse error = null;
		
		if(e instanceof MethodArgumentNotValidException){
			System.out.println("Exception is a Method argument not valid exception...........................");
			MethodArgumentNotValidException mEx = (MethodArgumentNotValidException) e;
			BindingResult result = mEx.getBindingResult();
			FieldError fError = result.getFieldError();

			if (fError != null) {
				error	= new ApiResponse(0, null, fError.getDefaultMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value(), null);
			}
		}else{
			error = new ApiResponse(-1, null, e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value(), request.getHeader("app_lang"));
		}
		
		return error;
	}
	
	/* (non-Javadoc)
	 * @see org.springframework.boot.web.servlet.error.ErrorController#getErrorPath()
	 */
	@Override
	public String getErrorPath() {
		return PATH;
	}

}
