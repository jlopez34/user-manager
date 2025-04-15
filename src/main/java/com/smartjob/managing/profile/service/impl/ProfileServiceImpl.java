package com.smartjob.managing.profile.service.impl;

import com.smartjob.managing.profile.payload.request.ProfileRequest;
import com.smartjob.managing.profile.payload.response.ProfileError;
import com.smartjob.managing.profile.payload.response.ProfileMessage;
import com.smartjob.managing.profile.payload.response.ProfileResponse;
import com.smartjob.managing.profile.repository.ProfileRepository;
import com.smartjob.managing.profile.repository.entity.Profile;
import com.smartjob.managing.profile.service.ProfileService;
import com.smartjob.managing.profile.mapper.ProfileMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class ProfileServiceImpl implements ProfileService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProfileServiceImpl.class);

    @Autowired
    private ProfileRepository repository;

    @Override
    public ProfileResponse saveProfile(ProfileRequest profileRequest, String token) {
        LOGGER.info("Save profile request:{}", profileRequest);
        ProfileResponse profileResponse = null;
        try {
            if(!repository.existsByEmail(profileRequest.getEmail())) {
                Profile profile = ProfileMapper.profileToUserEntity(profileRequest, token);
                repository.save(profile);
                profileResponse = ProfileMapper.userEntityToUserResponse(profile);
                profileResponse.setCodeResponse(HttpStatus.OK.toString());
            }else{
                LOGGER.info("Email inserted have been associated with some user previously");
                ProfileMessage profileMessage = new ProfileMessage();
                profileMessage.setMessage("Email inserted have been associated with some user previously");
                profileResponse = profileMessage;
                profileResponse.setCodeResponse(HttpStatus.CONFLICT.toString());
            }
        }catch (Exception exp){
            LOGGER.error("Save profile request failed:{}", exp);
            ProfileError profileError = new ProfileError();
            profileError.setCodeResponse(HttpStatus.INTERNAL_SERVER_ERROR.toString());
            profileError.setError(exp.getMessage());
            profileResponse = profileError;
        }
        return profileResponse;
    }



}
