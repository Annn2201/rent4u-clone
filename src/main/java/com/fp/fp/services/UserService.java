package com.fp.fp.services;

import com.fp.fp.dtos.UserDTO;
import com.fp.fp.models.Users;
import jakarta.servlet.http.HttpServletRequest;

public interface UserService {
    void saveUser(Users users);
    UserDTO getUserByUsername(String username);
    UserDTO getCurrentUser();

    void updatePassword(String newPassword, String username);

    boolean checkValidOldPassword(String password, String username);
}
