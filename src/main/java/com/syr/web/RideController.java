package com.syr.web;

import java.util.List;

import javax.validation.Valid;

import org.json.simple.JSONObject;
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
import com.syr.models.Invitation;
import com.syr.models.Ride;
import com.syr.models.RideJoinRequest;
import com.syr.services.RideServices;

/**
 * @author Ebin
 * @on 03-Oct-2018
 * @version 0.0
 */
@RestController
@RequestMapping("/${url.security}/ride")
public class RideController {

	@Autowired
	RideServices rideServices;
	
	private ApiResponse apiResponse;
	
	@RequestMapping(value = "/create", method = RequestMethod.POST, produces = { "application/JSON" })
	public ResponseEntity<ApiResponse> createRide(@RequestHeader(value = "app_lang") String lang,@Valid @RequestBody Ride ride)
			throws Exception {
		apiResponse = new ApiResponse(1, rideServices.createRide(ride), "Success", HttpStatus.OK.value(), lang);
		return new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/get", method = RequestMethod.POST, produces = { "application/JSON" })
	public ResponseEntity<ApiResponse> getMyRides(@RequestHeader(value = "app_lang") String lang,@RequestBody ObjectNode on)
			throws Exception {
		apiResponse = new ApiResponse(1, rideServices.getOneRideById(on.get("rideNo").asText()), "Success", HttpStatus.OK.value(), lang);
		return new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/my-rides", method = RequestMethod.POST, produces = { "application/JSON" })
	public ResponseEntity<ApiResponse> getMyRides(@RequestHeader(value = "app_lang") String lang,@RequestBody JSONObject json)
			throws Exception {
		apiResponse = new ApiResponse(1, rideServices.getMyRides(json), "Success", HttpStatus.OK.value(), lang);
		return new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/search", method = RequestMethod.POST, produces = { "application/JSON" })
	public ResponseEntity<ApiResponse> searchRides(@RequestHeader(value = "app_lang") String lang,@RequestBody JSONObject json)
			throws Exception {
		apiResponse = new ApiResponse(1, rideServices.searchRides(json), "Success", HttpStatus.OK.value(), lang);
		return new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/invite", method = RequestMethod.POST, produces = { "application/JSON" })
	public ResponseEntity<ApiResponse> inviteCoRiders(@RequestHeader(value = "app_lang") String lang,@RequestBody @Valid List<Invitation> invitations)
			throws Exception {
		apiResponse = new ApiResponse(1, rideServices.inviteCoRiders(invitations), "Success", HttpStatus.OK.value(), lang);
		return new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/invitation/list", method = RequestMethod.POST, produces = { "application/JSON" })
	public ResponseEntity<ApiResponse> listInvitations(@RequestHeader(value = "app_lang") String lang,@RequestBody JSONObject json)
			throws Exception {
		apiResponse = new ApiResponse(1, rideServices.listInvitations(json), "Success", HttpStatus.OK.value(), lang);
		return new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/invitations", method = RequestMethod.POST, produces = { "application/JSON" })
	public ResponseEntity<ApiResponse> listInvitationsOfARide(@RequestHeader(value = "app_lang") String lang,@RequestBody ObjectNode on)
			throws Exception {
		apiResponse = new ApiResponse(1, rideServices.listRideInvitations(on.get("rideNo").asText()), "Success", HttpStatus.OK.value(), lang);
		return new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/join", method = RequestMethod.POST, produces = { "application/JSON" })
	public ResponseEntity<ApiResponse> joinRide(@RequestHeader(value = "app_lang") String lang,@RequestBody @Valid RideJoinRequest joinRequest)
			throws Exception {
		apiResponse = new ApiResponse(1, rideServices.joinRide(joinRequest), "Success", HttpStatus.OK.value(), lang);
		return new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.OK);
	}
}
