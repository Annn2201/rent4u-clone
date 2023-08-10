package com.fp.fp.services;

import com.fp.fp.dtos.BrandDTO;
import com.fp.fp.models.Brands;

import java.util.List;

public interface BrandService {
    void addBrand(Brands brands);
    Boolean checkExistBrand(String brandName);
    List<BrandDTO> getAllBrand();
}
