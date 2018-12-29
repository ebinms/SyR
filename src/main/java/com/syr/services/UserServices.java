package com.syr.services;

import java.util.List;

import javax.validation.Valid;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.syr.models.User;
import com.syr.models.UserLogin;
import com.syr.models.UserVehicle;

/**
 * @author apple
 * @on 29-Sep-2018
 * @version 
 */
public interface UserServices {

	User getUserDetailsById(long userId) throws UsernameNotFoundException;
	User getUserDetailsByUsername(String username) throws UsernameNotFoundException;
	UserDetails loadUserByUsername(String username)throws UsernameNotFoundException;
	UserLogin getUserLoginByUserName(String username);
	/**
	 * @param user
	 * @return
	 */
	Object addUser(User user) throws Exception;
	/**
	 * @param uVehicle
	 * @return
	 */
	Object addUserVehicle(@Valid UserVehicle uVehicle)throws Exception;
	/**
	 * @param asText
	 * @return
	 */
	Object listUserVehicles(long userId);
	/**
	 * @param asLong
	 * @return
	 */
	UserVehicle getUserVehicle(long vehicleId);
	/**
	 * @param usernameList
	 * @return
	 */
	List<User> findUserByUsernames(List<String> usernameList);
}
