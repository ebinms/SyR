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

import com.syr.models.ErrorLog;
import com.syr.services.impl.ErrorServicesImpl;

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
	
	@Autowired
	ErrorServicesImpl errServices;
	
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
		
		try{
			ErrorLog eLog	= new ErrorLog("#", "#", request.getRequestURL().toString(), String.valueOf(error.getResponseCode()), e.getMessage(), error.getMessage(), "#",error.getLanguage());
			errServices.saveErrorLog(eLog);
		}catch (Exception ex) {
			System.out.println("Error while saving error log---"+e.getMessage());
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
