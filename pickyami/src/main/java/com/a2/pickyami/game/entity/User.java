package com.a2.pickyami.game.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Entity
@Data
@Table(name = "User")
public class User implements UserDetails {

    @Id
    @GeneratedValue
    private int id;

    @NotNull
    private String email;


    private String profile;


    @NotNull
    private String username;

    @NotNull
    private String password;


    private String phone;

    @Enumerated(EnumType.STRING)
    private Role role;

    @Column(unique = true, nullable = false, updatable = false)
    private String uid;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));//TOdo: Verify
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @PrePersist
    public void generateGameUid() {
        if (uid == null) {
            uid = UUID.randomUUID().toString();
        }
    }
}
