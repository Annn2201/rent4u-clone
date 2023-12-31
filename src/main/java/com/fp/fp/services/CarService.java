package com.fp.fp.services;

import com.fp.fp.dtos.CarDTO;
import com.fp.fp.dtos.UserDTO;
import com.fp.fp.models.Cars;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface CarService {
    void addCar(Cars cars, MultipartFile file);
    List<CarDTO> getAllCars();
    CarDTO getCarById(Long carId);
    void updateCar(CarDTO carDTO);
    void rentCar(HttpServletRequest request, CarDTO carDTO);
    void deleteCar(Long carID);
}
