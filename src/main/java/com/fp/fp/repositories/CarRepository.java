package com.fp.fp.repositories;

import com.fp.fp.models.Cars;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CarRepository extends JpaRepository<Cars, Long> {
    Optional<Long> findMaxIdByCarId(Cars car);
}
