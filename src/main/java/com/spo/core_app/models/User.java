package com.spo.core_app.models;

import com.spo.core_app.enums.UserStatus;
import com.spo.core_app.enums.UserType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@SuperBuilder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User extends GlobalRecord {

    private String userId;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column(unique = true, nullable = false)
    private String email;

    private String password;

    private String phoneNumber;

    @Enumerated(EnumType.STRING)
    private UserType userType;

    @Enumerated(EnumType.STRING)
    private UserStatus status;

    @ManyToOne
    private Company company;

    private String addressLine1;

    private String addressLine2;

    private String addressLine3;

    private LocalDate joiningDate;

    private LocalDateTime lastLoginDate;

    private Boolean emailVerified;

    @ManyToMany
    private List<Role> roles;

    private Boolean mfaEnabled;
}
