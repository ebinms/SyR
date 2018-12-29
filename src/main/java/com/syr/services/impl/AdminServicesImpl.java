package com.syr.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.syr.app_config.IdGenerator;
import com.syr.models.ApplicationClient;
import com.syr.models.VehicleCompany;
import com.syr.models.VehicleModel;
import com.syr.models.VehicleType;
import com.syr.repositories.AppClientRepository;
import com.syr.repositories.VehicleCompanyRepository;
import com.syr.repositories.VehicleModelRepository;
import com.syr.repositories.VehicleTypeRepository;
import com.syr.services.Adminservices;

/**
 * @author Ebin
 * @on 30-Sep-2018
 * @version
 */
@Service
public class AdminServicesImpl implements Adminservices {

	@Autowired
	BCryptPasswordEncoder passwordEncoder;

	@Autowired
	AppClientRepository appClientRepo;

	@Autowired
	VehicleTypeRepository vTypeRepo;

	@Autowired
	VehicleModelRepository vModelRepo;

	@Autowired
	VehicleCompanyRepository vCompanyRepo;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.syr.services.Adminservices#generateBusinessKey()
	 */
	@Override
	public Object generateBusinessKey() {
		return IdGenerator.createId();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.syr.services.Adminservices#encodePassword(java.lang.String)
	 */
	@Override
	public Object encodePassword(String password) {
		return passwordEncoder.encode(password);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.syr.services.Adminservices#addApplicationClient(com.syr.models.
	 * ApplicationClient)
	 */
	@Override
	public Object addApplicationClient(ApplicationClient client) throws Exception {
		try {
			if (client.getClientPassword() == null || client.getClientPassword().trim().equals("")
					|| client.getClientPassword().trim().equals("#")) {
				String password = IdGenerator.createId();
				client.setClientPassword(passwordEncoder.encode(password));
			}
			return appClientRepo.save(client);
		} catch (Exception e) {
			System.out.println("Exception.............." + e.getMessage());
			throw e;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.syr.services.Adminservices#listAllClients()
	 */
	@Override
	public Object listAllClients() {
		return appClientRepo.findAll();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.syr.services.Adminservices#getOneClient(long)
	 */
	@Override
	public Object getOneClient(long clientId) {
		return appClientRepo.findById(clientId);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.syr.services.Adminservices#resetClientPassword(long,
	 * java.lang.String, java.lang.String)
	 */
	@Override
	public Object resetClientPassword(long clientId, String oldPassword, String newPassword) throws Exception {
		try {
			ApplicationClient client = (ApplicationClient) getOneClient(clientId);
			if (!passwordEncoder.matches(oldPassword.trim(), client.getClientPassword())) {
				throw new Exception("old.password.match");
			}

			client.setClientPassword(passwordEncoder.encode(newPassword));
			return appClientRepo.save(client);
		} catch (Exception e) {
			throw e;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.syr.services.Adminservices#forgotClientPassword(long)
	 */
	@Override
	public Object forgotClientPassword(long clientId) {
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.syr.services.Adminservices#addVehicleTypes(java.util.List)
	 */
	@Transactional
	@Override
	public Object addVehicleTypes(@Valid List<VehicleType> vTypes) throws Exception {
		try {
			return vTypeRepo.saveAll(vTypes);
		} catch (Exception e) {
			throw e;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.syr.services.Adminservices#listVehicleTypes()
	 */
	@Override
	public Object listVehicleTypes() {
		return vTypeRepo.findAll();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.syr.services.Adminservices#addVehicleModels(java.util.List)
	 */
	@Override
	public Object addVehicleModels(@Valid List<VehicleModel> vModels) throws Exception {
		try {
			List<VehicleType> vTypes = vTypeRepo.findAllByVehicleTypeIdIn(
					vModels.stream().map(VehicleModel::getvTypeId).distinct().collect(Collectors.toList()));

			List<VehicleCompany> vCompanies = vCompanyRepo.findAllById(
					vModels.stream().map(VehicleModel::getCompanyId).distinct().collect(Collectors.toList()));

			vModels.stream().forEach(vm -> {
				vTypes.stream().forEach(vt -> {
					if (vm.getvTypeId() == vt.getVehicleTypeId())
						vm.setVehicleType(vt);
				});

				vCompanies.stream().forEach(vc -> {
					if (vm.getCompanyId() == vc.getCompanyId())
						vm.setVehicleCompany(vc);
				});
			});
			//vModels = vModels.stream().map(vModelRepo::save).collect(Collectors.toList());
			vModels	= vModelRepo.saveAll(vModels);
			return vModels;
		} catch (Exception e) {
			throw e;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.syr.services.Adminservices#updateVehicleModels(java.util.List)
	 */
	@Override
	public Object updateVehicleModels(@Valid List<VehicleModel> vModels) throws Exception {
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.syr.services.Adminservices#listVehicleModels()
	 */
	@Override
	public Object listVehicleModels() {
		return vModelRepo.findAllByModelIdNotNull();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.syr.services.Adminservices#addVehicleCompanies(java.util.List)
	 */
	@Transactional
	@Override
	public Object addVehicleCompanies(@Valid List<VehicleCompany> vCompanies) throws Exception {
		try {
			return vCompanyRepo.saveAll(vCompanies);
		} catch (Exception e) {
			throw e;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.syr.services.Adminservices#updateVehicleCompanies(java.util.List)
	 */
	@Transactional
	@Override
	public Object updateVehicleCompanies(@Valid List<VehicleCompany> vCompanies) throws Exception {
		try {
			return vCompanyRepo.saveAll(vCompanies);
		} catch (Exception e) {
			throw e;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.syr.services.Adminservices#listVehicleCompanies()
	 */
	@Override
	public Object listVehicleCompanies() {
		return vCompanyRepo.findAll();
	}
}
