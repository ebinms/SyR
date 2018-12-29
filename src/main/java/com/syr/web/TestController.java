package com.syr.web;

import org.json.simple.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.syr.app_config.ApiResponse;
import com.syr.defaults.SyrDefault;

/**
 * @author Ebin
 * @on 27-Sep-2018
 * @version 0.0
 */
@RestController
@RequestMapping(value = "/${url.security}/test")
public class TestController {
	
	ApiResponse apiResponse;

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/date")
	public Object testDate(@RequestHeader(value = "app_lang") String lang,@RequestParam("rCode") int rCode) throws Exception {
		
		JSONObject json	= new JSONObject();
		json.put("CUrr", SyrDefault.CURRENT_DATE());
		json.put("pd", SyrDefault.PAST_DEF_DATE);
		json.put("fd", SyrDefault.FUTURE_DEF_DATE);
		
		System.out.println("Lang........................."+lang);
		apiResponse	 = new ApiResponse(rCode, json, "Success", HttpStatus.OK.value(), null);
		
		return new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.OK);
	}
}
