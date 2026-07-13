package com.ordermanagement.user.model.entity;

import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;

@Entity
@Table(name = "roles")
public class Role implements GrantedAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, unique = true, length = 50)
    private RoleName name;

    public enum RoleName {
        ROLE_CUSTOMER,
        ROLE_ADMIN,
        ROLE_VENDOR
    }

    public Role() {
    }

    public Role(RoleName name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public RoleName getName() {
        return name;
    }

    @Override
    public String getAuthority() {
        return name.name();
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(RoleName name) {
        this.name = name;
    }
}