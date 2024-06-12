package com.metaphorce.exam.ecommerce.controllers;

import com.metaphorce.exam.ecommerce.dtos.AuthRequest;
import com.metaphorce.exam.ecommerce.dtos.AuthResponse;
import com.metaphorce.exam.ecommerce.dtos.SignUpResponse;
import com.metaphorce.exam.ecommerce.dtos.SignUpRequest;
import com.metaphorce.exam.ecommerce.entities.User;
import com.metaphorce.exam.ecommerce.mappers.impl.SignUpMapper;
import com.metaphorce.exam.ecommerce.services.JwtService;
import com.metaphorce.exam.ecommerce.services.UserInfoService;
import com.metaphorce.exam.ecommerce.services.UserService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * REST controller for managing the authorization of users.
 */
@RestController
@RequestMapping("/api/v1/auth")
@Slf4j
@Validated
public class AuthController
{
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final SignUpMapper signUpMapper;
    private final UserService userService;

    /**
     * Constructs a AuthController with the necessary dependencies.
     *
     * @param authenticationManager
     * @param jwtService
     * @param signUpMapper
     * @param userService the service handling user operations
     */
    @Autowired
    public AuthController(AuthenticationManager authenticationManager, JwtService jwtService,
                          SignUpMapper singUpMapper, UserService userService) {
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
        this.signUpMapper = singUpMapper;
        this.userService = userService;
    }

    /**
     * Signup a new user.
     *
     * @param request the SignUpRequest object representing the new user to signup
     * @return a ResponseEntity containing the created user and HTTP status CREATED
     */
    @PostMapping("/signup")
    public ResponseEntity<SignUpResponse> signUp(@Valid @RequestBody SignUpRequest request) {
        log.info("Singing up a new user: {}", request);
        return new ResponseEntity<>(signUpMapper.entityToResponse
                                    (userService.createUser(signUpMapper.requestToEntity(request))),
                                    HttpStatus.CREATED);
    }

    /**
     * Generates a new JWT token.
     *
     * @param authRequest the AuthRequest object that contains the credential of the user
     * @return a ResponseEntity containing the generated token and HTTP status OK
     */
    @PostMapping("/generateToken")
    public ResponseEntity<AuthResponse> authenticateAndGetToken(@Valid @RequestBody AuthRequest authRequest) {
        log.info("Authenticating user: {}", authRequest.getEmail());
        Authentication authentication = authenticationManager.authenticate
            ( new UsernamePasswordAuthenticationToken(authRequest.getEmail(), authRequest.getPassword()) );
        if ( authentication.isAuthenticated() ) {
            log.info("User {} successfully authenticated", authRequest.getEmail());
            return ResponseEntity.ok(AuthResponse.builder()
                                     .type("Bearer")
                                     .token(jwtService.generateToken(authRequest.getEmail()))
                                     .build());
        } else {
            throw new UsernameNotFoundException("Invalid user request!");
        }
    }
}
