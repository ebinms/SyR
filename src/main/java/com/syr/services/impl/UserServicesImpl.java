package com.syr.services.impl;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.syr.defaults.SyrStatus;
import com.syr.models.User;
import com.syr.models.UserEmail;
import com.syr.models.UserLogin;
import com.syr.models.UserPayable;
import com.syr.models.UserPhone;
import com.syr.models.UserVehicle;
import com.syr.repositories.UserLoginRepository;
import com.syr.repositories.UserPayableRepository;
import com.syr.repositories.UserRepository;
import com.syr.repositories.UserVehicleRepository;
import com.syr.services.MasterServices;
import com.syr.services.UserServices;

/**
 * @author Ebin
 * @on 29-Sep-2018
 * @version 0.0
 */
@Service(value = "userServices")
public class UserServicesImpl implements UserServices {
	
	@Autowired
	BCryptPasswordEncoder passwordEncoder;
	
	@Autowired
	MasterServices mServices;
	
	@Autowired
	UserRepository userRepo;
	
	@Autowired
	UserLoginRepository userLoginRepo;
	
	@Autowired
	UserPayableRepository payableRepo;
	
	@Autowired
	UserVehicleRepository uvRepo;

	/* (non-Javadoc)
	 * @see com.syr.services.UserServices#getUserDetails(long)
	 */
	@Override
	public User getUserDetailsById(long userId) throws UsernameNotFoundException {
		return userRepo.findOneByUserId(userId);
	}

	/* (non-Javadoc)
	 * @see com.syr.services.UserServices#getUserDetailsByUsername(java.lang.String)
	 */
	@Override
	public User getUserDetailsByUsername(String username) throws UsernameNotFoundException {
		return userRepo.findOneByUsername(username);
	}

	/* (non-Javadoc)
	 * @see com.syr.services.UserServices#loadUserByUsername(java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserLogin userLogin	= userLoginRepo.getOne(username);
		if(userLogin == null){
			throw new UsernameNotFoundException("Invalid username...");
		}
		return new org.springframework.security.core.userdetails.User(username, userLogin.getPassword(), getAuthority());
	}
	
	@SuppressWarnings("rawtypes")
	private List getAuthority() {
		return Arrays.asList(new SimpleGrantedAuthority("ROLE_ADMIN"));
	}

	/* (non-Javadoc)
	 * @see com.syr.services.UserServices#getUserLoginByUserName(java.lang.String)
	 */
	@Override
	public UserLogin getUserLoginByUserName(String username) {
		return userLoginRepo.findOneByUserName(username);
	}

	/* (non-Javadoc)
	 * @see com.syr.services.UserServices#addUser(com.syr.models.User)
	 */
	@Transactional
	@Override
	public Object addUser(@Valid User user) throws Exception {
		try{
			if(user.getPassword()==null||user.getPassword().trim().equals("#")||user.getPassword().trim().equals("")){
				throw new Exception("password.notnull");
			}else{
				user.setPassword(passwordEncoder.encode(user.getPassword()));
			}
			
			UserLogin ul = null;
			Set<UserPhone> upSet	= user.getUserPhones()==null?new HashSet<>():user.getUserPhones();
			Set<UserEmail> ueSet	= user.getUserEmails()==null?new HashSet<>():user.getUserEmails();
			Set<UserLogin> ulSet	= new HashSet<>();
			
			if(upSet.isEmpty()){
				UserPhone up	= new UserPhone();
				up.setCountryCode(user.getPrimaryMobCountryCode());
				up.setDndStatus(SyrStatus.NO.Status());
				up.setPhoneNo(user.getPrimaryMobileNo());
				up.setPhoneStatus(SyrStatus.ACTIVE.Status());
				up.setUser(user);
				upSet.add(up);
				
				ul	= new UserLogin();
				ul.setUser(user);
				ul.setUserName(String.valueOf(user.getPrimaryMobileNo()));
				ul.setPassword(user.getPassword());
				ulSet.add(ul);
			}
			
			if(ueSet.isEmpty()){
				UserEmail ue	= new UserEmail();
				ue.setDndStatus(SyrStatus.NO.Status());
				ue.setEmailStatus(SyrStatus.ACTIVE.Status());
				ue.setEmailId(user.getPrimaryEmail());
				ue.setUser(user);
				ueSet.add(ue);
				
				ul	= new UserLogin();
				ul.setUser(user);
				ul.setUserName(user.getPrimaryEmail());
				ul.setPassword(user.getPassword());
				ulSet.add(ul);
			}
			
			user.setUserPhones(upSet);
			user.setUserEmails(ueSet);
			user.setUserLogins(ulSet);
			
			user	= userRepo.save(user);
			
			UserPayable up	= new UserPayable();
			up.setUser(user);
			up.setPayableAmount(0);
			payableRepo.save(up);
			
			return user;
		}catch (Exception e) {
			throw e;
		}
	}

	/* (non-Javadoc)
	 * @see com.syr.services.UserServices#addUserVehicle(com.syr.models.UserVehicle)
	 */
	@Transactional
	@Override
	public Object addUserVehicle(@Valid UserVehicle uVehicle) throws Exception {
		try{
			if(uvRepo.existsByUserUserIdAndRegistrationNo(uVehicle.getUser().getUserId(),uVehicle.getRegistrationNo())){
				throw new Exception("user.registration.no.duplicate");
			}
			
			return uvRepo.save(uVehicle);
		}catch (Exception e) {
			throw e;
		}
	}

	/* (non-Javadoc)
	 * @see com.syr.services.UserServices#listUserVehicles(long)
	 */
	@Override
	public Object listUserVehicles(long userId) {
		return uvRepo.findAllByUserUserId(userId);
	}

	/* (non-Javadoc)
	 * @see com.syr.services.UserServices#getUserVehicle(long)
	 */
	@Override
	public UserVehicle getUserVehicle(long vehicleId) {
		return uvRepo.findOnByUserVehicleId(vehicleId);
	}

	/* (non-Javadoc)
	 * @see com.syr.services.UserServices#findUserByUsernames(java.util.List)
	 */
	@Override
	public List<User> findUserByUsernames(List<String> usernameList) {
		return userLoginRepo.findUsersByUsernames(usernameList);
	}

}
