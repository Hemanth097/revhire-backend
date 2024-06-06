package com.revature.revhire.dao;

import java.util.List;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Component;

import com.revature.revhire.dao.exception.ApplicationDaoException;
import com.revature.revhire.dao.exception.DuplicateEntryException;
import com.revature.revhire.dao.exception.RetrievalFailedException;
import com.revature.revhire.dto.ApplicationRequest;
import com.revature.revhire.dto.ApplicationResponse;
import com.revature.revhire.model.Application;
import com.revature.revhire.repository.ApplicationRepository;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Component
@AllArgsConstructor
@Log4j2
public class ApplicationDao {
	private final ApplicationRepository applicationRepository; 

	public ApplicationResponse createApplication(ApplicationRequest applicationRequest) throws DuplicateEntryException, ApplicationDaoException {
		try {
			Application application = Application.builder()
					.appliedOn(applicationRequest.getAppliedOn())
					.jobId(applicationRequest.getJobId())
					.resumeLink(applicationRequest.getResumeLink())
					.userId(applicationRequest.getUserId())
					.build();
			
			application = applicationRepository.save(application);
			return ApplicationResponse.builder()
					.id(application.getId())
					.appliedOn(application.getAppliedOn())
					.jobId(application.getJobId())
					.resumeLink(application.getResumeLink())
					.userId(application.getId())
					.build();
			
		}
		catch(DataIntegrityViolationException e) {
			log.error(e);
			throw new DuplicateEntryException("Date Integrity Violation for Entity Application");
			
			
		}
		catch(Exception e) {
			log.error(e);
            throw new ApplicationDaoException("Unable to create a new Application record");
			
		}

	}

	public ApplicationResponse getApplication(long id) throws RetrievalFailedException {
		try {
			
			
			Application application  = applicationRepository.findById(id).get();
			return ApplicationResponse.builder()
					.id(application.getId())
					.appliedOn(application.getAppliedOn())
					.jobId(application.getJobId())
					.resumeLink(application.getResumeLink())
					.userId(application.getId())
					.build();
			
		}
		catch(Exception e) {
			log.error(e);
            throw new RetrievalFailedException("Unable to get Application record");
			
		}

	}


	public boolean deleteApplication(long id) throws ApplicationDaoException {
		try {
			applicationRepository.deleteById(id);
			return true;
			}
			catch(Exception e) {
				log.error(e);
				throw new ApplicationDaoException("Unable to Delete record");
				
			}
	}
	public ApplicationResponse mapApplicationToApplicationResponse(Application application) {
		return ApplicationResponse.builder()
				.id(application.getId())
				.appliedOn(application.getAppliedOn())
				.jobId(application.getJobId())
				.resumeLink(application.getResumeLink())
				.userId(application.getId())
				.build();
	}

	public List<ApplicationResponse> getAllapplicationsByUserId(long userId) throws RetrievalFailedException {
		try {
		List<Application> applications = applicationRepository.findApplicationsByUserId(userId);
		
		return applications.stream().map(application -> mapApplicationToApplicationResponse(application)).toList();
		}
		catch(Exception e) {
			log.error(e);
            throw new RetrievalFailedException("Unable to get Application record");
			
		}
	}

	public List<ApplicationResponse> getAllapplicationsByJobId(long jobId) throws RetrievalFailedException {
		try {
			List<Application> applications = applicationRepository.findApplicationsByJobId(jobId);
			
			return applications.stream().map(application -> mapApplicationToApplicationResponse(application)).toList();
			}
			catch(Exception e) {
				log.error(e);
	            throw new RetrievalFailedException("Unable to get Application record");
				
			}
	}

}
