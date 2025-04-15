package com.smartjob.managing.profile.controller;

import com.smartjob.managing.profile.payload.request.Phone;
import com.smartjob.managing.profile.payload.request.ProfileRequest;
import com.smartjob.managing.profile.payload.response.ProfileMessage;
import com.smartjob.managing.profile.payload.response.ProfileResponse;
import com.smartjob.managing.profile.payload.response.ProfileSuccess;
import com.smartjob.managing.profile.service.ProfileService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class ProfileControllerTest {

    ProfileController profileController;

    @Mock
    ProfileService profileService;

    ProfileRequest profileRequest;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        profileController = new ProfileController(profileService);
        profileRequest = getProfileRequest();
    }

    @Test
    void createUserSuccess() {

        when(profileService.saveProfile(profileRequest, "TOKEN")).thenReturn(getProfileResponse());

        var response = profileController.createUser("TOKEN", profileRequest);
        assertNotNull(response);
        assertEquals(response.getBody().getCodeResponse(), "200 OK");
    }

    @Test
    void createUserWithEmailExist() {
        when(profileService.saveProfile(profileRequest, "TOKEN")).thenReturn(getProfileResponseConflict());

        var response = profileController.createUser("TOKEN", profileRequest);
        assertNotNull(response);
        assertEquals(response.getBody().getCodeResponse(), "409 Conflict");
    }

    private ProfileRequest getProfileRequest() {
        ProfileRequest profileRequest = new ProfileRequest();
        profileRequest.setEmail("test@test.com");
        profileRequest.setPassword("123456");
        profileRequest.setName("test");
        profileRequest.setPhones(List.of(new Phone("1234567","1","57")));
        return profileRequest;
    }

    private ProfileResponse getProfileResponse() {
        ProfileSuccess profileSuccess = new ProfileSuccess();
        profileSuccess.setCodeResponse("200 OK");
        profileSuccess.setIdUser("asdfasdfs-fefefe-seafas");
        profileSuccess.setActive(true);
        profileSuccess.setCreated(new Date());
        profileSuccess.setModified(new Date());
        profileSuccess.setLastLogin(new Date());

        return profileSuccess;
    }

    private ProfileResponse getProfileResponseConflict() {
        ProfileMessage profileMessage = new ProfileMessage();
        profileMessage.setCodeResponse("409 Conflict");
        profileMessage.setMessage("Email inserted have been associated with some user previously");

        return profileMessage;
    }
}