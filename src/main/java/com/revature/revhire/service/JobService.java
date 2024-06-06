package com.revature.revhire.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.revature.revhire.dao.JobDao;
import com.revature.revhire.dto.JobRequest;
import com.revature.revhire.dto.JobResponse;
import com.revature.revhire.service.exception.JobServiceException;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@AllArgsConstructor
@Log4j2
public class JobService {
	
	private final JobDao jobDao;

	public JobResponse createJob(JobRequest jobRequest) throws JobServiceException {
		
		try {
			
			return jobDao.createJob(jobRequest);
			
		}
		catch(Exception e) {
			log.error(e);
			throw new JobServiceException("Unable to Create a new Job");
			
		}
		
	}

	public JobResponse updateJob(JobRequest jobRequest, long id) throws JobServiceException {
	try {
			
			return jobDao.updateJob(jobRequest, id);
			
		}
		catch(Exception e) {
			log.error(e);
			throw new JobServiceException("Unable to update Job");
			
		}
	}

	public JobResponse getJob(long id) throws JobServiceException {
	try {
			
			return jobDao.getJob(id);
			
		}
		catch(Exception e) {
			log.error(e);
			throw new JobServiceException("Unable to get Job Information");
			
		}
	}

	public boolean deleteJob(long id) throws JobServiceException {
	try {
			
			return jobDao.deleteJob(id);
			
		}
		catch(Exception e) {
			log.error(e);
			throw new JobServiceException("Unable to Delete the Job");
			
		}
	}

	public List<JobResponse> getAllJobsByUserId(long userId) throws JobServiceException {
	try {
			
			return jobDao.getAllJobsByUserId(userId);
			
		}
		catch(Exception e) {
			log.error(e);
			throw new JobServiceException("Unable to retreive all the Jobs by user Id");
			
		}
	}

	public List<JobResponse> getAllJobs() throws JobServiceException {
	try {
			
			return jobDao.getAllJobs();
			
		}
		catch(Exception e) {
			log.error(e);
			throw new JobServiceException("Unable to get all the Jobs");
			
		}
	}

}