package com.syr.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.syr.models.VehicleCompany;

/**
 * @author Ebin
 * @on 02-Oct-2018
 * @version 0.0
 */
@Repository
public interface VehicleCompanyRepository extends JpaRepository<VehicleCompany, Long>{

	/**
	 * @param status
	 * @param type
	 * @return
	 */
	List<VehicleCompany> findAllByCompanyStatusAndUserViewIn(String status, String... type);

	/**
	 * @param companyId
	 * @param status
	 * @return
	 */
	VehicleCompany findOneByCompanyIdAndCompanyStatusIn(long companyId, String... status);

}
