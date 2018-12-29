package com.syr.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.syr.models.Ride;
import com.syr.models.UserVehicle;

/**
 * @author Ebin
 * @on 03-Oct-2018
 * @version 0.0
 */
@Repository
public interface RideRepository extends JpaRepository<Ride, String>{

	/**
	 * @param uv
	 * @param status
	 * @return
	 */
	boolean existsByVehicleAndRideStatusIn(UserVehicle uv, String... status);

	/**
	 * @param userId
	 * @param of
	 * @return
	 */
	@EntityGraph("ride_details_full")
	Page<Ride> findAllByVehicleUserUserId(String userId, Pageable of);

	/**
	 * @param userId
	 * @return
	 */
	@EntityGraph("ride_details_full")
	List<Ride> findAllByVehicleUserUserId(String userId);

	/**
	 * @param rideNo
	 * @return
	 */
	@EntityGraph("ride_details_full")
	Ride findOneByRideNo(String rideNo);

	/**
	 * @param userVehicleId
	 * @param status
	 * @return
	 */
	boolean existsByVehicleUserVehicleIdAndRideStatusIn(long userVehicleId, String... status);

}
