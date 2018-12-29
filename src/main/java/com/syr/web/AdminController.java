package com.syr.web;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.syr.app_config.ApiResponse;
import com.syr.models.ApplicationClient;
import com.syr.models.VehicleCompany;
import com.syr.models.VehicleModel;
import com.syr.models.VehicleType;
import com.syr.services.Adminservices;

/**
 * @author apple
 * @on 30-Sep-2018
 * @version 
 */
@RestController
@RequestMapping("/${url.security}/admin")
public class AdminController {

	private ApiResponse apiResponse;
	
	@Autowired
	private Adminservices adminServices;
	
	@RequestMapping(value = "/business-key")
	public ApiResponse generateBusinessKey(){
		
		apiResponse	= new ApiResponse(1, adminServices.generateBusinessKey(), "Success", HttpStatus.OK.value(), null);
		
		return apiResponse;
	}
	
	@RequestMapping(value = "/password/encode")
	public ApiResponse encodePassword(@RequestParam("password") String password){
		
		apiResponse	= new ApiResponse(1, adminServices.encodePassword(password), "Success", HttpStatus.OK.value(), null);
		
		return apiResponse;
	}
	
	@RequestMapping(value = "/client/add")
	private ApiResponse addClient(@RequestBody ApplicationClient client) throws Exception{
		apiResponse	= new ApiResponse(1, adminServices.addApplicationClient(client), "Success", HttpStatus.OK.value(), null);
		
		return apiResponse;
	}
	
	@RequestMapping(value = "/client/update")
	private ApiResponse updateClient(@RequestBody ApplicationClient client) throws Exception{
		apiResponse	= new ApiResponse(1, adminServices.addApplicationClient(client), "Success", HttpStatus.OK.value(), null);
		
		return apiResponse;
	}
	
	@RequestMapping(value = "/client/list")
	private ApiResponse listClients() throws Exception{
		apiResponse	= new ApiResponse(1, adminServices.listAllClients(), "Success", HttpStatus.OK.value(), null);
		
		return apiResponse;
	}
	
	@RequestMapping(value = "/client/get")
	private ApiResponse getClient(@RequestBody ObjectNode on) throws Exception{
		apiResponse	= new ApiResponse(1, adminServices.getOneClient(on.get("clientId").asLong(1001)), "Success", HttpStatus.OK.value(), null);
		
		return apiResponse;
	}
	
	@RequestMapping(value = "/client/password/reset")
	private ApiResponse resetClientPassword(@RequestBody ObjectNode on) throws Exception{
		apiResponse	= new ApiResponse(1, adminServices.resetClientPassword(on.get("clientId").asLong(),on.get("oldPassword").asText(),on.get("newPassword").asText()), "Success", HttpStatus.OK.value(), null);
		
		return apiResponse;
	}
	
	@RequestMapping(value = "/client/password/forgot")
	private ApiResponse forgotClientPassword(@RequestBody ObjectNode on) throws Exception{
		apiResponse	= new ApiResponse(1, adminServices.forgotClientPassword(on.get("clientId").asLong()), "Success", HttpStatus.OK.value(), null);
		
		return apiResponse;
	}
	
	@RequestMapping(value = "/vehicle-type/add")
	private ApiResponse addVehicleType(@Valid @RequestBody List<VehicleType> vTypes) throws Exception{
		apiResponse	= new ApiResponse(1, adminServices.addVehicleTypes(vTypes), "Success", HttpStatus.OK.value(), null);
		
		return apiResponse;
	}
	
	@RequestMapping(value = "/vehicle-type/update")
	private ApiResponse updateVehicleType(@Valid @RequestBody List<VehicleType> vTypes) throws Exception{
		apiResponse	= new ApiResponse(1, adminServices.addVehicleTypes(vTypes), "Success", HttpStatus.OK.value(), null);
		
		return apiResponse;
	}
	
	@RequestMapping(value = "/vehicle-type/list")
	private ApiResponse listVehicleTypes() throws Exception{
		apiResponse	= new ApiResponse(1, adminServices.listVehicleTypes(), "Success", HttpStatus.OK.value(), null);
		
		return apiResponse;
	}
	
	@RequestMapping(value = "/vehicle-company/add")
	private ApiResponse addVehicleCompany(@Valid @RequestBody List<VehicleCompany> vCompanies) throws Exception{
		apiResponse	= new ApiResponse(1, adminServices.addVehicleCompanies(vCompanies), "Success", HttpStatus.OK.value(), null);
		
		return apiResponse;
	}
	
	@RequestMapping(value = "/vehicle-company/update")
	private ApiResponse updateVehicleCompanies(@Valid @RequestBody List<VehicleCompany> vCompanies) throws Exception{
		apiResponse	= new ApiResponse(1, adminServices.updateVehicleCompanies(vCompanies), "Success", HttpStatus.OK.value(), null);
		
		return apiResponse;
	}
	
	@RequestMapping(value = "/vehicle-company/list")
	private ApiResponse listVehicleCompanies() throws Exception{
		apiResponse	= new ApiResponse(1, adminServices.listVehicleCompanies(), "Success", HttpStatus.OK.value(), null);
		
		return apiResponse;
	}
	
	@RequestMapping(value = "/vehicle-model/add")
	private ApiResponse addVehicleModel(@Valid @RequestBody List<VehicleModel> vModels) throws Exception{
		apiResponse	= new ApiResponse(1, adminServices.addVehicleModels(vModels), "Success", HttpStatus.OK.value(), null);
		
		return apiResponse;
	}
	
	@RequestMapping(value = "/vehicle-model/update")
	private ApiResponse updateVehicleModel(@Valid @RequestBody List<VehicleModel> vModels) throws Exception{
		apiResponse	= new ApiResponse(1, adminServices.updateVehicleModels(vModels), "Success", HttpStatus.OK.value(), null);
		
		return apiResponse;
	}
	
	@RequestMapping(value = "/vehicle-model/list")
	private ApiResponse listVehicleModels() throws Exception{
		apiResponse	= new ApiResponse(1, adminServices.listVehicleModels(), "Success", HttpStatus.OK.value(), null);
		
		return apiResponse;
	}
}
