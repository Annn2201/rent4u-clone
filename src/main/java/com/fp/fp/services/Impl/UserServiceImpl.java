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
import org.springframework.beans.BeanUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final JwtUtilities jwtUtilities;
    private final PasswordEncoder passwordEncoder;
    @Override
    public void saveUser(Users user) {
        Roles roles = roleRepository.findByName("ROLE_USER");
        if (roles == null) {
            roles = roleRepository.save(new Roles("ROLE_USER"));
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setConfirmPassword(passwordEncoder.encode(user.getConfirmPassword()));
        user.setUserRole(List.of(roles));
        userRepository.save(user);
    }

    @Override
    public UserDTO getUserByUsername(String username) {
        Users user = userRepository.findByUsername(username).orElse(new Users());
        UserDTO dto = new UserDTO();
        BeanUtils.copyProperties(user, dto);
        return dto;
    }

    @Override
    public UserDTO getCurrentUser() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        UserDTO currentUser = getUserByUsername(username);
        return currentUser;
    }

    @Override
    public void updatePassword(String newPassword,
                               String username) {
        Users userWannaChangePassword = userRepository.findByUsername(username).orElseThrow(() -> new CustomerException("User not found!"));
        userWannaChangePassword.setPassword(passwordEncoder.encode(newPassword));
        saveUser(userWannaChangePassword);
    }

    @Override
    public boolean checkValidOldPassword(String password, String username) {
        UserDTO userDTO = getUserByUsername(username);
        return passwordEncoder.matches(password, userDTO.getPassword());
    }
}
