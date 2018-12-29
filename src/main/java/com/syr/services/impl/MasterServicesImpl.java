package com.syr.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.syr.defaults.SyrStatus;
import com.syr.defaults.SyrUserType;
import com.syr.models.VehicleCompany;
import com.syr.models.VehicleModel;
import com.syr.models.VehicleType;
import com.syr.repositories.VehicleCompanyRepository;
import com.syr.repositories.VehicleModelRepository;
import com.syr.repositories.VehicleTypeRepository;
import com.syr.services.MasterServices;

/**
 * @author Ebin
 * @on 03-Oct-2018
 * @version 0.0
 */
@Service
public class MasterServicesImpl implements MasterServices{
	
	@Autowired
	VehicleTypeRepository vTypeRepo;
	
	@Autowired
	VehicleCompanyRepository vCompanyRepo;
	
	@Autowired
	VehicleModelRepository vModelRepo;

	/* (non-Javadoc)
	 * @see com.syr.services.MasterServices#listVehicleTypes()
	 */
	@Override
	public Object listVehicleTypes() {
		return vTypeRepo.findAllByStatusAndUserViewIn(SyrStatus.ACTIVE.Status(),SyrUserType.NORMAL.Type());
	}

	/* (non-Javadoc)
	 * @see com.syr.services.MasterServices#getOneVehicleType(long)
	 */
	@Override
	public VehicleType getOneVehicleType(long typeId) {
		return vTypeRepo.findOneByVehicleTypeIdAndStatusIn(typeId,SyrStatus.ACTIVE.Status());
	}

	/* (non-Javadoc)
	 * @see com.syr.services.MasterServices#listVehicleModels()
	 */
	@Override
	public Object listVehicleModels() {
		return vModelRepo.findAllByStatusAndUserViewIn(SyrStatus.ACTIVE.Status(),SyrUserType.NORMAL.Type());
	}

	/* (non-Javadoc)
	 * @see com.syr.services.MasterServices#getOneVehicleModel(long)
	 */
	@Override
	public VehicleModel getOneVehicleModel(long modelId) {
		return vModelRepo.findOneByModelIdAndStatus(modelId,SyrStatus.ACTIVE.Status());
	}

	/* (non-Javadoc)
	 * @see com.syr.services.MasterServices#listVehicleCompanies()
	 */
	@Override
	public Object listVehicleCompanies() {
		return vCompanyRepo.findAllByCompanyStatusAndUserViewIn(SyrStatus.ACTIVE.Status(),SyrUserType.NORMAL.Type());
	}

	/* (non-Javadoc)
	 * @see com.syr.services.MasterServices#getOneVehcleCompany(long)
	 */
	@Override
	public VehicleCompany getOneVehcleCompany(long companyId) {
		return vCompanyRepo.findOneByCompanyIdAndCompanyStatusIn(companyId,SyrStatus.ACTIVE.Status());
	}

	/* (non-Javadoc)
	 * @see com.syr.services.MasterServices#listVehicleModels(long, long)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public Object listVehicleModels(long typeId, long companyId) {
		List<VehicleModel> vModels	= null;
		
		if(typeId == 0&&companyId == 0){
			vModels	= (List<VehicleModel>) listVehicleModels();
		}else if(typeId == 0){
			vModels	= vModelRepo.findAllByVehicleCompanyCompanyIdAndStatusAndUserView(companyId,SyrStatus.ACTIVE.Status(),SyrUserType.NORMAL.Type());
		}else{
			vModels	= vModelRepo.findAllByVehicleTypeVehicleTypeIdAndStatusAndUserView(typeId,SyrStatus.ACTIVE.Status(),SyrUserType.NORMAL.Type());
		}
		return vModels;
	}

}
