package com.syr.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.syr.models.RideJoinRequest;

/**
 * @author Ebin
 * @on 03-Oct-2018
 * @version 0.0
 */
@Repository
public interface RideJoinRequestRepository extends JpaRepository<RideJoinRequest, String>{

}
