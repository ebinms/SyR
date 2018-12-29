package com.syr.web;

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
import com.syr.services.MasterServices;

/**
 * @author Ebin
 * @on 03-Oct-2018
 * @version 0.0
 */
@RestController
@RequestMapping("/${url.security}/master")
public class MasterController {

	@Autowired
	MasterServices mServices;
	
	ApiResponse apiResponse;
	
	@RequestMapping(value = "/vehicle/type/list", method = RequestMethod.GET, produces = { "application/JSON" })
	public ResponseEntity<ApiResponse> listVehicleTypes(@RequestHeader(value = "app_lang") String lang) throws Exception {
		apiResponse = new ApiResponse(1, mServices.listVehicleTypes(),
				"Success", HttpStatus.OK.value(), lang);
		return new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/vehicle/company/list", method = RequestMethod.GET, produces = { "application/JSON" })
	public ResponseEntity<ApiResponse> listVehicleCompany(@RequestHeader(value = "app_lang") String lang) throws Exception {
		apiResponse = new ApiResponse(1, mServices.listVehicleCompanies(),
				"Success", HttpStatus.OK.value(), lang);
		return new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/vehicle/model/list", method = RequestMethod.GET, produces = { "application/JSON" })
	public ResponseEntity<ApiResponse> listVehicleModel(@RequestHeader(value = "app_lang") String lang) throws Exception {
		apiResponse = new ApiResponse(1, mServices.listVehicleModels(),
				"Success", HttpStatus.OK.value(), lang);
		return new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/vehicle/model/list/by-company-and-type", method = RequestMethod.POST, produces = { "application/JSON" })
	public ResponseEntity<ApiResponse> listVehicleModel(@RequestHeader(value = "app_lang") String lang,@RequestBody ObjectNode on) throws Exception {
		apiResponse = new ApiResponse(1, mServices.listVehicleModels(on.get("typeId").asLong(), on.get("companyId").asLong()),
				"Success", HttpStatus.OK.value(), lang);
		return new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.OK);
	}
}
