package com.fp.fp.controllers;

import com.fp.fp.dtos.BrandDTO;
import com.fp.fp.dtos.CarDTO;
import com.fp.fp.dtos.TypeDTO;
import com.fp.fp.dtos.UserDTO;
import com.fp.fp.models.Brands;
import com.fp.fp.models.Cars;
import com.fp.fp.services.BrandService;
import com.fp.fp.services.CarService;
import com.fp.fp.services.TypeService;
import com.fp.fp.services.UserService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin")
public class CarController {
    private final UserService userService;
    private final CarService carService;
    private final BrandService brandService;
    private final TypeService typeService;
    @GetMapping("/")
    public String homepage(@ModelAttribute("car") CarDTO carDTO,
                           HttpServletRequest request,
                           Model model) {
        List<TypeDTO> types = typeService.getAllCarType();
        List<BrandDTO> brands = brandService.getAllBrand();
        List<CarDTO> cars = carService.getAllCars();
        UserDTO currentUser = userService.getCurrentUser(request);
        model.addAttribute("types", types);
        model.addAttribute("brands", brands);
        model.addAttribute("cars", cars);
        model.addAttribute("currentUser", currentUser);
        return "manage-car";
    }
    @PostMapping("/car")
    public String addCar(Cars cars,
                         Model model) {
//        if (brandService.checkExistBrand(cars.getBrand().getBrandName()) == false) {
//            Brands brands = new Brands();
//            brands.setBrandName(cars.getBrand().getBrandName());
//        }
        carService.addCar(cars);
        return "redirect:/admin/";
    }
    @PostMapping("/car/{carId}")
    public String updateCar(@PathVariable(value = "carId") Long carId) {
        CarDTO findCarById = carService.getCarById(carId);
        carService.updateCar(findCarById);
        return "redirect:/admin/car";
    }
    @DeleteMapping("/car/{carId}")
    public ResponseEntity deleteCar(@PathVariable(value = "carId") Long carId) {
        carService.deleteCar(carId);
        return ResponseEntity.ok().build();
    }
}
