package com.metaphorce.exam.ecommerce.services;

import com.metaphorce.exam.ecommerce.dtos.UserInfoDetails;
import com.metaphorce.exam.ecommerce.entities.User;
import com.metaphorce.exam.ecommerce.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Service to get the user details.
 */
@Service
public class UserInfoService implements UserDetailsService
{
    private final UserRepository repository;

    /**
     * Constructs a UserInfoService with the necessary dependencies.
     *
     * @param repository the repository for accessing user data.
     */
    @Autowired
    public UserInfoService(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<User> userDetail = repository.findByEmail(email);
        // Converting userDetail to UserDetails
        return userDetail.map(UserInfoDetails::new)
            .orElseThrow(() -> new UsernameNotFoundException(String.format("User with email=%s not found",
                                                                           email)));
    }

}
