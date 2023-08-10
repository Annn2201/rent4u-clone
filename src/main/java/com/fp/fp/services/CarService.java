package com.fp.fp.services;

import com.fp.fp.dtos.CarDTO;
import com.fp.fp.models.Cars;

import java.util.List;

public interface CarService {
    void addCar(Cars cars);
    List<CarDTO> getAllCars();
    CarDTO getCarById(Long carId);
    void updateCar(CarDTO carDTO);
    void deleteCar(Long carID);
}
