package com.fp.fp.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Users implements Serializable, UserDetails {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private long userId;
    @Basic
    private String username;
    @Basic
    private String password;
    @Basic
    private String confirmPassword;
    @Basic
    private String firstName;
    @Basic
    private String lastName;
    @Basic
    private String email;
    @Basic
    private String phone;
    @ManyToMany(fetch=FetchType.EAGER , cascade = CascadeType.ALL)
    @JoinTable(name = "users_roles", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    private List<Roles> userRole = new ArrayList<>();

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();
        for (Roles roles: this.userRole) {
            authorities.add(new SimpleGrantedAuthority(roles.getName()));
        }
        return authorities;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
