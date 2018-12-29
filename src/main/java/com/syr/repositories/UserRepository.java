package com.syr.repositories;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.syr.models.User;

/**
 * @author Ebin
 * @on 29-Sep-2018
 * @version 0.0
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	/**
	 * @param username
	 * @return
	 */
	@EntityGraph("userDetails")
	@Query(value = "SELECT user "
			+ "FROM User user "
			+ "JOIN FETCH user.userLogins ul "
			+ "WHERE ul.userName = ?1")
	User findOneByUsername(String username);

	/**
	 * @param userId
	 * @return
	 */
	@EntityGraph("userDetails")
	User findOneByUserId(long userId);

}
