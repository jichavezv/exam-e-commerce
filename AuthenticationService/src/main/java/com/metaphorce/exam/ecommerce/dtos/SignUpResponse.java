package com.metaphorce.exam.ecommerce.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Data Transfer Object for the sign up response.
 *
 */
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class SignUpResponse
{
    /**
     * The id of the signed up user.
     */
    private Long id;

    /**
     * The username of the signed up user.
     */
    private String username;

    /**
     * The first name of the signed up user.
     */
    private String firstName;

    /**
     * The last name of the signed up user.
     */
    private String lastName;

    /**
     * The email of the signed up user.
     */
    private String email;

    /**
     * The telephone of the signed up user.
     */
    private String telephone;

    /**
     * The role of the signed up user.
     */
    private String role;
}
