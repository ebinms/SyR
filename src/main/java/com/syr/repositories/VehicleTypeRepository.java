package com.syr.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.syr.models.VehicleType;

/**
 * @author Ebin
 * @on 01-Oct-2018
 * @version 0.0
 */
@Repository
public interface VehicleTypeRepository extends JpaRepository<VehicleType, Long>{

	/**
	 * @param collect
	 * @return
	 */
	List<VehicleType> findAllByVehicleTypeIdIn(List<Long> typeIds);

	/**
	 * @param string
	 * @param string2
	 * @return
	 */
	List<VehicleType> findAllByStatusAndUserView(String status, String userView);

	/**
	 * @param typeId
	 * @return
	 */
	VehicleType findOneByVehicleTypeIdAndStatusIn(long typeId,String... status);

	/**
	 * @param status
	 * @param type
	 * @return
	 */
	List<VehicleType> findAllByStatusAndUserViewIn(String status, String... type);

}
