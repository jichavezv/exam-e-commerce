package com.metaphorce.exam.ecommerce.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Data Transfer Object for the authorization request.
 *
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthRequest {
    /**
     * The email of the user to authorize.
     */
    @NotBlank
    @Size(max = 128)
    private String email;

    /**
     * The password of the user to authorize.
     */
    @NotBlank
    @Size(max = 512)
    private String password;
}
