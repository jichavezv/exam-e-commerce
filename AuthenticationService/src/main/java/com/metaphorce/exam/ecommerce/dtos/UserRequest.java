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
 * Data Transfer Object representing user information.
 *
 */
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class UserRequest
{
    /**
     * The username of the user.
     */
    @NotBlank
    @Size(min = 2, max = 64)
    private String username;

    /**
     * The first name of the user.
     */
    @NotBlank
    @Size(max = 64)
    private String firstName;

    /**
     * The last name of the user.
     */
    @NotBlank
    @Size(max = 64)
    private String lastName;

    /**
     * The email of the user.
     */
    @NotBlank
    @Email
    @Size(max = 128)
    private String email;

    /**
     * The telephone of the user.
     */
    @NotBlank
    @Size(max = 64)
    private String telephone;

    /**
     * The password of the user.
     */
    @NotBlank
    @Size(min = 6, max = 128)
    private String password;

    /**
     * The role of the user.
     */
    @NotBlank
    @Size(max = 64)
    @Pattern(regexp="^(seller|buyer)$")
    private String role;
}
