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

import com.revature.revhire.controller.ApplicationController;
import com.revature.revhire.dto.ApplicationRequest;
import com.revature.revhire.dto.ApplicationResponse;
import com.revature.revhire.service.ApplicationService;
import com.revature.revhire.service.exception.ApplicationServiceException;

@SpringBootTest
class ApplicationTest {

    private ApplicationController applicationController;
    private ApplicationService applicationService;

    @BeforeEach
    public void setUp() {
        applicationService = mock(ApplicationService.class);
        applicationController = new ApplicationController(applicationService);
    }

    @Test
    void testCreateApplication() throws ApplicationServiceException {
        ApplicationRequest applicationRequest = new ApplicationRequest();
        ApplicationResponse expectedResponse = new ApplicationResponse();
        when(applicationService.createApplication(applicationRequest)).thenReturn(expectedResponse);
        ApplicationResponse response = applicationController.createApplication(applicationRequest);

        assertEquals(expectedResponse, response);
    }

    @Test
    void testCreateApplicationError() throws ApplicationServiceException {
        ApplicationRequest applicationRequest = new ApplicationRequest();
        when(applicationService.createApplication(applicationRequest)).thenThrow(new RuntimeException("Test exception"));
        assertThrows(HttpClientErrorException.class, () -> applicationController.createApplication(applicationRequest));
    }

    @Test
    void testGetApplication() throws ApplicationServiceException {
        long applicationId = 1;
        ApplicationResponse expectedResponse = new ApplicationResponse();
        when(applicationService.getApplication(applicationId)).thenReturn(expectedResponse);
        ApplicationResponse response = applicationController.getApplication(applicationId);
        assertEquals(expectedResponse, response);
    }

    @Test
    void testGetApplicationError() throws ApplicationServiceException {
        long applicationId = 1;
        when(applicationService.getApplication(applicationId)).thenThrow(new RuntimeException("Test exception"));
        assertThrows(HttpClientErrorException.class, () -> applicationController.getApplication(applicationId));
    }

    @Test
    void testGetAllApplicationsByJobId() throws ApplicationServiceException {
        long jobId = 1;
        List<ApplicationResponse> expectedResponseList = new ArrayList<>();
        when(applicationService.getAllapplicationsByJobId(jobId)).thenReturn(expectedResponseList);
        List<ApplicationResponse> responseList = applicationController.getAllApplicationsByJobId(jobId);
        assertEquals(expectedResponseList, responseList);
    }

    @Test
    void testGetAllApplicationsByUserId() throws ApplicationServiceException {
        long userId = 1;
        List<ApplicationResponse> expectedResponseList = new ArrayList<>();
        when(applicationService.getAllapplicationsByUserId(userId)).thenReturn(expectedResponseList);
        List<ApplicationResponse> responseList = applicationController.getAllapplicationsByUserId(userId);
        assertEquals(expectedResponseList, responseList);
    }

    @Test
    void testDeleteApplicationSuccess() throws ApplicationServiceException {
        long applicationId = 1;
        when(applicationService.deleteApplication(applicationId)).thenReturn(true);
        String response = applicationController.deleteApplication(applicationId);
        assertEquals("application Deleted Successfully", response);
    }

    @Test
    void testDeleteApplicationError() throws ApplicationServiceException {
        long applicationId = 1;
        when(applicationService.deleteApplication(applicationId)).thenReturn(false);
        assertThrows(HttpClientErrorException.class, () -> applicationController.deleteApplication(applicationId));
    }
}
