package com.fp.fp.repositories;

import com.fp.fp.models.Brands;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BrandRepository extends JpaRepository<Brands, Long> {
    Optional<Brands> findByBrandName(String brandName);
}
