package com.syr.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.syr.models.UserVehicle;

/**
 * @author Ebin
 * @on 03-Oct-2018
 * @version 0.0
 */
@Repository
public interface UserVehicleRepository extends JpaRepository<UserVehicle, Long>{

	/**
	 * @param userId
	 * @return
	 */
	@EntityGraph("userVehicle")
	List<UserVehicle> findAllByUserUserId(long userId);

	/**
	 * @param vehicleId
	 * @return
	 */
	@EntityGraph("userVehicle")
	UserVehicle findOnByUserVehicleId(long vehicleId);

	/**
	 * @param userId
	 * @param registrationNo
	 * @return
	 */
	boolean existsByUserUserIdAndRegistrationNo(long userId, String registrationNo);

}
