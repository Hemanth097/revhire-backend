package com.revature.revhire;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.HttpClientErrorException;
import java.util.ArrayList;
import java.util.List;

import com.revature.revhire.controller.JobController;
import com.revature.revhire.dto.JobRequest;
import com.revature.revhire.dto.JobResponse;
import com.revature.revhire.service.JobService;
import com.revature.revhire.service.exception.JobServiceException;

@SpringBootTest
public class JobTest {

    private JobController jobController;
    private JobService jobService;

    @BeforeEach
    public void setUp() {
        jobService = mock(JobService.class);
        jobController = new JobController(jobService);
    }

    @Test
     void testCreateJob() throws JobServiceException {
        JobRequest jobRequest = new JobRequest(Long.valueOf(102),"Infosys","https://example.com/logo.png","2023-10-05","Systems Engineer","Chennai, IND","Sample Description",List.of("Java","Python"));
        JobResponse expectedResponse = new JobResponse();
        when(jobService.createJob(jobRequest)).thenReturn(expectedResponse);
        JobResponse response = jobController.createJob(jobRequest);
        assertEquals(expectedResponse, response);
    }

    @Test
     void testCreateJobError() throws JobServiceException {
        JobRequest jobRequest = new JobRequest(Long.valueOf(102),"Infosys","https://example.com/logo.png","2023-10-05","Systems Engineer","Chennai, IND","Sample Description",List.of("Java","Python"));
        when(jobService.createJob(jobRequest)).thenThrow(new RuntimeException("Test exception"));
        assertThrows(HttpClientErrorException.class, () -> jobController.createJob(jobRequest));
    }

    @Test
     void testUpdateJob() throws JobServiceException {
        JobRequest jobRequest = new JobRequest(Long.valueOf(102),"Infosys","https://example.com/logo.png","2023-10-05","Systems Engineer","Chennai, IND","Sample Description",List.of("Java","Python"));
        long jobId = 1;
        JobResponse expectedResponse = new JobResponse();
        when(jobService.updateJob(jobRequest, jobId)).thenReturn(expectedResponse);
        JobResponse response = jobController.updateateJob(jobRequest, jobId);
        assertEquals(expectedResponse, response);
    }

    @Test
     void testUpdateJobError() throws JobServiceException {
        JobRequest jobRequest = new JobRequest(Long.valueOf(102),"Infosys","https://example.com/logo.png","2023-10-05","Systems Engineer","Chennai, IND","Sample Description",List.of("Java","Python"));
        long jobId = 1;
        when(jobService.updateJob(jobRequest, jobId)).thenThrow(new RuntimeException("Test exception"));
        assertThrows(HttpClientErrorException.class, () -> jobController.updateateJob(jobRequest, jobId));
    }

    @Test
     void testGetJob() throws JobServiceException {
        long jobId = 1;
        JobResponse expectedResponse = new JobResponse();
        when(jobService.getJob(jobId)).thenReturn(expectedResponse);
        JobResponse response = jobController.getJob(jobId);
        assertEquals(expectedResponse, response);
    }

    @Test
     void testGetJobError() throws JobServiceException {
        long jobId = 1;
        when(jobService.getJob(jobId)).thenThrow(new RuntimeException("Test exception"));
        assertThrows(HttpClientErrorException.class, () -> jobController.getJob(jobId));
    }

    @Test
     void testGetAllJobs() throws JobServiceException {
        List<JobResponse> expectedResponseList = new ArrayList<>();
        when(jobService.getAllJobs()).thenReturn(expectedResponseList);
        List<JobResponse> responseList = jobController.getAllJobs();
        assertEquals(expectedResponseList, responseList);
    }

    @Test
     void testGetAllJobsByUserId() throws JobServiceException {
        long userId = 1;
        List<JobResponse> expectedResponseList = new ArrayList<>();
        when(jobService.getAllJobsByUserId(userId)).thenReturn(expectedResponseList);
        List<JobResponse> responseList = jobController.getAllJobsByUserId(userId);
        assertEquals(expectedResponseList, responseList);
    }

    @Test
     void testDeleteJobSuccess() throws JobServiceException {
        long jobId = 1;
        when(jobService.deleteJob(jobId)).thenReturn(true);
        String response = jobController.deleteJob(jobId);
        assertEquals("job Deleted Successfully", response);
    }

    @Test
     void testDeleteJobError() throws JobServiceException {
        long jobId = 1;
        when(jobService.deleteJob(jobId)).thenReturn(false);
        assertThrows(HttpClientErrorException.class, () -> jobController.deleteJob(jobId));
    }
}

