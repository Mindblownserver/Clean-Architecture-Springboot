package com.ums.infrastructure.persistence.entities;

import com.ums.domain.UserState;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "users")
public class UserEntity {
    @Id
    @Column(unique = true)
    private String cin;

    private String username;
    private String passwdHash;

    @Enumerated(EnumType.STRING)
    private UserState state;

    @OneToOne
    private RoleEntity role;
}
