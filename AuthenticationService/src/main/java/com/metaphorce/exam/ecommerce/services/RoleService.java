package com.metaphorce.exam.ecommerce.services;

import com.metaphorce.exam.ecommerce.entities.Role;
import com.metaphorce.exam.ecommerce.exceptions.ResourceNotFoundException;
import com.metaphorce.exam.ecommerce.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Service for handling role operations.
 */
@Service
public class RoleService
{
    private final RoleRepository repository;

    /**
     * Constructs a RoleService with the necessary dependencies.
     *
     * @param repository the repository for the user model.
     */
    @Autowired
    public RoleService(RoleRepository repository) {
        this.repository = repository;
    }

    /**
     * Gets a role by name.
     *
     * @param name the name of the role
     * @return a Role object
     * @throws ResourceNotFoundException if the role is not found
     */
    public Role getRoleByName(String name) {
        return repository.findByName(name)
            .orElseThrow(() -> new ResourceNotFoundException(String.format("Role with name=%s not found",
                                                                           name)));
    }
}
