package com.fp.fp.controllers;

import com.fp.fp.dtos.BrandDTO;
import com.fp.fp.dtos.CarDTO;
import com.fp.fp.dtos.TypeDTO;
import com.fp.fp.dtos.UserDTO;
import com.fp.fp.services.BrandService;
import com.fp.fp.services.CarService;
import com.fp.fp.services.TypeService;
import com.fp.fp.services.UserService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
        UserDTO currentUser = userService.getCurrentUser(request);
        model.addAttribute("cars", allCar);
        model.addAttribute("currentUser", currentUser);
        return "index";
    }
    @GetMapping("/car/{carId}")
    public String showInfoCar(@PathVariable(value = "carId") Long carId,
                              Model model,
                              HttpServletRequest request) {
        UserDTO currentUser = userService.getCurrentUser(request);
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
}
