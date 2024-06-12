package com.metaphorce.exam.ecommerce.services;

import com.metaphorce.exam.ecommerce.exceptions.ResourceNotFoundException;
import com.metaphorce.exam.ecommerce.exceptions.UserAlreadyExistsException;
import com.metaphorce.exam.ecommerce.entities.User;
import com.metaphorce.exam.ecommerce.repositories.UserRepository;
import jakarta.validation.ConstraintViolationException;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * Service for handling user operations.
 */
@Service
public class UserService
{
    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;

    /**
     * Constructs a UserService with the necessary dependencies.
     *
     * @param repository the repository for the user model.
     * @param processor the processor for user bulk operations.
     * @param passwordEncoder the password encoder.
     */
    @Autowired
    public UserService(UserRepository repository, PasswordEncoder passwordEncoder) {
        this.repository = repository;
         this.passwordEncoder = passwordEncoder;
    }

    /**
     * Creates a new user.
     *
     * @param user the User object representing the user to be created
     * @return the created User object
     * @throws UserAlreadyExistsException when a user with the given email already exists
     */
    public User createUser(User user) {
        if ( repository.existsByEmail(user.getEmail()) )
            throw new UserAlreadyExistsException(String.format("A user with email=%s already exists.",
                                                               user.getEmail()));
        if ( user.getPassword() != null )
            user.setPassword(passwordEncoder.encode(user.getPassword()));
        return repository.save(user);
    }

    /**
     * Gets the list of all users.
     *
     * @return a list of User objects
     */
    public List<User> getAllUsers() {
        return repository.findAll();
    }

    /**
     * Gets an user by identifier.
     *
     * @param id the id of the user
     * @return an User object
     * @throws ResourceNotFoundException if the user is not found
     */
    public User getUserById(Long id) {
        return repository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException(String.format("User with id=%d not found",
                                                                           id)));
    }

    /**
     * Deletes an user by identifier.
     *
     * @param id the id of the user
     * @throws ResourceNotFoundException if the user is not found
     */
    public void deleteUser(Long id) {
        User user = repository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException(String.format("User with id=%d not found",
                                                                           id)));
        repository.delete(user);
    }

    /**
     * Updates an existing user.
     *
     * @param id the id of the user
     * @param user an User object
     * @return the modificated User oject
     * @throws ResourceNotFoundException if the user is not found
     */
    public User updateUser(Long id, User user) {
        if ( ! repository.existsById(id) )
            throw new ResourceNotFoundException(String.format("User with id=%d not found", id));
        user.setId(id);
        if ( user.getPassword() != null )
            user.setPassword(passwordEncoder.encode(user.getPassword()));
        return repository.save(user);
    }

    /**
     * Get the names of all users.
     *
     * @return a list of strings
     */
    public List<String> getNames() {
        return repository.findNames();
    }
}
