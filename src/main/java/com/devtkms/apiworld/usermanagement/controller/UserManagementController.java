package com.devtkms.apiworld.usermanagement.controller;

import com.devtkms.apiworld.common.dto.ApiResponseDto;
import com.devtkms.apiworld.usermanagement.dto.*;
import com.devtkms.apiworld.usermanagement.service.DeleteUserService;
import com.devtkms.apiworld.usermanagement.service.GetUserService;
import com.devtkms.apiworld.usermanagement.service.RegisterUserService;
import com.devtkms.apiworld.usermanagement.service.UpdateUserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.context.SecurityContextHolderStrategy;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.security.web.context.SecurityContextRepository;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class UserManagementController {

    @Autowired
    RegisterUserService registerUserService;

    @Autowired
    GetUserService getUserService;

    @Autowired
    UpdateUserService updateUserService;

    @Autowired
    DeleteUserService deleteUserService;

    @Autowired
    private AuthenticationManager authenticationManager;

    private final SecurityContextRepository securityContextRepository =
            new HttpSessionSecurityContextRepository();
    private final SecurityContextHolderStrategy securityContextHolderStrategy =
            SecurityContextHolder.getContextHolderStrategy();

    /**
     * Registers a new user.
     *
     * @param registerUserRequestDto the request DTO containing user registration details
     * @return a ResponseEntity containing the API response DTO with the registered user's information
     */
    @PostMapping("/register")
    public ResponseEntity<ApiResponseDto<RegisterUserResponseDto>> registerUser(
            @Valid @RequestBody RegisterUserRequestDto registerUserRequestDto) {

        RegisterUserResponseDto result = registerUserService.registerUser(registerUserRequestDto);
        ApiResponseDto<RegisterUserResponseDto> response = ApiResponseDto.success(result);
        return ResponseEntity.ok(response);
    }

    /**
     * Retrieves user information by user ID.
     *
     * @param userId the ID of the user to retrieve
     * @return a ResponseEntity containing the API response DTO with the user's information
     */
    @GetMapping("/{userId}")
    public ResponseEntity<ApiResponseDto<GetUserResponseDto>> getUserById(@PathVariable Long userId) {

        GetUserResponseDto result = getUserService.getUser(userId);
        ApiResponseDto<GetUserResponseDto> response = ApiResponseDto.success(result);
        return ResponseEntity.ok(response);
    }

    /**
     * Updates user information.
     *
     * @param updateUserRequestDto the request DTO containing the updated user information
     * @return a ResponseEntity containing the API response DTO with the updated user's information
     */
    @PutMapping("/{userId}")
    public ResponseEntity<ApiResponseDto<UpdateUserResponseDto>> updateUser(@Valid @RequestBody UpdateUserRequestDto updateUserRequestDto) {

        UpdateUserResponseDto result = updateUserService.updateUser(updateUserRequestDto);
        ApiResponseDto<UpdateUserResponseDto> response = ApiResponseDto.success(result);
        return ResponseEntity.ok(response);
    }

    /**
     * Deletes a user by user ID.
     *
     * @param userId the ID of the user to delete
     * @return a ResponseEntity containing a success response (no data)
     */
    @DeleteMapping("/{userId}")
    public ResponseEntity<ApiResponseDto<Void>> deleteUser(@PathVariable Long userId) {

        deleteUserService.deleteUser(userId);
        return ResponseEntity.ok(ApiResponseDto.success(null));
    }

    /**
     * Authenticates a user and creates a security context.
     *
     * @param loginRequestDto contains the user ID and password for authentication
     * @param request the HTTP request object
     * @param response the HTTP response object
     * @return a ResponseEntity containing a success message wrapped in ApiResponseDto if authentication is successful
     */
    @PostMapping("/login")
    public ResponseEntity<ApiResponseDto<String>> login(@RequestBody LoginRequestDto loginRequestDto, HttpServletRequest request,
                                                        HttpServletResponse response) {

        // Create an unauthenticated token with user ID and password
        UsernamePasswordAuthenticationToken token = UsernamePasswordAuthenticationToken.unauthenticated(
                loginRequestDto.getUserId(), loginRequestDto.getPassword());

        // Authenticate the user with the authentication manager
        Authentication authentication = authenticationManager.authenticate(token);

        // Create an empty security context and set the authenticated user
        SecurityContext context = securityContextHolderStrategy.createEmptyContext();
        context.setAuthentication(authentication);

        // Store the security context in the session
        securityContextHolderStrategy.setContext(context);
        securityContextRepository.saveContext(context, request, response);

        // Create a successful response DTO
        ApiResponseDto<String> responseDto = ApiResponseDto.success("Login successful.");

        // Return the response entity with the success message
        return ResponseEntity.ok(responseDto);
    }
}