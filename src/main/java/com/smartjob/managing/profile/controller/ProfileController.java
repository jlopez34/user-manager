package com.smartjob.managing.profile.controller;

import com.smartjob.managing.profile.payload.request.ProfileRequest;
import com.smartjob.managing.profile.payload.response.ProfileResponse;
import com.smartjob.managing.profile.service.ProfileService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

@Tag(name = "Profile", description = "Profile Controller")
@RestController
@RequestMapping("/api/v1/profile")
public class ProfileController {
    private static final Logger LOGGER = LoggerFactory.getLogger(ProfileController.class);

    private final ProfileService service;

    public ProfileController(ProfileService service) {
        this.service = service;
    }

    @Operation(summary = "Create Profile Method is responsible for creating (registering) a new profile.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation"),
            @ApiResponse(responseCode = "409", description = "duplicate email")
    })
    @PostMapping(value = "/create/")
    public ResponseEntity<ProfileResponse> createUser(@RequestHeader(HttpHeaders.AUTHORIZATION) String token, @Valid @RequestBody ProfileRequest request) {
        LOGGER.info("::: Consuming service saveProfile ::::{}", request.toString());
        ProfileResponse profileSaved = service.saveProfile(request, token);
        return ResponseEntity.ok(profileSaved);
    }
}
