package com.syr.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.syr.models.User;
import com.syr.models.UserLogin;

/**
 * @author apple
 * @on 29-Sep-2018
 * @version 
 */
@Repository
public interface UserLoginRepository extends JpaRepository<UserLogin, String>{

	/**
	 * @param username
	 * @return
	 */
	UserLogin findOneByUserName(String username);

	/**
	 * @param usernameList
	 * @return
	 */
	@Query(value = "SELECT DISTINCT user "
			+ "FROM UserLogin ul "
			+ "JOIN ul.user user "
			+ "WHERE ul.userName IN (?1)")
	List<User> findUsersByUsernames(List<String> usernameList);


}
