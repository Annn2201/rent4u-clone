package com.fp.fp.services.Impl;

import com.fp.fp.dtos.BrandDTO;
import com.fp.fp.models.Brands;
import com.fp.fp.repositories.BrandRepository;
import com.fp.fp.services.BrandService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BrandServiceImpl implements BrandService {
    private final BrandRepository brandRepository;
    @Override
    public void addBrand(Brands brands) {
        brandRepository.save(brands);
    }

    @Override
    public Boolean checkExistBrand(String brandName) {
        Brands brands = brandRepository.findByBrandName(brandName).orElse(null);
        if (brands == null) {
            return false;
        }
        return true;
    }

    @Override
    public List<BrandDTO> getAllBrand() {
        List<Brands> allBrands = brandRepository.findAll();
        return allBrands.stream().map(brand -> BrandDTO.builder()
                .brandId(brand.getBrandId())
                .brandName(brand.getBrandName())
                .cars(brand.getCars())
                .build()).collect(Collectors.toList());
    }
}
