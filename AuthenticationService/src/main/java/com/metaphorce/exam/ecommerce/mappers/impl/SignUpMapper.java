package com.metaphorce.exam.ecommerce.mappers.impl;

import com.metaphorce.exam.ecommerce.dtos.SignUpResponse;
import com.metaphorce.exam.ecommerce.dtos.SignUpRequest;
import com.metaphorce.exam.ecommerce.entities.User;
import com.metaphorce.exam.ecommerce.exceptions.ResourceNotFoundException;
import com.metaphorce.exam.ecommerce.mappers.MapperI;
import com.metaphorce.exam.ecommerce.services.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Mapper interface to translate between user entities objects and
 * sign up DTO objects.
 */
@Component
public class SignUpMapper implements MapperI<User, SignUpResponse, SignUpRequest>
{
    @Autowired
    RoleService roleService;

    /**
     * Translates from the SignUpRequest DTO to the User entity object.
     *
     * @param request a SignUpRequest DTO
     * @return a User entity object
     */
    @Override
    public User requestToEntity(SignUpRequest request) {
        return User.builder()
            .username(request.getUsername())
            .firstName(request.getFirstName())
            .lastName(request.getLastName())
            .email(request.getEmail())
            .telephone(request.getTelephone())
            .password(request.getPassword())
            .role(roleService.getRoleByName("seller"))
            .build();
    }

    /**
     * Translates from the User entity object to the SignUpResponse DTO.
     *
     * @param entity a User entity object
     * @return a SignUpResponse DTO
     */
    @Override
    public SignUpResponse entityToResponse(User entity) {
        return SignUpResponse.builder()
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
