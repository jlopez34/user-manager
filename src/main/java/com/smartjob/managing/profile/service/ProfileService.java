package com.smartjob.managing.profile.service;

import com.smartjob.managing.profile.payload.request.ProfileRequest;
import com.smartjob.managing.profile.payload.response.ProfileResponse;

public interface ProfileService {

    ProfileResponse saveProfile(ProfileRequest profileRequest, String token);
}
