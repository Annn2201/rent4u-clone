package com.fp.fp.models;

import jakarta.persistence.*;
import lombok.*;
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
    @SequenceGenerator(name = "my_entity_seq", sequenceName = "MY_ENTITY_SEQ", allocationSize = 5)
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "my_entity_seq")
    private Long userId;
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
    private String newPassword;
    private String confirmNewPassword;
    private String resetPasswordToken;
    @ManyToMany(fetch=FetchType.EAGER)
    @JoinTable(name = "users_roles", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    private List<Roles> userRole = new ArrayList<>();

    @OneToMany(mappedBy = "users")
    private List<Cars> cars;

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

    @Override
    public String toString() {
        return
                "userId=" + userId +
                ", username='" + username;
    }
}
