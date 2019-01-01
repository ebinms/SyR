package com.syr.services.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.syr.defaults.SyrDefault;
import com.syr.defaults.SyrRideType;
import com.syr.defaults.SyrStatus;
import com.syr.models.Invitation;
import com.syr.models.Ride;
import com.syr.models.RideJoinRequest;
import com.syr.models.User;
import com.syr.models.UserVehicle;
import com.syr.repositories.InvitationRepository;
import com.syr.repositories.RideJoinRequestRepository;
import com.syr.repositories.RideRepository;
import com.syr.services.RideServices;
import com.syr.services.UserServices;
import com.syr.utils.SyrUtil;

/**
 * @author Ebin
 * @on 03-Oct-2018
 * @version 0.0
 */
@Service
public class RideServicesImpl implements RideServices {

	@Autowired
	UserServices userServices;

	@Autowired
	RideRepository rideRepo;

	@Autowired
	InvitationRepository inviteRepo;

	@Autowired
	RideJoinRequestRepository rJoinReqRepo;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.syr.services.RideServices#createRide(com.syr.models.Ride)
	 */
	@Transactional
	@Override
	public Object createRide(@Valid Ride ride) throws Exception {
		try {
			/* Checking whether vehicle exists or not */
			UserVehicle uv = userServices.getUserVehicle(ride.getVehicle().getUserVehicleId());
			if (uv == null)
				throw new Exception("userVehicle.notFound");

			ride.setVehicle(uv);

			/* if the vehicle exists checks for seat availability */
			if (ride.getSeatAvailability() == 0)
				ride.setSeatAvailability(uv.getSeatingCapacity());

			User user = uv.getUser();

			/* Check whether the user is active or not */
			if (!user.getUserStatus().trim().equals(SyrStatus.ACTIVE.Status()))
				throw new Exception("user.notactive");

			/*
			 * Checks whether the user has an already active ride . PS: For
			 * now user can have only one ride in a particular time
			 */
			if (rideRepo.existsByVehicleAndRideStatusIn(uv, SyrStatus.ACTIVE.Status())) {
				throw new Exception("user.active.ride.exist");
			}

			/*
			 * For now in paid ride, the fuel amount provided by ride owner
			 * is divided by the seats available. Ride owner is included
			 */
			if (ride.getRideType().trim().equals(SyrRideType.PAID.Status())) {
				if (ride.getTotalFuelAmount() > SyrDefault.DOUBLE) {
					ride.setPerHeadAmount(
							SyrUtil.formatDouble(ride.getTotalFuelAmount() / ride.getSeatAvailability()));
				}
			}
			/* Ride start time check */
			if (ride.getStartingTime().isBefore(SyrDefault.CURRENT_DATE_TIME())) {
				throw new Exception("ride.start.presentOrFuture");
			}
			return rideRepo.save(ride);

		}
		catch (Exception e) {
			throw e;
		}
	}

	/*
	 * (non-Javadoc)
	 * Function to get ride owners rides
	 * 
	 * @see
	 * com.syr.services.RideServices#getMyRides(org.json.simple.JSONObject)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public Object getMyRides(JSONObject json) throws Exception {
		String userId = "#";
		int pageNo = 0;
		int pageSize = 0;
		Direction direction = null;
		String sortparam = "addDate";
		List<Ride> rides = null;

		JSONObject result = new JSONObject();

		try {
			if (json.containsKey("userId")) {
				userId = json.get("userId").toString().trim();
			}
			else {
				throw new Exception("userId.param.missing");
			}

			if (json.containsKey("pageNo")) {
				pageNo = Integer.valueOf(json.get("pageNo").toString()) - 1;
				pageSize = Integer.valueOf(json.get("pageSize").toString());
				direction = json.get("sortDirection").toString().trim().equals("A") ? Direction.ASC
						: Direction.DESC;
				sortparam = json.get("sortDirection").toString().trim();

				Page<Ride> ridePage = rideRepo.findAllByVehicleUserUserId(userId,
						PageRequest.of(pageNo, pageSize, direction, sortparam));

				result.put("pageNumber", ridePage.getNumber());
				result.put("totalPageCount", ridePage.getTotalPages());
				result.put("currentPageSize", ridePage.getNumberOfElements());
				result.put("totalResultCount", ridePage.getTotalElements());
				result.put("pageContent", ridePage.getContent());
			}
			else {
				rides = rideRepo.findAllByVehicleUserUserId(userId);

				result.put("pageNumber", 0);
				result.put("totalPageCount", rides.size());
				result.put("currentPageSize", rides.size());
				result.put("totalResultCount", rides.size());
				result.put("pageContent", rides);
			}
		}
		catch (Exception e) {
			throw e;
		}
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.syr.services.RideServices#searchRides(org.json.simple.JSONObject)
	 */
	@Override
	public Object searchRides(JSONObject json) {
		return null;
	}

