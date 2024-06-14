package com.metaphorce.exam.ecommerce.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import java.time.LocalDateTime;
import java.util.Collection;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

/**
 * Entity class representing a user.
 */
@Entity
@Table(name = "users")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User
{
    /**
     * The unique identifier of the user.
     */
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "role_id", referencedColumnName = "id")
    private Role role;

    /**
     * The username of the user.
     */
    @Column(nullable = false, length = 64, unique=true)
    private String username;

    /**
     * The first name of the user.
     */
    @Column(length = 64)
    private String firstName;

    /**
     * The last name of the user.
     */
    @Column(length = 64)
    private String lastName;

    /**
     * The email address of the user.
     */
    @Column(nullable = false, length = 128, unique=true)
    private String email;

    /**
     * The telephone of the user.
     */
    @Column(nullable = false, length = 64)
    private String telephone;

    /**
     * The password of the user.
     */
    @Column(nullable = false, length = 64)
    private String password;

    /**
     * The creation time of the user.
     */
    @CreationTimestamp
    private LocalDateTime created_at;

    /**
     * The update time of the user.
     */
    @UpdateTimestamp
    private LocalDateTime modified_at;

}
