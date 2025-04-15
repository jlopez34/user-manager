package com.smartjob.managing.profile.controller;

import com.smartjob.managing.profile.payload.request.UserRequest;
import com.smartjob.managing.profile.security.JwtUtil;
import com.smartjob.managing.profile.service.impl.CustomUserDetailService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Authentication", description = "Authentication Controller")
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    CustomUserDetailService customUserDetailService;

    @Autowired
    JwtUtil jwtUtil;

    @Operation(summary = "Log into a system by entering username and password generating JWT code")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation")
    })
    @PostMapping("/signin")
    public String authenticateUser(@RequestBody UserRequest userRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(userRequest.getUsername(), userRequest.getPassword())
        );
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        return jwtUtil.createToken(userDetails.getUsername());
    }


    @Operation(summary = "Register or create new account and generating JWT code")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation")
    })
    @PostMapping("/signup")
    public String registerUser(@RequestBody UserRequest userRequest) {
        customUserDetailService.register(userRequest.getUsername(), userRequest.getPassword());
        return jwtUtil.createToken(userRequest.getUsername());
    }

}
