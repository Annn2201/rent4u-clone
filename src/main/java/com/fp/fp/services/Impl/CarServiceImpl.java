package com.fp.fp.services.Impl;

import com.fp.fp.config.JwtUtilities;
import com.fp.fp.dtos.CarDTO;
import com.fp.fp.exceptions.CustomerException;
import com.fp.fp.models.Cars;
import com.fp.fp.models.Users;
import com.fp.fp.repositories.CarRepository;
import com.fp.fp.repositories.UserRepository;
import com.fp.fp.services.CarService;
import com.fp.fp.services.MinIOService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CarServiceImpl implements CarService {
    private final CarRepository carRepository;
    private final JwtUtilities jwtUtilities;
    private final UserRepository userRepository;
    private final MinIOService minIOService;
    @Override
    public void addCar(Cars car, MultipartFile file) {
            car.setCarImageUrl(minIOService.putCarImage(car.getCarName(), file));
            carRepository.save(car);
    }
    @Override
    public List<CarDTO> getAllCars() {
        List<Cars> cars = carRepository.findAll();
        List<CarDTO> carDTOS = cars.stream().map(car -> {
            CarDTO carDTO = new CarDTO();
            BeanUtils.copyProperties(car, carDTO);
            return carDTO;
        }).collect(Collectors.toList());
        return carDTOS;
    }
    @Override
    public CarDTO getCarById(Long carId) {
        Cars car = carRepository.findById(carId).orElseThrow(() -> new CustomerException("Car not found "));
        CarDTO carDTO = new CarDTO();
        BeanUtils.copyProperties(car, carDTO);
        return carDTO;
    }
    @Override
    public void updateCar(CarDTO carDTO) {
        Cars cars = carRepository.findById(carDTO.getCarId()).orElseThrow(() -> new CustomerException("Car not found "));
        cars.setCarName(carDTO.getCarName());
        cars.setBrand(carDTO.getBrand());
        cars.setCarType(carDTO.getCarType());
        cars.setColor(carDTO.getColor());
        cars.setRentCost(carDTO.getRentCost());
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
