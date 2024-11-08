package com.devtkms.apiworld.common.config;

import com.devtkms.apiworld.usermanagement.security.CustomUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.security.web.context.SecurityContextRepository;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    /**
     * Configures the security filter chain for HTTP requests.
     *
     * @param http the HttpSecurity object to configure
     * @return the configured SecurityFilterChain
     * @throws Exception if an error occurs while configuring the security
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        SecurityContextRepository repo = new HttpSessionSecurityContextRepository();
        http
                .csrf((csrf) -> csrf.disable()) // Disable CSRF protection
                .authorizeHttpRequests((authorize) -> authorize
                        .requestMatchers("/swagger-ui/**", "/v3/api-docs/**").permitAll() // Allow access to Swagger UI
                        .requestMatchers("/api/user/register").permitAll() // Allow access to user registration
                        .requestMatchers("/api/user/{userId}").permitAll() // Allow access to user information by ID
                        .requestMatchers("/api/user/login").permitAll() // Allow access to user login
                        .requestMatchers("/api/file/register").permitAll() // Allow access to file registration
                        .requestMatchers("/api/file/async").permitAll() // Allow access to asynchronous file upload
                        .requestMatchers("/api/file/serialize").permitAll() // Allow access to object serialization
                        .requestMatchers("/api/file/deserialize").permitAll() // Allow access to object deserialization
                        .requestMatchers("/api/sales/aggregate").permitAll()
                        .requestMatchers("/api/products").permitAll()
                        .requestMatchers("/api/products/{id}").permitAll()
                        .requestMatchers("/api/filter/items").permitAll()
                        .requestMatchers("/api/reflection/class-info/{className}").permitAll()
                        .requestMatchers("/api/parallel/process").permitAll()
                        .requestMatchers("/api/data-processing/process").permitAll()
                        .requestMatchers("/api/resources/process").permitAll()
                        .anyRequest().authenticated() // All other requests require authentication
                )
                .securityContext((context) -> context
                        .securityContextRepository(repo)); // Set the security context repository
        return http.build(); // Build and return the security filter chain
    }

    /**
     * Creates an AuthenticationManager bean for managing authentication.
     *
     * @param userDetailsService the custom UserDetailsService for loading user-specific data
     * @param passwordEncoder the PasswordEncoder for encoding passwords
     * @return the configured AuthenticationManager
     */
    @Bean
    public AuthenticationManager authenticationManager(
            CustomUserDetailsService userDetailsService,
            PasswordEncoder passwordEncoder) {

        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService); // Set the custom UserDetailsService
        authenticationProvider.setPasswordEncoder(passwordEncoder); // Set the password encoder

        ProviderManager providerManager = new ProviderManager(authenticationProvider); // Create the authentication provider manager
        providerManager.setEraseCredentialsAfterAuthentication(false); // Prevent erasure of credentials after authentication

        return providerManager; // Return the configured AuthenticationManager
    }

    /**
     * Provides a PasswordEncoder bean for encoding passwords using BCrypt.
     *
     * @return a BCryptPasswordEncoder instance
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(); // Return a new BCryptPasswordEncoder
    }
}