package com.fp.fp.services.Impl;

import com.fp.fp.config.JwtUtilities;
import com.fp.fp.dtos.UserDTO;
import com.fp.fp.exceptions.CustomerException;
import com.fp.fp.models.Roles;
import com.fp.fp.models.Users;
import com.fp.fp.repositories.RoleRepository;
import com.fp.fp.repositories.UserRepository;
import com.fp.fp.services.UserService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final JwtUtilities jwtUtilities;
    @Override
    public void saveUser(Users user) {
        Roles roles = roleRepository.findByName("ROLE_USER");
        if (roles == null) {
            roles = roleRepository.save(new Roles("ROLE_USER"));
        }
        user.setUserRole(List.of(roles));
        userRepository.save(user);
    }

    @Override
    public UserDTO getUserByUsername(String username) {
        Users users = userRepository.findByUsername(username).orElse(new Users());
        return UserDTO.builder()
                .firstName(users.getFirstName())
                .lastName(users.getLastName())
                .email(users.getEmail())
                .username(users.getUsername())
                .password(users.getPassword())
                .confirmPassword(users.getConfirmPassword())
                .userRole(users.getUserRole())
                .build();
    }

    @Override
    public UserDTO getCurrentUser(HttpServletRequest request) {
        String token = jwtUtilities.extractToken(request);
        String username = jwtUtilities.extractUsername(token);
        UserDTO currentUser = getUserByUsername(username);
        return currentUser;
    }
}
