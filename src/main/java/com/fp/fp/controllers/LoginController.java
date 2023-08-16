package com.fp.fp.controllers;

import com.fp.fp.config.JwtUtilities;
import com.fp.fp.dtos.LoginDTO;
import com.fp.fp.dtos.UserDTO;
import com.fp.fp.models.Roles;
import com.fp.fp.models.Users;
import com.fp.fp.services.UserService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.Role;
import org.apache.catalina.User;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class LoginController {
    private final UserService userService;
    private final JwtUtilities jwtUtilities;

    @GetMapping("/register")
    public String showRegisterPage(Model model) {
        Users users = new Users();
        model.addAttribute("user", users);
        return "register";
    }

    @PostMapping("/register")
    public String register(Users user,
                           Model model,
                           BindingResult result) {
        UserDTO checkedUser = userService.getUserByUsername(user.getUsername());
        if (checkedUser.getUsername() != null) {
            result.rejectValue("username", null,
                    "Username is used !!!");
            model.addAttribute("user", user);
            return "/register";
        }
        userService.saveUser(user);
        return "redirect:/register?success";
    }

    @GetMapping("/login")
    public String showLoginPage() {
        return "login";
    }

    @PostMapping("/login")
    public String login(LoginDTO loginDTO,
                        HttpServletResponse response) {
        UserDTO loginUser = userService.getUserByUsername(loginDTO.getUsername());
        if (loginUser == null) {
            return "redirect:/login?invalid";
        }
        List<String> roleNames = new ArrayList<>();
        for (Roles roles : loginUser.getUserRole()) {
            roleNames.add(roles.getName());
        }
        String jwtToken = jwtUtilities.generateToken(loginDTO.getUsername(), roleNames);
        Cookie jwtCookie = new Cookie("jwt", jwtToken);
        response.addCookie(jwtCookie);
        for (String role : roleNames) {
            if (role.equals("ROLE_ADMIN")) {
                return "redirect:/admin/";
            }
        }
    return "redirect:/";
    }
}
