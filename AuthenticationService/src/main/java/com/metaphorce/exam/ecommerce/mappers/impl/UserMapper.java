package com.metaphorce.exam.ecommerce.mappers.impl;

import com.metaphorce.exam.ecommerce.dtos.UserResponse;
import com.metaphorce.exam.ecommerce.dtos.UserRequest;
import com.metaphorce.exam.ecommerce.entities.User;
import com.metaphorce.exam.ecommerce.mappers.MapperI;
import com.metaphorce.exam.ecommerce.services.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Mapper interface to translate between user entities objects and user DTO objects.
 */
@Component
public class UserMapper implements MapperI<User, UserResponse, UserRequest>
{
    @Autowired
    RoleService roleService;

    /**
     * Translates from the UserRequest DTO to the User entity object.
     *
     * @param request a UserRequest DTO
     * @return a User entity object
     */
    @Override
    public User requestToEntity(UserRequest request) {
        return User.builder()
            .username(request.getUsername())
            .firstName(request.getFirstName())
            .lastName(request.getLastName())
            .email(request.getEmail())
            .telephone(request.getTelephone())
            .password(request.getPassword())
            .role(roleService.getRoleByName(request.getRole()))
            .build();
    }

    /**
     * Translates from the User entity object to the UserResponse DTO.
     *
     * @param entity a User entity object
     * @return a UserResponse DTO
     */
    @Override
    public UserResponse entityToResponse(User entity) {
        return UserResponse.builder()
            .id(entity.getId())
            .username(entity.getUsername())
            .firstName(entity.getFirstName())
            .lastName(entity.getLastName())
            .email(entity.getEmail())
            .telephone(entity.getTelephone())
            .role(entity.getRole().getName())
            .build();
    }
}
