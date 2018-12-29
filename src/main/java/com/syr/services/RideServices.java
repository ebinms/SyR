package com.syr.services;

import java.util.List;

import javax.validation.Valid;

import org.json.simple.JSONObject;

import com.syr.models.Invitation;
import com.syr.models.Ride;
import com.syr.models.RideJoinRequest;

/**
 * @author Ebin
 * @on 03-Oct-2018
 * @version 0.0
 */
public interface RideServices {

	/**
	 * @param ride
	 * @return
	 * @throws Exception 
	 */
	Object createRide(@Valid Ride ride) throws Exception;

	/**
	 * @param json
	 * @return
	 * @throws Exception 
	 */
	Object getMyRides(JSONObject json) throws Exception;

	/**
	 * @param json
	 * @return
	 */
	Object searchRides(JSONObject json);

	/**
	 * @param invitations
	 * @return
	 */
	Object inviteCoRiders(@Valid List<Invitation> invitations)throws Exception;

	/**
	 * @param joinRequest
	 * @return
	 */
	Object joinRide(@Valid RideJoinRequest joinRequest)throws Exception;

	/**
	 * @param asLong
	 * @return
	 */
	Object getOneRideById(String rideNo);

	/**
	 * @param asText
	 * @param asText2
	 * @return
	 */
	Object invitationList(long userId, String invitationType);

	/**
	 * @param json
	 * @return
	 * @throws Exception 
	 */
	Object listInvitations(JSONObject json) throws Exception;

	/**
	 * @param asText
	 * @return
	 */
	Object listRideInvitations(String rideNo);

}
