package com.revature.revhire.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.revature.revhire.dao.ApplicationDao;
import com.revature.revhire.dto.ApplicationRequest;
import com.revature.revhire.dto.ApplicationResponse;
import com.revature.revhire.service.exception.ApplicationServiceException;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
@AllArgsConstructor
public class ApplicationService {
	
	private final ApplicationDao applicationDao;

	public ApplicationResponse createApplication(ApplicationRequest applicationRequest) throws ApplicationServiceException {
        try {
			
			return applicationDao.createApplication(applicationRequest);
			
		}
		catch(Exception e) {
			log.error(e);
			throw new ApplicationServiceException("Unable to Create a new application");
			
		}

	}

	public ApplicationResponse getApplication(long id) throws ApplicationServiceException {
        try {
			
			return applicationDao.getApplication( id);
			
		}
		catch(Exception e) {
			log.error(e);
			throw new ApplicationServiceException("Unable to get application details");
			
		}
	
		
	}



	public List<ApplicationResponse> getAllapplicationsByUserId(long userId) throws ApplicationServiceException {
       try {
			
			return applicationDao.getAllapplicationsByUserId( userId);
			
		}
		catch(Exception e) {
			log.error(e);
			throw new ApplicationServiceException("Unable to get applications by User Id");
			
		}
	}

	public boolean deleteApplication(long id) throws ApplicationServiceException {
        try {
			
			return applicationDao.deleteApplication(id);
			
		}
		catch(Exception e) {
			log.error(e);
			throw new ApplicationServiceException("Unable to Delete application");
			
		}

	}

	public List<ApplicationResponse> getAllapplicationsByJobId(long jobId) throws ApplicationServiceException {
        try {
			
			return applicationDao.getAllapplicationsByJobId( jobId);
			
		}
		catch(Exception e) {
			log.error(e);
			throw new ApplicationServiceException("Unable to get applications by Job Id");
			
		}
	}

}
