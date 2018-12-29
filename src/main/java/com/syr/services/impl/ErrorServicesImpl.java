package com.syr.services.impl;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.syr.models.ErrorLog;
import com.syr.repositories.ErrorLogRepository;

@Service
public class ErrorServicesImpl {

	@Autowired
	ErrorLogRepository eLogRepo;
	
	public void saveErrorLog(ErrorLog eLog) {
		try{
			ExecutorService executor = Executors.newSingleThreadExecutor();
			
			executor.execute(new Runnable()
			{
				@Override
				public void run() {
					eLogRepo.save(eLog);
				}
			});
		}catch (Exception e) {
			throw e;
		}
	}
}
