package com.metaphorce.exam.ecommerce.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Data Transfer Object for the sign up request.
 *
 */
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class SignUpRequest
{
    /**
     * The username of the new user to sign up.
     */
    @NotBlank
    @Size(min = 2, max = 64)
    private String username;

    /**
     * The first name of the new user to sign up.
     */
    @NotBlank
    @Size(max = 64)
    private String firstName;

    /**
     * The last name of the new user to sign up.
     */
    @NotBlank
    @Size(max = 64)
    private String lastName;

    /**
     * The email of the new user to sign up.
     */
    @NotBlank
    @Email
    @Size(max = 128)
    private String email;

    /**
     * The telephone of the new user to sign up.
     */
    @NotBlank
    @Size(max = 64)
    private String telephone;

    /**
     * The password of the new user to sign up.
     */
    @NotBlank
    @Size(min = 6, max = 128)
    private String password;
}
