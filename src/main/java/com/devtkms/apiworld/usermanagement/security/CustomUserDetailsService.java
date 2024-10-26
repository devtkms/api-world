package com.devtkms.apiworld.usermanagement.security;

import com.devtkms.apiworld.usermanagement.dao.UsersDao;
import com.devtkms.apiworld.usermanagement.entity.UsersEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UsersDao usersDao; // DAO for accessing user data

    /**
     * Loads user details by username (user ID).
     *
     * @param username the username (user ID) of the user to be loaded
     * @return UserDetails containing the user's information
     * @throws UsernameNotFoundException if the user cannot be found
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try {
            Long userId = Long.parseLong(username); // Convert username to user ID
            UsersEntity user = usersDao.selectUser(userId); // Retrieve user from the database
            if (user == null) {
                throw new UsernameNotFoundException("User not found with userId: " + userId);
            }

            return new CustomUserDetails(
                    user.getUserId(),
                    user.getPassword()); // Create and return CustomUserDetails with user ID and password
        } catch (NumberFormatException e) {
            throw new UsernameNotFoundException("Invalid userId format: " + username, e);
        } catch (Exception e) {
            throw new UsernameNotFoundException("Error occurred while fetching user with userId: " + username, e);
        }
    }
}