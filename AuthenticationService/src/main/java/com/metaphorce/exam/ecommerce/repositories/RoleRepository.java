package com.metaphorce.exam.ecommerce.repositories;

import java.util.List;
import java.util.Optional;
import com.metaphorce.exam.ecommerce.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * Repository for performing CRUD operations on Role entities.
 */
@Repository
public interface RoleRepository extends JpaRepository<Role, String>
{
    /**
     * Finds a role by name.
     *
     * @param name the name of the role to find
     * @return an Optional object containing the Role, if it exists
     */
    public Optional<Role> findByName(String name);
}