	/*
	 * (non-Javadoc)
	 * Function to invite co-riders|| PS: Only registered users will be invited
	 * others will get ignored for now. In future they will get an SMS to
	 * install the application
	 * 
	 * @see com.syr.services.RideServices#inviteCoRiders(java.util.List)
	 */
	@Transactional
	@Override
	public Object inviteCoRiders(@Valid List<Invitation> invitations) throws Exception {
		try {
			Ride ride = rideRepo.findOneByRideNo(invitations.get(0).getRide().getRideNo());

			/* Check whether the ride is active or closed */
			if (!ride.getRideStatus().trim().equals(SyrStatus.OPEN.Status()))
				throw new Exception("ride.closed");

			/* Check whether the seats available */
			if (ride.getSeatAvailability() <= 0) {
				throw new Exception("invitation.seat.full");
			}

			List<String> usernameList = invitations.stream().distinct().map(Invitation::getGuestUsername)
					.collect(Collectors.toList());

			List<Object[]> existingUsernamesArr = inviteRepo
					.findAllUsernamesByRideNo(invitations.get(0).getRide().getRideNo());
			Set<String> existingUsernames = new HashSet<>();

			for (Object[] strArr : existingUsernamesArr) {
				existingUsernames.addAll(Arrays.asList(Arrays.stream(strArr).filter(s -> (s != null))
						.map(String::valueOf).toArray(String[]::new)));
			}

			usernameList = usernameList.stream()
					.filter(un -> (existingUsernames.stream().filter(eun -> un.equals(eun)).count()) < 1)
					.collect(Collectors.toList());

			if (usernameList != null && !usernameList.isEmpty()) {
				List<User> userList = userServices.findUserByUsernames(usernameList);

				ride.setInviteCount(ride.getInviteCount() + userList.size());
				rideRepo.save(ride);

				invitations = invitations.stream()
						.peek(invite -> 
							invite.setGuestUser(userList.stream()
								.filter(user -> (user.getPrimaryEmail().equals(invite.getGuestUsername().trim()) || String
									.valueOf(user.getPrimaryMobileNo()).equals(invite.getGuestUsername().trim())))
						.findAny()
						.orElse(null)))
						.peek(invite -> invite.setRide(ride))
						.peek(invite -> invite.setRideStatus(ride.getRideStatus()))
						.map(inviteRepo::save)
						.collect(Collectors.toList());
			}

			return invitations;

		}
		catch (Exception e) {
			throw e;
		}
	}

