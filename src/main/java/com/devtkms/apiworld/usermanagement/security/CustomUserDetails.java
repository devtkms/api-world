package com.devtkms.apiworld.usermanagement.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

public class CustomUserDetails implements UserDetails {

    private Long userId; // The user's ID
    private String password; // The user's password

    /**
     * Constructor to initialize CustomUserDetails with user ID and password.
     *
     * @param userId the user's ID
     * @param password the user's password
     */
    public CustomUserDetails(Long userId, String password) {
        this.userId = userId;
        this.password = password;
    }

    /**
     * Gets the user's ID.
     *
     * @return the user's ID
     */
    public Long getUserId() {
        return userId;
    }

    /**
     * Returns the collection of authorities granted to the user.
     *
     * @return an empty list, as no authorities are specified
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.emptyList(); // No authorities assigned
    }

    /**
     * Returns the user's password.
     *
     * @return the user's password
     */
    @Override
    public String getPassword() {
        return password;
    }

    /**
     * Returns the username for the user.
     *
     * @return the user's ID as a string
     */
    @Override
    public String getUsername() {
        return userId.toString(); // Return user ID as the username
    }

    /**
     * Indicates whether the user's account has expired.
     *
     * @return true, as the account is considered non-expired
     */
    @Override
    public boolean isAccountNonExpired() {
        return true; // The account is non-expired
    }

    /**
     * Indicates whether the user account is locked.
     *
     * @return true, as the account is considered non-locked
     */
    @Override
    public boolean isAccountNonLocked() {
        return true; // The account is non-locked
    }

    /**
     * Indicates whether the user's credentials (password) have expired.
     *
     * @return true, as the credentials are considered non-expired
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true; // The credentials are non-expired
    }

    /**
     * Indicates whether the user's account is enabled.
     *
     * @return true, as the account is considered enabled
     */
    @Override
    public boolean isEnabled() {
        return true; // The account is enabled
    }
}