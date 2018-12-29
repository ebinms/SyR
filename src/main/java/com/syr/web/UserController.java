package com.syr.web;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.syr.app_config.ApiResponse;
import com.syr.models.User;
import com.syr.models.UserVehicle;
import com.syr.services.UserServices;

/**
 * @author Ebin
 * @on 28-Sep-2018
 * @version 0.
 */
@RestController
@RequestMapping(value = "/${url.security}/user")
public class UserController {

	ApiResponse apiResponse;

	@Autowired
	UserServices userServices;

	@RequestMapping(value = "/add", method = RequestMethod.POST, produces = { "application/JSON" })
	public ResponseEntity<ApiResponse> addUser(@RequestHeader(value = "app_lang") String lang,@Valid @RequestBody User user)
			throws Exception {
		apiResponse = new ApiResponse(1, userServices.addUser(user), "Success", HttpStatus.OK.value(), lang);
		return new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.OK);
	}

	@RequestMapping(value = "/get", method = RequestMethod.POST, produces = { "application/JSON" })
	public ResponseEntity<ApiResponse> getUserDetails(@RequestHeader(value = "app_lang") String lang,
			@RequestBody ObjectNode oNode) {
		apiResponse = new ApiResponse(1, userServices.getUserDetailsByUsername(oNode.get("username").asText()),
				"Success", HttpStatus.OK.value(), lang);
		return new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/get/by-id", method = RequestMethod.POST, produces = { "application/JSON" })
	public ResponseEntity<ApiResponse> getUserDetailsById(@RequestHeader(value = "app_lang") String lang,
			@RequestBody ObjectNode oNode) {
		apiResponse = new ApiResponse(1, userServices.getUserDetailsById(oNode.get("userId").asLong()),
				"Success", HttpStatus.OK.value(), lang);
		return new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/vehicle/add", method = RequestMethod.POST, produces = { "application/JSON" })
	public ResponseEntity<ApiResponse> addUserVehicle(@RequestHeader(value = "app_lang") String lang,
			@Valid @RequestBody UserVehicle uVehicle) throws Exception {
		apiResponse = new ApiResponse(1, userServices.addUserVehicle(uVehicle),
				"Success", HttpStatus.OK.value(), lang);
		return new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/vehicle/list", method = RequestMethod.POST, produces = { "application/JSON" })
	public ResponseEntity<ApiResponse> listUserVehicle(@RequestHeader(value = "app_lang") String lang,
			@RequestBody ObjectNode on) throws Exception {
		apiResponse = new ApiResponse(1, userServices.listUserVehicles(on.get("userId").asLong()),
				"Success", HttpStatus.OK.value(), lang);
		return new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/vehicle/get", method = RequestMethod.POST, produces = { "application/JSON" })
	public ResponseEntity<ApiResponse> getUserVehicle(@RequestHeader(value = "app_lang") String lang,
			@RequestBody ObjectNode on) throws Exception {
		apiResponse = new ApiResponse(1, userServices.getUserVehicle(on.get("vehicleId").asLong()),
				"Success", HttpStatus.OK.value(), lang);
		return new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.OK);
	}
}