	/*
	 * (non-Javadoc)
	 * Function respond to/ request to join a ride.
	 * @see
	 * com.syr.services.RideServices#joinRide(com.syr.models.RideJoinRequest)
	 */
	@Transactional
	@Override
	public Object joinRide(@Valid RideJoinRequest joinRequest) throws Exception {
		try {
			Ride ride = rideRepo.findOneByRideNo(joinRequest.getRide().getRideNo());

			if (!ride.getRideStatus().trim().equals(SyrStatus.OPEN.Status()))
				throw new Exception("ride.closed");

			if (ride.getSeatAvailability() <= 0) {
				throw new Exception("joinReq.seat.full");
			}
			else {
				if (ride.getSeatAvailability() < joinRequest.getNumberOfJoinees()) {
					throw new Exception("joinReq.no.space");
				}
			}

			if (ride.getVehicle().getUser().getUserId() == joinRequest.getJoineeUser().getUserId()) {
				throw new Exception("joinee.user.same");
			}

			ride.setJoinInRequestCount(ride.getJoinInRequestCount() + 1);
			rideRepo.save(ride);

			joinRequest.setRide(ride);
			return rJoinReqRepo.save(joinRequest);
		}
		catch (Exception e) {
			throw e;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.syr.services.RideServices#getOneRideById(long)
	 */
	@Override
	public Ride getOneRideById(String rideNo) {
		return rideRepo.findOneByRideNo(rideNo);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.syr.services.RideServices#invitationList(java.lang.String,
	 * java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public Object invitationList(long userId, String invitationType) {
		JSONObject jObj = new JSONObject();
		List<Invitation> sendList = null;
		List<Invitation> receivedList = null;
		switch (invitationType.trim()) {
		case "B": // SyrRequestType.BOTH.Get()
			sendList = inviteRepo.findAllByRideVehicleUserUserId(userId);
			receivedList = inviteRepo.findAllByGuestUserUserId(userId);

			break;
		case "S":
			sendList = inviteRepo.findAllByRideVehicleUserUserId(userId);
			break;
		case "R":
			receivedList = inviteRepo.findAllByGuestUserUserId(userId);
			break;
		default:
			jObj.put("send", sendList);
			jObj.put("received", receivedList);
			break;
		}
		return jObj;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.syr.services.RideServices#getHostInvitations(org.json.simple.
	 * JSONObject)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public Object listInvitations(JSONObject json) throws Exception {
		try {
			long userId = 0;
			String invitationType = "B";
			int pageNo = 0;
			int pageSize = 0;
			Direction direction = null;
			String sortparam = "addDate";
			List<Invitation> hostInvites = new ArrayList<>();
			List<Invitation> guestInvites = new ArrayList<>();
			Page<Invitation> hInvitesPage = null;
			Page<Invitation> gInvitesPage = null;

			JSONObject hInvJson = new JSONObject();
			JSONObject gInvJson = new JSONObject();
			JSONObject resultJson = new JSONObject();

			if (!json.containsKey("userId"))
				throw new Exception("userId.missing.param");
			else
				userId = Long.valueOf(json.get("userId").toString().trim());

			if (json.containsKey("pageNo")) {
				pageNo = Integer.valueOf(json.get("pageNo").toString());
				pageSize = Integer.valueOf(json.get("pageSize").toString());
				direction = json.containsKey("sortDirection")
						? (json.get("sortDirection").toString().trim().equals("A") ? Direction.ASC
								: Direction.DESC)
						: Direction.ASC;
			}

			if (json.containsKey("invitationType"))
				invitationType = json.get("invitationType").toString().trim();

			switch (invitationType.trim()) {
			case "B":
				if (pageNo > 0) {
					hInvitesPage = inviteRepo.findAllByRideVehicleUserUserId(userId,
							PageRequest.of(pageNo - 1, pageSize, direction, sortparam));
					gInvitesPage = inviteRepo.findAllByGuestUserUserId(userId,
							PageRequest.of(pageNo - 1, pageSize, direction, sortparam));
				}
				else {
					hostInvites = inviteRepo.findAllByRideVehicleUserUserId(userId);
					guestInvites = inviteRepo.findAllByGuestUserUserId(userId);
				}

				hInvJson.put("pageNo", pageNo);
				hInvJson.put("pageSize", pageSize);
				hInvJson.put("totalPageCount", pageNo > 0 ? hInvitesPage.getTotalPages() : 1);
				hInvJson.put("totalResultCount", pageNo > 0 ? hInvitesPage.getTotalElements() : hostInvites.size());
				hInvJson.put("pageContent", pageNo > 0 ? hInvitesPage.getContent() : hostInvites);

				gInvJson.put("pageNo", pageNo);
				gInvJson.put("pageSize", pageSize);
				gInvJson.put("totalPageCount", pageNo > 0 ? gInvitesPage.getTotalPages() : 1);
				gInvJson.put("totalResultCount",
						pageNo > 0 ? gInvitesPage.getTotalElements() : guestInvites.size());
				gInvJson.put("pageContent", pageNo > 0 ? gInvitesPage.getContent() : guestInvites);
				break;
			case "H":
				if (pageNo > 0) {
					hInvitesPage = inviteRepo.findAllByRideVehicleUserUserId(userId,
							PageRequest.of(pageNo - 1, pageSize, direction, sortparam));
				}
				else {
					hostInvites = inviteRepo.findAllByRideVehicleUserUserId(userId);
				}

				hInvJson.put("pageNo", pageNo);
				hInvJson.put("pageSize", pageSize);
				hInvJson.put("totalPageCount", pageNo > 0 ? hInvitesPage.getTotalPages() : 1);
				hInvJson.put("totalResultCount", pageNo > 0 ? hInvitesPage.getTotalElements() : hostInvites.size());
				hInvJson.put("pageContent", pageNo > 0 ? hInvitesPage.getContent() : hostInvites);

				break;
			case "G":
				if (pageNo > 0) {
					gInvitesPage = inviteRepo.findAllByGuestUserUserId(userId,
							PageRequest.of(pageNo - 1, pageSize, direction, sortparam));
				}
				else {
					guestInvites = inviteRepo.findAllByGuestUserUserId(userId);
				}

				gInvJson.put("pageNo", pageNo);
				gInvJson.put("pageSize", pageSize);
				gInvJson.put("totalPageCount", pageNo > 0 ? gInvitesPage.getTotalPages() : 1);
				gInvJson.put("totalResultCount",
						pageNo > 0 ? gInvitesPage.getTotalElements() : guestInvites.size());
				gInvJson.put("pageContent", pageNo > 0 ? gInvitesPage.getContent() : guestInvites);
				break;
			default:
				break;
			}
			resultJson.put("host", hInvJson);
			resultJson.put("guest", gInvJson);
			return resultJson;
		}
		catch (Exception e) {
			throw e;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.syr.services.RideServices#listRideInvitations(java.lang.String)
	 */
	@Override
	public Object listRideInvitations(String rideNo) {
		return inviteRepo.findAllByRideRideNo(rideNo);
	}

}
