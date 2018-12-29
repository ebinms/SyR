package com.syr.services;

import com.syr.models.VehicleCompany;
import com.syr.models.VehicleModel;
import com.syr.models.VehicleType;

/**
 * @author Ebin
 * @on 03-Oct-2018
 * @version 0.0
 */
public interface MasterServices {

	Object listVehicleTypes();
	VehicleType getOneVehicleType(long typeId);
	Object listVehicleModels();
	VehicleModel getOneVehicleModel(long modelId);
	Object listVehicleModels(long typeId,long companyId);
	Object listVehicleCompanies();
	VehicleCompany getOneVehcleCompany(long companyId);
}
