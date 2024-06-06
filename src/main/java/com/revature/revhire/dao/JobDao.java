package com.revature.revhire.dao;

import java.util.List;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Component;

import com.revature.revhire.dao.exception.DuplicateEntryException;
import com.revature.revhire.dao.exception.JobDaoException;
import com.revature.revhire.dao.exception.RetrievalFailedException;
import com.revature.revhire.dto.JobRequest;
import com.revature.revhire.dto.JobResponse;
import com.revature.revhire.model.Job;
import com.revature.revhire.repository.JobRepository;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Component
@Log4j2
@AllArgsConstructor
public class JobDao {
	
	private final JobRepository jobRepository;

	public List<JobResponse> getAllJobs() throws RetrievalFailedException {
		try {
			List<Job> jobs = jobRepository.findAll();
			return jobs.stream().map(job -> mapJobToJobResponse(job)).toList();
		}
		catch(Exception e) {
			log.error(e.getMessage());
			throw new RetrievalFailedException("Unable to retreive job Records");
		}
	}

	public List<JobResponse> getAllJobsByUserId(long userId) throws RetrievalFailedException {
		try {
			List<Job> jobs = jobRepository.findJobsByUserId(userId);
			return jobs.stream().map(job -> mapJobToJobResponse(job)).toList();
		}
		catch(Exception e) {
			log.error(e.getMessage());
			throw new RetrievalFailedException("Unable to retreive job Records");
		}
	}

	public boolean deleteJob(long id) throws JobDaoException {
		try {
			jobRepository.deleteById(id);
			return true;
			}
			catch(Exception e) {
				log.error(e);
				throw new JobDaoException("Unable to Delete record");
				
			}
	}
	
	public JobResponse mapJobToJobResponse(Job job) {
		return JobResponse.builder()
				.id(job.getId())
				.companyName(job.getCompanyName())
				.createdOn(job.getCreatedOn())
				.creatorId(job.getCreatorId())
				.description(job.getDescription())
				.designation(job.getDesignation())
				.location(job.getLocation())
				.logoLink(job.getLogoLink())
				.skills(job.getSkills())
				.build();
	}

	public JobResponse getJob(long id) throws JobDaoException {
		try {
			Job job = jobRepository.findById(id).get();
			return JobResponse.builder()
					.id(job.getId())
					.companyName(job.getCompanyName())
					.createdOn(job.getCreatedOn())
					.creatorId(job.getCreatorId())
					.description(job.getDescription())
					.designation(job.getDesignation())
					.location(job.getLocation())
					.logoLink(job.getLogoLink())
					.skills(job.getSkills())
					.build();
			
		}
		
		catch(Exception e) {
			log.error(e);
            throw new JobDaoException("Unable to get the job record");
			
		}
	}

	public JobResponse updateJob(JobRequest jobRequest, long id) throws JobDaoException {
		
		try {
			Job job = Job.builder()
					.id(id)
					.companyName(jobRequest.getCompanyName())
					.createdOn(jobRequest.getCreatedOn())
					.creatorId(jobRequest.getCreatorId())
					.designation(jobRequest.getDesignation())
					.description(jobRequest.getDescription())
					.location(jobRequest.getLocation())
					.logoLink(jobRequest.getLogoLink())
					.skills(jobRequest.getSkills())
					.build();
			
			job = jobRepository.save(job);
			return JobResponse.builder()
					.id(job.getId())
					.companyName(job.getCompanyName())
					.createdOn(job.getCreatedOn())
					.creatorId(job.getCreatorId())
					.description(job.getDescription())
					.designation(job.getDesignation())
					.location(job.getLocation())
					.logoLink(job.getLogoLink())
					.skills(job.getSkills())
					.build();
			
		}
		
		catch(Exception e) {
			log.error(e);
            throw new JobDaoException("Unable to update job record");
			
		}

		
	}

	public JobResponse createJob(JobRequest jobRequest) throws JobDaoException, DuplicateEntryException {
		try {
			Job job = Job.builder()
					.companyName(jobRequest.getCompanyName())
					.createdOn(jobRequest.getCreatedOn())
					.creatorId(jobRequest.getCreatorId())
					.designation(jobRequest.getDesignation())
					.description(jobRequest.getDescription())
					.location(jobRequest.getLocation())
					.logoLink(jobRequest.getLogoLink())
					.skills(jobRequest.getSkills())
					.build();
			
			job = jobRepository.save(job);
			return JobResponse.builder()
					.id(job.getId())
					.companyName(job.getCompanyName())
					.createdOn(job.getCreatedOn())
					.creatorId(job.getCreatorId())
					.description(job.getDescription())
					.designation(job.getDesignation())
					.location(job.getLocation())
					.logoLink(job.getLogoLink())
					.skills(job.getSkills())
					.build();
			
		}
		catch(DataIntegrityViolationException e) {
			log.error(e);
			throw new DuplicateEntryException("Date Integrity Violation for Entity User");
			
			
		}
		catch(Exception e) {
			log.error(e);
            throw new JobDaoException("Unable to create a new job record");
			
		}
	}

}
