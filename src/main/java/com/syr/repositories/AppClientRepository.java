package com.syr.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.syr.models.ApplicationClient;

/**
 * @author Ebin
 * @on 01-Oct-2018
 * @version 0.0
 */
public interface AppClientRepository extends JpaRepository<ApplicationClient, Long>{

}
