package com.a2.pickyami.game.entity;

import com.a2.pickyami.game.enums.Role;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Players implements UserDetails {

    @Id
    @GeneratedValue
    private int id;

    @NotNull
    private String fullName;

    @NotNull
    private String email;


    private String profile;


    public @NotNull String getUsername() {
        return uid;
    }

    @NotNull
    private String password;


    @Enumerated(EnumType.STRING)
    private Role role;

    @Column(unique = true, nullable = false, updatable = false)
    private String uid;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (role == null) {
            return List.of(new SimpleGrantedAuthority("ROLE_"+Role.user));
        }
        return List.of(new SimpleGrantedAuthority("ROLE_"+role.name()));
    }

    @Override
    public boolean isAccountNonExpired() {
        return true; // Account is not expired
    }

    @Override
    public boolean isAccountNonLocked() {
        return true; // Account is not locked
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true; // Credentials are not expired
    }

    @Override
    public boolean isEnabled() {
        return true; // Account is enabled
    }


    @PrePersist
    public void generateGameUid() {
        if (uid == null) {
            uid = UUID.randomUUID().toString();
        }
    }
}
