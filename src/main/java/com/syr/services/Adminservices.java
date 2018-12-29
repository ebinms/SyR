package com.syr.services;

import java.util.List;

import javax.validation.Valid;

import com.syr.models.ApplicationClient;
import com.syr.models.VehicleCompany;
import com.syr.models.VehicleModel;
import com.syr.models.VehicleType;

/**
 * @author Ebin
 * @on 30-Sep-2018
 * @version 0.0
 */
public interface Adminservices {

	Object generateBusinessKey();

	/**
	 * @param password
	 * @return
	 */
	Object encodePassword(String password);

	/**
	 * @param client
	 * @return
	 */
	Object addApplicationClient(ApplicationClient client) throws Exception;

	/**
	 * @return
	 */
	Object listAllClients();

	/**
	 * @param asLong
	 * @return
	 */
	Object getOneClient(long clientId);

	/**
	 * @param asLong
	 * @param asText
	 * @param asText2
	 * @return
	 * @throws Exception 
	 */
	Object resetClientPassword(long clientId, String oldPassword, String newPassword) throws Exception;

	/**
	 * @param asLong
	 * @return
	 */
	Object forgotClientPassword(long clientId);

	/**
	 * @param vTypes
	 * @return
	 */
	Object addVehicleTypes(@Valid List<VehicleType> vTypes)throws Exception;

	/**
	 * @return
	 */
	Object listVehicleTypes();

	/**
	 * @param vModels
	 * @return
	 */
	Object addVehicleModels(@Valid List<VehicleModel> vModels)throws Exception;

	/**
	 * @param vModels
	 * @return
	 */
	Object updateVehicleModels(@Valid List<VehicleModel> vModels)throws Exception;

	/**
	 * @return
	 */
	Object listVehicleModels();

	/**
	 * @param vCompanies
	 * @return
	 */
	Object addVehicleCompanies(@Valid List<VehicleCompany> vCompanies)throws Exception;

	/**
	 * @param vCompanies
	 * @return
	 */
	Object updateVehicleCompanies(@Valid List<VehicleCompany> vCompanies)throws Exception;

	/**
	 * @return
	 */
	Object listVehicleCompanies();
}
