package com.fp.fp.services.Impl;

import com.fp.fp.config.JwtUtilities;
import com.fp.fp.dtos.CarDTO;
import com.fp.fp.dtos.UserDTO;
import com.fp.fp.exceptions.CustomerException;
import com.fp.fp.models.Cars;
import com.fp.fp.models.Users;
import com.fp.fp.repositories.CarRepository;
import com.fp.fp.repositories.UserRepository;
import com.fp.fp.services.CarService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CarServiceImpl implements CarService {
    private final CarRepository carRepository;
    private final JwtUtilities jwtUtilities;
    private final UserRepository userRepository;
    @Override
    public void addCar(Cars cars) {
        carRepository.save(cars);
    }
    @Override
    public List<CarDTO> getAllCars() {
        List<Cars> cars = carRepository.findAll();
        return cars.stream().map(car ->CarDTO.builder()
                .carId(car.getCarId())
                .carName(car.getCarName())
                .brand(car.getBrand())
                .carType(car.getCarType())
                .color(car.getColor())
                .rentCost(car.getLentCost())
                .users(car.getUsers())
                .build()).collect(Collectors.toList());

    }
    @Override
    public CarDTO getCarById(Long carId) {
        Cars car = carRepository.findById(carId).orElseThrow(() -> new CustomerException("Car not found "));
        return CarDTO.builder()
                .carId(car.getCarId())
                .carName(car.getCarName())
                .brand(car.getBrand())
                .carType(car.getCarType())
                .color(car.getColor())
                .rentCost(car.getLentCost())
                .users(car.getUsers())
                .build();
    }
    @Override
    public void updateCar(CarDTO carDTO) {
        Cars cars = carRepository.findById(carDTO.getCarId()).orElseThrow(() -> new CustomerException("Car not found "));
        cars.setCarName(carDTO.getCarName());
        cars.setBrand(carDTO.getBrand());
        cars.setCarType(carDTO.getCarType());
        cars.setColor(carDTO.getColor());
        cars.setLentCost(carDTO.getRentCost());
        cars.setUsers(carDTO.getUsers());
        carRepository.save(cars);
    }

    @Override
    public void rentCar(HttpServletRequest request, CarDTO carDTO) {
        String token = jwtUtilities.extractToken(request);
        String username = jwtUtilities.extractUsername(token);
        Users users = userRepository.findByUsername(username).orElseThrow(() -> new CustomerException("User not found"));
        carDTO.setUsers(users);
        updateCar(carDTO);
    }

    @Override
    public void deleteCar(Long carID) {
        Cars cars = carRepository.findById(carID).orElseThrow(() -> new CustomerException("Car not found"));
        carRepository.delete(cars);
    }
}
