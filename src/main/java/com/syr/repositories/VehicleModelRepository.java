package com.syr.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.syr.models.VehicleModel;

/**
 * @author Ebin
 * @on 02-Oct-2018
 * @version 0.0
 */
@Repository
public interface VehicleModelRepository extends JpaRepository<VehicleModel, Long>{

	/**
	 * @return
	 */
	@EntityGraph("vehicleModel")
	List<VehicleModel> findAllByModelIdNotNull();

	/**
	 * @param string
	 * @param string2
	 * @return
	 */
	@EntityGraph("vehicleModel")
	List<VehicleModel> findAllByStatusAndUserViewIn(String status, String... userView);

	/**
	 * @param companyId
	 * @return
	 */
	@EntityGraph("vehicleModel")
	List<VehicleModel> findAllByVehicleCompanyCompanyId(long companyId);

	/**
	 * @param companyId
	 * @param status
	 * @param type
	 * @return
	 */
	@EntityGraph("vehicleModel")
	List<VehicleModel> findAllByVehicleCompanyCompanyIdAndStatusAndUserView(long companyId, String status, String type);

	/**
	 * @param typeId
	 * @param status
	 * @param type
	 * @return
	 */
	@EntityGraph("vehicleModel")
	List<VehicleModel> findAllByVehicleTypeVehicleTypeIdAndStatusAndUserView(long typeId, String status, String type);

	/**
	 * @param modelId
	 * @param status
	 * @return
	 */
	@EntityGraph("vehicleModel")
	VehicleModel findOneByModelIdAndStatus(long modelId, String status);

}
