package com.syr.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.syr.models.Invitation;

/**
 * @author Ebin
 * @on 03-Oct-2018
 * @version 0.0
 */
@Repository
public interface InvitationRepository extends JpaRepository<Invitation, String>{

	/**
	 * @param userId
	 * @return
	 */
	@EntityGraph("invitationDetails")
	List<Invitation> findAllByRideVehicleUserUserId(long userId);

	/**
	 * @param userId
	 * @return
	 */
	@EntityGraph("invitationDetails")
	List<Invitation> findAllByGuestUserUserId(long userId);

	/**
	 * @param rideNo
	 * @return
	 */
	@Query(value = "SELECT inv.guestUsername,user.primaryEmail,user.primaryMobileNo "
			+ "FROM Invitation inv "
			+ "JOIN inv.ride ride "
			+ "LEFT OUTER JOIN inv.guestUser user "
			+ "WHERE inv.ride.rideNo = ?1")
	List<Object[]> findAllUsernamesByRideNo(String rideNo);

	/**
	 * @param userId
	 * @param of
	 * @return
	 */
	@EntityGraph("invitationDetails")
	Page<Invitation> findAllByRideVehicleUserUserId(long userId, Pageable page);

	/**
	 * @param userId
	 * @param of
	 * @return
	 */
	@EntityGraph("invitationDetails")
	Page<Invitation> findAllByGuestUserUserId(long userId, Pageable page);

	/**
	 * @param rideNo
	 * @return
	 */
	@EntityGraph("invitationDetails")
	List<Invitation> findAllByRideRideNo(String rideNo);

}
