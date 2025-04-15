package com.smartjob.managing.profile.payload.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProfileSuccess extends ProfileResponse {
    private String idUser;
    private Date created;
    private Date modified;
    private Date lastLogin;
    private String token;
    private Boolean active;
}
