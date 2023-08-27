package com.fp.fp.controllers;

import com.fp.fp.dtos.*;
import com.fp.fp.services.CarService;
import com.fp.fp.services.UserService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final CarService carService;
    @GetMapping("/")
    public String showHomepage(HttpServletRequest request,
                               Model model) {
        List<CarDTO> allCar = carService.getAllCars();
        UserDTO currentUser = userService.getCurrentUser();
        model.addAttribute("cars", allCar);
        model.addAttribute("currentUser", currentUser);
        return "index";
    }
    @GetMapping("/user/{username}")
    public String showInfoPage(@PathVariable(value = "username") String username,
                               HttpServletRequest request,
                               Model model) {
        UserDTO currentUser = userService.getCurrentUser();
        model.addAttribute("currentUser", currentUser);
        return "info-user";
    }
    @GetMapping("/car/{carId}")
    public String showInfoCar(@PathVariable(value = "carId") Long carId,
                              Model model,
                              HttpServletRequest request) {
        UserDTO currentUser = userService.getCurrentUser();
        CarDTO car = carService.getCarById(carId);
        model.addAttribute("currentUser", currentUser);
        model.addAttribute("car", car);
        return "car-for-rent";
    }
    @PostMapping("/car/{carId}")
    public String rentCar(@PathVariable(value = "carId") Long carId,
                          HttpServletRequest request) {
        CarDTO carDTO = carService.getCarById(carId);
        carService.rentCar(request, carDTO);
        return "redirect:/car/{carId}?success";
    }
    @GetMapping("/user/{username}/password")
    public String showChangePassword(@PathVariable(value = "username") String username,
                                     HttpServletRequest request,
                                     Model model) {
        UserDTO currentUser = userService.getCurrentUser();
        model.addAttribute("currentUser", currentUser);
        return "change-password";
    }
    @PostMapping("/user/{username}/password")
    public String changePassword(@PathVariable(value = "username") String username,
                                 ChangePasswordDTO changePasswordDTO) {
        if (!userService.checkValidOldPassword(changePasswordDTO.getPassword(), username)){
            return "redirect:/user/{username}/password?invalid";
        }
        userService.updatePassword(changePasswordDTO.getNewPassword(), username);
        return "redirect:/user/{username}/password?success";
    }
}
