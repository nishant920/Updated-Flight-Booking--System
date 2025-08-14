package com.fbs.db_api.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table(name = "users")
public class AppUser {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    UUID id;
    @Column(nullable = false)
    String name;
    @Column(unique = true, nullable = false)
    String email;
    @Column(nullable = false)
    String password;
    @Column(unique = true, nullable = false)
    Long number;
    @Column(nullable = false)
    boolean isVerified;
    String userType;
    String status;
    LocalDateTime createdAt;
    LocalDateTime updatedAt;
}
