package com.syr.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.syr.models.ErrorLog;

public interface ErrorLogRepository extends JpaRepository<ErrorLog, Long>{

}
