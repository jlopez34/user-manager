package com.smartjob.managing.profile.mapper;

import com.smartjob.managing.profile.payload.request.ProfileRequest;
import com.smartjob.managing.profile.payload.response.ProfileSuccess;
import com.smartjob.managing.profile.repository.entity.Phone;
import com.smartjob.managing.profile.repository.entity.Profile;
import jakarta.validation.constraints.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public abstract class ProfileMapper {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProfileMapper.class);

    public static Profile profileToUserEntity(ProfileRequest profile, String token){
        LOGGER.info("profileToUserEntity: profile={}", profile);
        Profile profileEntity = null;
        if(profile != null){
            profileEntity = new Profile();
            profileEntity.setName(profile.getName());
            profileEntity.setEmail(profile.getEmail());
            profileEntity.setPassword(profile.getPassword());
            profileEntity.setLastLogin(new Date());
            profileEntity.setActive(true);
            profileEntity.setToken(token.substring(7));
            profileEntity.setUserPhonesByIdUser(phoneFrom(profile.getPhones(), profileEntity));
        }

        return profileEntity;
    }

    private static Set<Phone> phoneFrom(@NotNull List<com.smartjob.managing.profile.payload.request.Phone> phones, Profile profile) {
        LOGGER.info("phoneFrom: phones={}", phones);
        return phones.stream()
                .map(phone -> {
                    Phone phoneEntity = new Phone();
                    phoneEntity.setCityCode(phone.getCityCode());
                    phoneEntity.setCountryCode(phone.getCountryCode());
                    phoneEntity.setNumber(phone.getNumber());
                    phoneEntity.setProfile(profile);
                    return phoneEntity;
                }).collect(Collectors.toSet());
    }

    public static ProfileSuccess userEntityToUserResponse(Profile profile) {
        LOGGER.info("userEntityToUserResponse: userEntity={}", profile);

        ProfileSuccess success = new ProfileSuccess();
        success.setIdUser(profile.getIdProfile().toString());
        success.setCreated(profile.getCreated());
        success.setModified(profile.getModified());
        success.setLastLogin(profile.getLastLogin());
        success.setToken(profile.getToken());
        success.setActive(profile.getActive());

        return success;
    }
}
